package com.telit.money.start.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.AllLightCloseAndOpenAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.AdviceUtils;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 云盘微课
 * ******************************************************************
 */
public class AllLightAndCloseFragment extends Fragment implements View.OnClickListener {
    private TextView home_one_close;
    private TextView home_one_open;
    private TextView tv_all_close_and_open1;
    private TextView tv_all_close_and_open2;
    private TextView tv_all_close_and_open3;
    private TextView tv_all_close_and_open4;
    private TextView tv_all_close_and_open5;
    private TextView tv_all_close_and_open6;
    private TextView tv_all_close_and_open7;
    private TextView tv_all_close_and_open8;
    private TextView tv_all_close_and_open9;
    private TextView tv_all_close_and_open10;
    private TextView tv_all_close_and_open11;
    private TextView tv_all_close_and_open12;
    private TextView tv_all_close_and_open13;
    private TextView tv_all_close_and_open14;
    private TextView tv_all_close_and_open15;
    private TextView tv_all_close_and_open16;
    private TextView tv_all_close_and_open17;
    private TextView tv_all_close_and_open18;
    private TextView tv_all_close_and_open19;
    private TextView tv_all_close_and_open20;
    private TextView tv_all_close_and_open21;
    private TextView tv_all_close_and_open22;



    private static final int Operator_Success_Close = 2;
    private static final int Operator_Success_Open = 1;
    protected ExecutorService executorService;

    private volatile int countClose = 0;
    private volatile int countOpen = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Operator_Success_Close:
                   AdviceBean adviceBeanClose = (AdviceBean) msg.obj;
                    int road = Integer.valueOf(adviceBeanClose.getRoad());
                    String address = adviceBeanClose.getAdress();
                    String sendInfoAreess = NumUtil.getSendInfoAreess(road, address, true);
                    String name = adviceBeanClose.getName();
                    String obj = "" + adviceBeanClose.getArea() + "....." + name + "...第" + +road + "路开......" + sendInfoAreess;

                   if (countClose == 1){
                       tv_all_close_and_open1.setText(obj);
                   }else if (countClose == 2){
                       tv_all_close_and_open2.setText(obj);
                   }else if (countClose == 3){
                       tv_all_close_and_open3.setText(obj);
                   }else if (countClose == 4){
                       tv_all_close_and_open4.setText(obj);
                   }else if (countClose == 5){
                       tv_all_close_and_open5.setText(obj);
                   }else if (countClose == 6){
                       tv_all_close_and_open6.setText(obj);
                   }else if (countClose == 7){
                       tv_all_close_and_open7.setText(obj);
                   }else if (countClose == 8){
                       tv_all_close_and_open8.setText(obj);
                   }else if (countClose == 9){
                       tv_all_close_and_open9.setText(obj);
                   }else if (countClose == 10){
                       tv_all_close_and_open10.setText(obj);
                   }else if (countClose == 11){
                       tv_all_close_and_open11.setText(obj);
                   }else if (countClose == 12){
                       tv_all_close_and_open12.setText(obj);
                   }else if (countClose == 13){
                       tv_all_close_and_open13.setText(obj);
                   }else if (countClose == 14){
                       tv_all_close_and_open14.setText(obj);
                   }else if (countClose == 15){
                       tv_all_close_and_open15.setText(obj);
                   }else if (countClose == 16){
                       tv_all_close_and_open16.setText(obj);
                   }else if (countClose == 17){
                       tv_all_close_and_open17.setText(obj);
                   }else if (countClose == 18){
                       tv_all_close_and_open18.setText(obj);
                   }else if (countClose == 19){
                       tv_all_close_and_open19.setText(obj);
                   }else if (countClose == 20){
                       tv_all_close_and_open20.setText(obj);
                   }else if (countClose == 21){
                       tv_all_close_and_open21.setText(obj);
                   }else if (countClose == 22){
                       tv_all_close_and_open22.setText(obj);
                       //初始化
                       countClose=0;
                   }

                    break;

                case Operator_Success_Open:
                    AdviceBean adviceBeanOpen = (AdviceBean) msg.obj;
                    int road1 = Integer.valueOf(adviceBeanOpen.getRoad());
                    String address1 = adviceBeanOpen.getAdress();
                    String sendInfoAreess1 = NumUtil.getSendInfoAreess(road1, address1, false);
                    String name1 = adviceBeanOpen.getName();
                    String obj1 = "" + adviceBeanOpen.getArea() + "....." + name1 + "...第" + +road1+ "路开......" + sendInfoAreess1;

                    if (countOpen == 1){
                        tv_all_close_and_open1.setText(obj1);
                    }else if (countOpen == 2){
                        tv_all_close_and_open2.setText(obj1);
                    }else if (countOpen == 3){
                        tv_all_close_and_open3.setText(obj1);
                    }else if (countOpen == 4){
                        tv_all_close_and_open4.setText(obj1);
                    }else if (countOpen == 5){
                        tv_all_close_and_open5.setText(obj1);
                    }else if (countOpen == 6){
                        tv_all_close_and_open6.setText(obj1);
                    }else if (countOpen == 7){
                        tv_all_close_and_open7.setText(obj1);
                    }else if (countOpen == 8){
                        tv_all_close_and_open8.setText(obj1);
                    }else if (countOpen == 9){
                        tv_all_close_and_open9.setText(obj1);
                    }else if (countOpen == 10){
                        tv_all_close_and_open10.setText(obj1);
                    }else if (countOpen == 11){
                        tv_all_close_and_open11.setText(obj1);
                    }else if (countOpen == 12){
                        tv_all_close_and_open12.setText(obj1);
                    }else if (countOpen == 13){
                        tv_all_close_and_open13.setText(obj1);
                    }else if (countOpen == 14){
                        tv_all_close_and_open14.setText(obj1);
                    }else if (countOpen == 15){
                        tv_all_close_and_open15.setText(obj1);
                    }else if (countOpen == 16){
                        tv_all_close_and_open16.setText(obj1);
                    }else if (countOpen == 17){
                        tv_all_close_and_open17.setText(obj1);
                    }else if (countOpen == 18){
                        tv_all_close_and_open18.setText(obj1);
                    }else if (countOpen == 19){
                        tv_all_close_and_open19.setText(obj1);
                    }else if (countOpen == 20){
                        tv_all_close_and_open20.setText(obj1);
                    }else if (countOpen == 21){
                        tv_all_close_and_open21.setText(obj1);
                    }else if (countOpen == 22){
                        tv_all_close_and_open22.setText(obj1);
                        //在处初始化
                        countOpen=0;
                    }

                    break;

            }
        }
    };


    /**
     * 创建新实例
     *
     * @return
     */
    public static AllLightAndCloseFragment newInstance() {
        AllLightAndCloseFragment fragment = new AllLightAndCloseFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_cloudmicrolecture;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        home_one_close = (TextView) root.findViewById(R.id.home_one_close);
        home_one_open = (TextView) root.findViewById(R.id.home_one_open);
        tv_all_close_and_open1 = (TextView) root.findViewById(R.id.tv_all_close_and_open1);
        tv_all_close_and_open2 = (TextView) root.findViewById(R.id.tv_all_close_and_open2);
        tv_all_close_and_open3 = (TextView) root.findViewById(R.id.tv_all_close_and_open3);
        tv_all_close_and_open4 = (TextView) root.findViewById(R.id.tv_all_close_and_open4);
        tv_all_close_and_open5 = (TextView) root.findViewById(R.id.tv_all_close_and_open5);
        tv_all_close_and_open6 = (TextView) root.findViewById(R.id.tv_all_close_and_open6);
        tv_all_close_and_open7 = (TextView) root.findViewById(R.id.tv_all_close_and_open7);
        tv_all_close_and_open8 = (TextView) root.findViewById(R.id.tv_all_close_and_open8);
        tv_all_close_and_open9 = (TextView) root.findViewById(R.id.tv_all_close_and_open9);
        tv_all_close_and_open10 = (TextView) root.findViewById(R.id.tv_all_close_and_open10);
        tv_all_close_and_open11 = (TextView) root.findViewById(R.id.tv_all_close_and_open11);
        tv_all_close_and_open12 = (TextView) root.findViewById(R.id.tv_all_close_and_open12);
        tv_all_close_and_open13 = (TextView) root.findViewById(R.id.tv_all_close_and_open13);
        tv_all_close_and_open14 = (TextView) root.findViewById(R.id.tv_all_close_and_open14);
        tv_all_close_and_open15 = (TextView) root.findViewById(R.id.tv_all_close_and_open15);
        tv_all_close_and_open16 = (TextView) root.findViewById(R.id.tv_all_close_and_open16);
        tv_all_close_and_open17 = (TextView) root.findViewById(R.id.tv_all_close_and_open17);
        tv_all_close_and_open18 = (TextView) root.findViewById(R.id.tv_all_close_and_open18);
        tv_all_close_and_open19 = (TextView) root.findViewById(R.id.tv_all_close_and_open19);
        tv_all_close_and_open20 = (TextView) root.findViewById(R.id.tv_all_close_and_open20);
        tv_all_close_and_open21 = (TextView) root.findViewById(R.id.tv_all_close_and_open21);
        tv_all_close_and_open22 = (TextView) root.findViewById(R.id.tv_all_close_and_open22);


    }

    @Override
    protected void initView() {
        super.initView();
    }


    @Override
    protected void initData() {
        super.initData();
        executorService = Executors.newSingleThreadExecutor();


    }

    @Override
    protected void initListener() {
        super.initListener();
        home_one_close.setOnClickListener(this);
        home_one_open.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_one_close:
                //全部关机
                getAllClose();
                break;
            case R.id.home_one_open:
                //全部开机
                getOpenAll();

                break;
        }
    }

    //全部开机
    private void getOpenAll() {
        //先判断设备是不是在线
        boolean connected = SimpleClientNetty.getInstance().isConnected();
        if (!connected){
            ToastUtils.show("当前设备不在线");
            return;
        }

        //如果先点了关灯当前又正在开灯，应该在关灯
        if(countClose>0){
            ToastUtils.show("当前正在全部关灯中.....");
            return;
        }

        if (countOpen>0){
            ToastUtils.show("当前正在全部开灯中.....");
            return;
        }else {
            //初始化状态
            tv_all_close_and_open1.setText("");
            tv_all_close_and_open2.setText("");
            tv_all_close_and_open3.setText("");
            tv_all_close_and_open4.setText("");
            tv_all_close_and_open5.setText("");
            tv_all_close_and_open6 .setText("");
            tv_all_close_and_open7 .setText("");
            tv_all_close_and_open8 .setText("");
            tv_all_close_and_open9 .setText("");
            tv_all_close_and_open10.setText("");
            tv_all_close_and_open11.setText("");
            tv_all_close_and_open12 .setText("");
            tv_all_close_and_open13 .setText("");
            tv_all_close_and_open14 .setText("");
            tv_all_close_and_open15 .setText("");
            tv_all_close_and_open16.setText("");
            tv_all_close_and_open17.setText("");
            tv_all_close_and_open18 .setText("");
            tv_all_close_and_open19 .setText("");
            tv_all_close_and_open20 .setText("");
            tv_all_close_and_open21 .setText("");
            tv_all_close_and_open22 .setText("");
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdviceUtils adviceUtils = AdviceUtils.getInstances();
                    List<AdviceBean> allOpen = adviceUtils.getOpenAll(getContext());

                    for (int i = 0; i < allOpen.size(); i++) {
                        AdviceBean adviceBean = allOpen.get(i);
                        Thread.sleep(3000);
                        countOpen++;
                        int road = Integer.valueOf(adviceBean.getRoad());
                        String address = adviceBean.getAdress();
                        String sendInfoAreess = NumUtil.getSendInfoAreess(road, address, true);
                        String name = adviceBean.getName();
                        String obj = "" + adviceBean.getArea() + "....." + name + "...第" + +road + "路开......" + sendInfoAreess;
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                        QZXTools.logD("tianqin....我是全部开灯." + obj);

                        //全部开机
                        Message message = Message.obtain();
                        message.what = Operator_Success_Open;
                        message.obj = adviceBean;
                        mHandler.sendMessage(message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }


    //全部关机
    private void getAllClose() {
        //先判断设备是不是在线
        boolean connected = SimpleClientNetty.getInstance().isConnected();
        if (!connected){
            ToastUtils.show("当前设备不在线");
            return;
        }

        //如果先点了开灯当前又正在开灯，有点了关灯
        if(countOpen>0){
            ToastUtils.show("当前正在全部开灯中.....");
            return;
        }


        if (countClose>0){
            ToastUtils.show("当前正在全部关灯中.....");
            return;
        }else {
            //初始化状态
            tv_all_close_and_open1.setText("");
            tv_all_close_and_open2.setText("");
            tv_all_close_and_open3.setText("");
            tv_all_close_and_open4.setText("");
            tv_all_close_and_open5.setText("");
            tv_all_close_and_open6 .setText("");
            tv_all_close_and_open7 .setText("");
            tv_all_close_and_open8 .setText("");
            tv_all_close_and_open9 .setText("");
            tv_all_close_and_open10.setText("");
            tv_all_close_and_open11.setText("");
            tv_all_close_and_open12 .setText("");
            tv_all_close_and_open13 .setText("");
            tv_all_close_and_open14 .setText("");
            tv_all_close_and_open15 .setText("");
            tv_all_close_and_open16.setText("");
            tv_all_close_and_open17.setText("");
            tv_all_close_and_open18 .setText("");
            tv_all_close_and_open19 .setText("");
            tv_all_close_and_open20 .setText("");
            tv_all_close_and_open21 .setText("");
            tv_all_close_and_open22 .setText("");
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    AdviceUtils adviceUtils = AdviceUtils.getInstances();
                    List<AdviceBean> allClose = adviceUtils.getAllClose(getContext());
                    //先关电脑
                    adviceUtils.fistCompaterGames();

                    for (int i = 0; i < allClose.size(); i++) {
                        AdviceBean adviceBean = allClose.get(i);
                        List<AdviceBean.Computer> computerList = adviceBean.getComputerList();
                        int road = Integer.valueOf(adviceBean.getRoad());
                        String address = adviceBean.getAdress();
                        String sendInfoAreess = NumUtil.getSendInfoAreess(road, address, false);
                        String name = adviceBean.getName();
                        String obj = "" + adviceBean.getArea() + "....." + name + "...第" + +road + "路关......" + sendInfoAreess;

                        if (computerList != null && computerList.size() > 0) {
                            Thread.sleep(5000);
                            QZXTools.logD("tianqin.....线程2。。最后关有电脑的灯。。。" + obj);
                        } else {
                            Thread.sleep(3000);

                            QZXTools.logD("tianqin.....线程2。。先关没有电脑的灯。。。" + obj);
                        }
                        countClose++;

                        QZXTools.logD("tianqin.....线程2。.........。。。countClose=" + countClose);
                        //发送关机的指令
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);

                        Message message = Message.obtain();
                        message.what = Operator_Success_Close;
                        message.obj = adviceBean;
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }


    public void sendMessage() {
        QZXTools.logD("wobei diaoyong ke ");
    }

    @Override
    public void onDestroyView() {
        if (executorService!=null){
            executorService.shutdownNow();
        }

        super.onDestroyView();
    }


}
