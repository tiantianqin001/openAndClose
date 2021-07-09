package com.telit.money.start;

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
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.telit.money.start.activity.ChangeCrdActivity;
import com.telit.money.start.activity.LoginActivity;
import com.telit.money.start.constant.Constant;
import com.telit.money.start.dialoge.TipsDialog;
import com.telit.money.start.dialoge.UrlUpdateDialog;
import com.telit.money.start.fragment.MyContentFragment;
import com.telit.money.start.netty.MsgUtils;
import com.telit.money.start.netty.SimpleClientListener;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.QZXTools;
import com.telit.money.start.utils.SharedPreferenceUtil;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SimpleClientListener {
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

    private String onLine="";

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
                    onLine="在线";
                    if (mHandler != null) {
                        mHandler.removeCallbacks(reComment);
                    }

                    break;
                case Constant.OffLine:
                    //因为会出现界面关闭，这个handler还在执行的情况，空指针
                    SimpleClientNetty.getInstance().setReconnected(true);
                    onLine="离线";

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
        //判断是不是已经登录
        String userId = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("userId");
        if (TextUtils.isEmpty(userId)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

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
                if (TextUtils.isEmpty(socketIp) || TextUtils.isEmpty(socketPort)){
                    SimpleClientNetty.getInstance().init(Constant.SOCKED_IP, Constant.SOCKED_PORT);
                }else {
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

    }

    private void initView() {
        home_timetable = (ImageView) findViewById(R.id.home_timetable);
        home_one_open = (TextView) findViewById(R.id.home_one_open);
        home_one_close = (TextView) findViewById(R.id.home_one_close);
        home_line_open = (TextView) findViewById(R.id.home_line_open);
        home_line_close = (TextView) findViewById(R.id.home_line_close);


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
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")){
                    ToastUtils.show("当前设备不在线");
                    return;
                }
                SimpleClientNetty.getInstance().sendMsgToServer(MsgUtils.HEAD_ONE_OPEN,
                        "");
                break;
            case R.id.home_one_close:
                //一键关机
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")){
                    ToastUtils.show("当前设备不在线");
                    return;
                }
                //关机要延迟1秒
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SimpleClientNetty.getInstance().sendMsgToServer(MsgUtils.HEAD_ONE_CLOSE,
                                "");
                    }
                },1200);

                break;
            case R.id.home_line_open:
                //一键开灯
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")){
                    ToastUtils.show("当前设备不在线");
                    return;
                }
                break;
            case R.id.home_line_close:
                //一键关灯
                if (TextUtils.isEmpty(onLine) || onLine.equals("离线")){
                    ToastUtils.show("当前设备不在线");
                    return;
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
}