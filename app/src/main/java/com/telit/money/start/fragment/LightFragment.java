package com.telit.money.start.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.LightAdapter;
import com.telit.money.start.adapter.ZhanXiangAdapter;
import com.telit.money.start.bean.LightBean;
import com.telit.money.start.bean.SaveLight;
import com.telit.money.start.customview.CustomPopWindow;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 本地微课
 * ******************************************************************
 */
public class LightFragment extends Fragment implements LightAdapter.onClickListerner {


    private RecyclerView rv_list_light;
    private List<LightBean> lightBeans =new ArrayList<>();
    private CustomPopWindow mCustomPopWindow;

    /**
     * 创建新实例
     *
     * @return
     */
    public static LightFragment newInstance() {
       LightFragment fragment = new LightFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_localmicrolecture;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_list_light = root.findViewById(R.id.rv_list_light);
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected void initData() {
        super.initData();
        LightBean lightBean1 =new LightBean();
        lightBean1.setNum(0);
        lightBean1.setClose("第1路灯全关");
        lightBean1.setOpen("第1路灯全开");
        lightBean1.setAddress("第1路地址是00");

        lightBeans.add(lightBean1);

        LightBean lightBean2 =new LightBean();
        lightBean2.setNum(1);
        lightBean2.setClose("第2路灯全关");
        lightBean2.setOpen("第2路灯全开");
        lightBean2.setAddress("第2路地址是00");
        lightBeans.add(lightBean2);

        LightBean lightBean3 =new LightBean();
        lightBean3.setNum(2);
        lightBean3.setClose("第3路灯全关");
        lightBean3.setOpen("第3路灯全开");
        lightBean3.setAddress("第3路地址是00");
        lightBeans.add(lightBean3);
        LightBean lightBean4 =new LightBean();
        lightBean4.setNum(3);
        lightBean4.setClose("第4路灯全关");
        lightBean4.setOpen("第4路灯全开");
        lightBean4.setAddress("第4路地址是00");
        lightBeans.add(lightBean4);

        LightBean lightBean5 =new LightBean();
        lightBean5.setNum(4);
        lightBean5.setClose("第5路灯全关");
        lightBean5.setOpen("第5路灯全开");
        lightBean5.setAddress("第5路地址是00");
        lightBeans.add(lightBean5);

        LightBean lightBean6 =new LightBean();
        lightBean6.setNum(5);
        lightBean6.setClose("第6路灯全关");
        lightBean6.setOpen("第6路灯全开");
        lightBean6.setAddress("第6路地址是00");
        lightBeans.add(lightBean6);

        LightBean lightBean7 =new LightBean();
        lightBean7.setNum(6);
        lightBean7.setClose("第7路灯全关");
        lightBean7.setOpen("第7路灯全开");
        lightBean7.setAddress("第7路地址是00");
        lightBeans.add(lightBean7);

        LightBean lightBean8 =new LightBean();
        lightBean8.setNum(7);
        lightBean8.setClose("第8路灯全关");
        lightBean8.setOpen("第8路灯全开");
        lightBean8.setAddress("第8路地址是00");
        lightBeans.add(lightBean8);

        LightBean lightBean9 =new LightBean();
        lightBean9.setNum(8);
        lightBean9.setClose("第9路灯全关");
        lightBean9.setOpen("第9路灯全开");
        lightBean9.setAddress("第9路地址是00");
        lightBeans.add(lightBean9);

        LightBean lightBean10 =new LightBean();
        lightBean10.setNum(9);
        lightBean10.setClose("第10路灯全关");
        lightBean10.setOpen("第10路灯全开");
        lightBean10.setAddress("第10路地址是00");
        lightBeans.add(lightBean10);

        LightBean lightBean11 =new LightBean();
        lightBean11.setNum(10);
        lightBean11.setClose("第11路灯全关");
        lightBean11.setOpen("第11路灯全开");
        lightBean11.setAddress("第11路地址是00");
        lightBeans.add(lightBean11);

        LightBean lightBean12 =new LightBean();
        lightBean12.setNum(11);
        lightBean12.setClose("第12路灯全关");
        lightBean12.setOpen("第12路灯全开");
        lightBean12.setAddress("第12路地址是00");
        lightBeans.add(lightBean12);


        LightAdapter lightAdapter = new LightAdapter(getActivity(), lightBeans);
        rv_list_light.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_list_light.setAdapter(lightAdapter);
        lightAdapter.setOnCliclListener(this);



    }


    @Override
    protected void initListener() {
        super.initListener();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 点击的下表和类型
     * @param position
     * @param type
     */
    @Override
    public void onClick(TextView view, int position, String type) {
        //判断 当前是不是在线然后发送消息
        ToastUtils.show(position+"........"+type);
                if (type.equals("开灯")){
                    String stingLight = QZXTools.openStingLight(position);
                    QZXTools.logD(stingLight);
                    //这个是获取最后一位
                    String lastString = NumUtil.bytesToHexLastString(stingLight);
                    String retailString= stingLight + " " + lastString;
                    QZXTools.logD(retailString);
                }else if (type.equals("关灯")){
                    String stingLight = QZXTools.closeStingLight(position);
                    QZXTools.logD(stingLight);
                    //这个是获取最后一位
                    String lastString = NumUtil.bytesToHexLastString(stingLight);
                    String retailString= stingLight + " " + lastString;
                    QZXTools.logD(retailString);
                }else if (type.equals("地址")){
                    //切换地址
                    View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_menu,null);
                    //处理popWindow 显示内容
                    handleLogic(contentView,position,view);
                    //创建并显示popWindow
                    mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(getContext())
                            .setView(contentView)
                            .create()
                            .showAsDropDown(view,0,-250);


                }


    }


    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     * @param position
     * @param textView
     */
    private void handleLogic(View contentView, int position, TextView textView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showContent = "第"+(position+1) +"路地址是00";
                        break;
                    case R.id.menu2:
                        showContent = "第"+(position+1) +"路地址是01";
                        break;
                    case R.id.menu3:
                        showContent = "第"+(position+1) +"路地址是02";
                        break;

                }
                textView.setText(showContent);

                //把当前点击的地址保存到数据库
                SaveLight saveLight = new SaveLight();
                saveLight.setIndex(position);
                saveLight.setWord(showContent);
                saveLight.setAdress("0"+(position));
                saveLight.save();
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);

    }
}
