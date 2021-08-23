package com.telit.money.start.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.R;
import com.telit.money.start.adapter.PrefaceAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.ArrayList;
import java.util.List;

public class CasualFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;
    private Handler handler=new Handler();
    private List<AdviceBean> adviceBeans = new ArrayList<>();
    @Override
    protected void addressInfo(List<XmlBean> xmlBeans) {
        super.addressInfo(xmlBeans);
        addressList.clear();
        for (XmlBean xmlBean : xmlBeans) {
            String area = xmlBean.getArea();
            if (area.equals("休闲区")){
                addressList.add(xmlBean);
            }
        }
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_staile_content = root.findViewById(R.id.rv_staile_content);
    }


    @Override
    protected void initData() {
        super.initData();
        AdviceBean adviceBean = new AdviceBean("休闲区","casual_one","轨道灯1路(地址2，第6路)" , "6","02",false);
        AdviceBean adviceBean1 = new AdviceBean( "休闲区","casual_two","灯带1路(地址2，第7路)", "7","02",false);
        adviceBeans.add(adviceBean);
        adviceBeans.add(adviceBean1);

        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "休闲区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int road, String type, boolean isOpen,String adress,int position) {
        if (type.equals("休闲区")){
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
            //先判断是不是在线
            String sendInfoAreess = NumUtil.getSendInfoAreess(road, adress, isOpen);
            QZXTools.logD(sendInfoAreess);
            boolean connected = SimpleClientNetty.getInstance().isConnected();
            if (connected){
                //发送消息
                //退出班级,服务端会主动关闭连接
                //如果当前是关灯，要先关电脑等30秒在关电
                if (!isOpen){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                        }
                    },1000*30);
                }else {

                    SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                }
            }else {
                ToastUtils.show("当前设备不在线");
            }
        }

    }



}
