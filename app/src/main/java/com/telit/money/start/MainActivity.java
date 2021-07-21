package com.telit.money.start;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.telit.money.start.activity.ChangeCrdActivity;
import com.telit.money.start.activity.LoginActivity;
import com.telit.money.start.bean.HandleTimeBean;
import com.telit.money.start.constant.Constant;
import com.telit.money.start.dialoge.TipsDialog;
import com.telit.money.start.dialoge.UrlUpdateDialog;
import com.telit.money.start.fragment.MyContentFragment;
import com.telit.money.start.net.Common;
import com.telit.money.start.netty.MsgUtils;
import com.telit.money.start.netty.SimpleClientListener;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;
import com.telit.money.start.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SimpleClientListener {
    private static final String TAG = "MainActivity";
    private MyHandler mHandler;
    private Runnable reComment = new Runnable() {
        @Override
        public void run() {
            //重连
            SimpleClientNetty.getInstance().reConnect();
            mHandler.postDelayed(reComment, 8 * 1000);
            QZXTools.logE("TAG+\"...我是离线....的情况先重连", null);
        }
    };
    private ImageView home_timetable;
    private TextView home_one_open;
    private TextView home_one_close;
    private TextView home_line_open;
    private TextView home_line_close;
    private ScheduledExecutorService messageExecutorService;

    private String onLine = "";
    private TextView tv_wen_du;
    private TextView tv_shi_du;
    private TextView tv_er_yang;
    private RelativeLayout rl_clear_sp;


    private class MyHandler extends Handler {
        private WeakReference<MainActivity> activity;

        public MyHandler(MainActivity testActivity) {
            super(Looper.getMainLooper());
            this.activity = new WeakReference<MainActivity>(testActivity);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity context = activity.get();
            if (null == context) {
                return;
            }
            switch (msg.what) {
                case Constant.OnLine:
                    ToastUtils.show("在线");
                    onLine = "在线";
                    if (mHandler != null) {
                        mHandler.removeCallbacks(reComment);
                    }

                    break;
                case Constant.OffLine:
                    //因为会出现界面关闭，这个handler还在执行的情况，空指针
                    SimpleClientNetty.getInstance().setReconnected(true);
                    onLine = "离线";

                    boolean netAvail = QZXTools.isNetworkAvailable();
                    if (!netAvail) {
                        ToastUtils.show("当前断网了");
                        //打开设置界面
                        QZXTools.enterWifiSetting(MainActivity.this);
                    } else {
                        ToastUtils.show("离线");
                        //这种状态教师端已经关闭了不能连接了
                        mHandler.postDelayed(reComment, 5000);
                    }
                    break;
                case Constant.ReceiveMessage:
                    String stringData = (String) msg.obj;
                    //如果没有收到内容
                    if (TextUtils.isEmpty(stringData)) return;


                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置导航栏的颜色
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        initView();
        initListener();
        initData();
        ////这里不要最后一位和第一位
 /*       String originalData = "55 11 00 17 01 01 FF FF FF FF FF FF FF FF FF FF FF";
        //这个是获取最后一位
        NumUtil.bytesToHexLastString(originalData);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (messageExecutorService == null) {
            messageExecutorService = Executors.newSingleThreadScheduledExecutor();

        }

        messageExecutorService.execute(new Runnable() {
            @Override
            public void run() {

                mHandler = new MyHandler(MainActivity.this);
                //通讯服务接口
                SimpleClientNetty.getInstance().setSimpleClientListener(MainActivity.this);
                String socketIp = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("socketIp");
                String socketPort = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("socketPort");
                if (TextUtils.isEmpty(socketIp) || TextUtils.isEmpty(socketPort)) {
                    SimpleClientNetty.getInstance().init(Constant.SOCKED_IP, Constant.SOCKED_PORT);
                } else {
                    //保存连接的ip和端口
                    SimpleClientNetty.getInstance().init(socketIp, Integer.valueOf(socketPort));
                }


            }
        });
    }

    private void initListener() {
        //切换ip
        home_timetable.setOnClickListener(this);
        //一键开机
        home_one_open.setOnClickListener(this);
        //一键关机
        home_one_close.setOnClickListener(this);
        //一键开灯
        home_line_open.setOnClickListener(this);
        //一键关灯
        home_line_close.setOnClickListener(this);


    }

    private void initData() {
        //获取温度和湿度的接口
        String url = Common.DeviceData;
        //实时数据接口
        OkGo.<String>get(url)
                .tag(this)
                .headers("userId", "4806")
                .params("groupId", "22619")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        QZXTools.logD(response.body().toString());
                        try {
                            HandleTimeBean handleTimeBean = JSON.parseObject(response.body().toString(),
                                    HandleTimeBean.class);
                            int code = handleTimeBean.getCode();
                            if (code == 1000) {
                                List<HandleTimeBean.DataBean> data = handleTimeBean.getData();
                                if (data != null && data.size() > 0) {
                                    //获取温度和湿度
                                    List<HandleTimeBean.DataBean.RealTimeDataBean> realTimeData = data.get(0).getRealTimeData();
                                    if (realTimeData != null && realTimeData.size() > 0) {
                                        if (realTimeData != null && realTimeData.size() > 0) {
                                            //温度

                                            tv_wen_du.setText(realTimeData.get(0).getDataValue() + "°");
                                        }
                                        if (realTimeData != null && realTimeData.size() > 1) {

                                            //湿度
                                            tv_shi_du.setText(realTimeData.get(1).getDataValue() + "%");
                                        }
                                    }
                                    //获取二氧化碳
                                    List<HandleTimeBean.DataBean.RealTimeDataBean> realTimeData1 = data.get(1).getRealTimeData();
                                    if (realTimeData1 != null && realTimeData1.size() > 0) {
                                        //二氧化碳
                                        if (realTimeData1 != null && realTimeData1.size() > 0) {

                                            tv_er_yang.setText(realTimeData1.get(0).getDataValue() + "ppm");
                                        }
                                    }


                                }
                            }

                        } catch (Exception e) {
                            e.getMessage();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        QZXTools.logD(response.body().toString());
                    }
                });


    }

    private void initView() {
        //获取温度
        tv_wen_du = (TextView) findViewById(R.id.tv_wen_du);
        tv_shi_du = (TextView) findViewById(R.id.tv_shi_fu);
        tv_er_yang = (TextView) findViewById(R.id.tv_er_yang);
        //获取湿度
        //获取二氧化碳


        home_timetable = (ImageView) findViewById(R.id.home_timetable);
        home_one_open = (TextView) findViewById(R.id.home_one_open);
        home_one_close = (TextView) findViewById(R.id.home_one_close);
        home_line_open = (TextView) findViewById(R.id.home_line_open);
        home_line_close = (TextView) findViewById(R.id.home_line_close);
        //移除sp
        rl_clear_sp = (RelativeLayout) findViewById(R.id.rl_clear_sp);
        rl_clear_sp.setOnClickListener(this);


        MyContentFragment fragment = MyContentFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private int count = 0;
    private long touchFirstTime;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_one_open:
                //一键开机
            /*    if (TextUtils.isEmpty(onLine) || onLine.equals("离线")) {
                    ToastUtils.show("当前设备不在线");
                    return;
                }*/
                for (int i = 0; i < 10; i++) {
                 String   getIp = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverIp" + i);
                  String  getPort = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverPort" + i);
                    if (TextUtils.isEmpty(getIp) || TextUtils.isEmpty(getPort)){
                        ToastUtils.show("当前"+i+1+"台设备没有被绑定");
                        return;
                    }
                    QZXTools. moveAdevice(getIp, getPort, "重启");
                }


                break;
            case R.id.home_one_close:
                //一键关机
            /*    if (TextUtils.isEmpty(onLine) || onLine.equals("离线")) {
                    ToastUtils.show("当前设备不在线");
                    return;
                }*/
                //关机要延迟1秒
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 10; i++) {
                            String   getIp = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverIp" + i);
                            String  getPort = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverPort" + i);
                            if (TextUtils.isEmpty(getIp) || TextUtils.isEmpty(getPort)){
                                ToastUtils.show("当前"+i+1+"台设备没有被绑定");
                                return;
                            }
                            QZXTools. moveAdevice(getIp, getPort, "关闭");
                        }


                    }
                }, 1200);

                break;
            case R.id.home_line_open:
                //一键开灯
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")) {
                    ToastUtils.show("当前设备不在线");
                    return;
                }
                break;
            case R.id.home_line_close:
                //一键关灯
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")) {
                    ToastUtils.show("当前设备不在线");
                    return;
                }
                break;

            case R.id.rl_clear_sp:
                //移除sp
                count++;
                long curTime1 = System.currentTimeMillis();
                if (count == 1) {
                    touchFirstTime = curTime1;
                }
                if (count == 3 && curTime1 - touchFirstTime <= 1000) {
                    count = 0;
                    ToastUtils.show("移除服务地址成功");
                    for (int i = 0; i < 10; i++) {
                        SharedPreferenceUtil.getInstance(MyApplication.getInstance()).setString("serverIp" +i,"");
                        SharedPreferenceUtil.getInstance(MyApplication.getInstance()).setString("serverPort" +i,"");
                    }


                } else if (curTime1 - touchFirstTime > 1000) {
                    //重置
                    count = 0;
                } else if (count > 3) {
                    //重置
                    count = 0;
                }
                break;

            case R.id.home_timetable:
                //切换ip
                count++;
                long curTime = System.currentTimeMillis();
                if (count == 1) {
                    touchFirstTime = curTime;
                }
                if (count == 3 && curTime - touchFirstTime <= 1000) {
                    count = 0;
                    //进入修改服务和通讯IP界面
                    UrlUpdateDialog urlUpdateDialog = new UrlUpdateDialog();
                    urlUpdateDialog.show(getSupportFragmentManager(), UrlUpdateDialog.class.getSimpleName());
                } else if (curTime - touchFirstTime > 1000) {
                    //重置
                    count = 0;
                } else if (count > 3) {
                    //重置
                    count = 0;
                }
                break;


        }
    }

    @Override
    public void onLine() {
        if (mHandler == null) {
            return;
        }
        Message message = mHandler.obtainMessage();
        message.what = Constant.OnLine;
        message.obj = "在线";
        mHandler.sendMessage(message);
    }

    @Override
    public void offLine() {
        if (mHandler == null) {
            return;
        }
        Message message = mHandler.obtainMessage();
        message.what = Constant.OffLine;
        message.obj = "离线";
        mHandler.sendMessage(message);
    }

    @Override
    public void receiveData(String msgInfo) {
        if (mHandler == null) {
            return;
        }
        Log.i("qin002", "receiveData: " + msgInfo);
        Message message = mHandler.obtainMessage();
        message.what = Constant.ReceiveMessage;
        message.obj = msgInfo;
        mHandler.sendMessage(message);
    }

    @Override
    public void isNoUser(boolean isNet) {
        //todo
    }

    @Override
    public void onBackPressed() {
        //弹出tips
        TipsDialog tipsDialog = new TipsDialog();
        tipsDialog.setTipsStyle("是否退出该?\n警告：如果退出，下次进入将不会收到当前在綫的状态",
                "返回", "退出", -1);
        tipsDialog.setBackNoMiss();
        tipsDialog.setClickInterface(new TipsDialog.ClickInterface() {
            @Override
            public void cancle() {
                tipsDialog.dismissAllowingStateLoss();
            }

            @Override
            public void confirm() {
                tipsDialog.dismissAllowingStateLoss();
                // InteractiveActivity.super.onBackPressed();
                MainActivity.this.finish();
            }
        });
        tipsDialog.show(getSupportFragmentManager(), TipsDialog.class.getSimpleName());
    }


    private Handler dataHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            initData();
            QZXTools.logD("我被调用了");
            dataHandler.postDelayed(runnable, 1000 * 60);
        }
    };

    private void flushData() {
        dataHandler.postDelayed(runnable, 1000 * 60);
    }
}