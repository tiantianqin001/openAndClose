package com.telit.money.start.fragment;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjq.toast.ToastUtils;
import com.telit.money.start.R;
import com.telit.money.start.adapter.PrefaceAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefaceFragment extends Fragment implements PrefaceAdapter.onClickListener {
    private RecyclerView rv_staile_content;
    private List<AdviceBean> adviceBeans = new ArrayList<>();
    private Handler handler=new Handler();
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_staile_content = root.findViewById(R.id.rv_staile_content);
    }
    @Override
    protected void addressInfo(List<XmlBean> xmlBeans) {
        super.addressInfo(xmlBeans);
        addressList.clear();
        for (XmlBean xmlBean : xmlBeans) {
            String area = xmlBean.getArea();
            if (area.equals("序厅区")){
                addressList.add(xmlBean);
            }
        }
    }
    @Override
    protected void initData() {
        super.initData();
        AdviceBean adviceBean = new AdviceBean("序厅区","preface_one","筒灯1路(地址1,第1路)", "1","01",false);
        AdviceBean adviceBean1 = new AdviceBean("序厅区","preface_two","展项+吊顶灯带1路(地址1,第2路)", "2","01",false);
        AdviceBean adviceBean2 = new AdviceBean("序厅区","preface_three","环绕灯带1路(地址1，第3路)", "3","01",false);
        AdviceBean adviceBean3 = new AdviceBean("序厅区","preface_fore","所有插座1路(AI迎宾墙+墙体插座(地址1，第4路))", "4","01",false);
        AdviceBean adviceBean4 = new AdviceBean("序厅区","preface_five","轨道灯1路(地址1，第5路)", "5","01",false);
        adviceBeans.add(adviceBean);
        adviceBeans.add(adviceBean1);
        adviceBeans.add(adviceBean2);
        adviceBeans.add(adviceBean3);
        adviceBeans.add(adviceBean4);
        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "序厅区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }
    @Override
    public void onClick(int road, String type, boolean isOpen,String adress,int position) {
        if (type.equals("序厅区")) {
            //第4路要设置设备的关只关设备开机是通电自己就开机
            if (!isOpen){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        XmlBean xmlBean = addressList.get(position);
                        String getIp = xmlBean.getUrl();
                        int getPort = xmlBean.getPort();
                        if (TextUtils.isEmpty(getIp) || TextUtils.isEmpty(String.valueOf(getPort))){
                            ToastUtils.show("ip和端口不能为空");
                            return;
                        }
                        QZXTools. moveAdevice(getIp, getPort, "关机");
                    }
                }, 1000 * 60);
            }
            //控住设备的开和关
            String sendInfoAreess = NumUtil.getSendInfoAreess(road, adress, isOpen);
            if (isOpen){
                QZXTools.logD("qin989.。。..."+type+"。.第"+road+"路开......"+sendInfoAreess);
            }else {
                QZXTools.logD("qin989.。。.."+type+"。.第"+road+"路关......"+sendInfoAreess);
            }
            QZXTools.logD(sendInfoAreess);

            //先判断是不是在线
            boolean connected = SimpleClientNetty.getInstance().isConnected();
            if (connected){
                //发送消息
                //退出班级,服务端会主动关闭连接
                //如果当前是关灯，要先关电脑等30秒在关电 1000*30
                if (!isOpen){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                        }
                    },1);
                }else {

                    SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                }
            }else {
                ToastUtils.show("当前设备不在线");
            }
        }
    }
}
