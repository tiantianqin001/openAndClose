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
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.List;

public class VisualFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;
    private Handler handler=new Handler();
    private List<AdviceBean> adviceBeans;


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_staile_content = root.findViewById(R.id.rv_staile_content);
    }
    @Override
    protected void initData() {
        super.initData();
        adviceBeans = NumUtil.getListInfo(getContext(), "visual.json");



        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "视觉区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int road, String type, boolean isOpen,String adress,int position) {

        if (type.equals("视觉区")){

            //控住设备的开和关
            //先判断是不是在线
            String sendInfoAreess = NumUtil.getSendInfoAreess(road, adress, isOpen);
            if (isOpen){
                QZXTools.logD("qin989.。。..."+type+"。.第"+road+"路开......"+sendInfoAreess);
            }else {
                QZXTools.logD("qin989.。。.."+type+"。.第"+road+"路关......"+sendInfoAreess);
            }
            boolean connected = SimpleClientNetty.getInstance().isConnected();
            if (connected){
                //发送消息
                if (!isOpen){
                    AdviceBean adviceBean = adviceBeans.get(position);
                    List<AdviceBean.Computer> computerList = adviceBean.getComputerList();

                    if (computerList!=null && computerList.size()>0){

                        //这个是关电脑
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                for (AdviceBean.Computer computer : computerList) {
                                    String getIp = computer.getUrl();

                                    if (TextUtils.isEmpty(getIp) ){
                                        ToastUtils.show("ip和端口不能为空");
                                        return;
                                    }
                                    QZXTools. moveAdevice(getIp);
                                }
                            }
                        }, 40);

                        //这个是关灯
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                            }
                        },1000 * 60);
                    }else {
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                    }


                }else {

                    SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                }
            }else {
                ToastUtils.show("当前设备不在线");
            }
        }

    }
}
