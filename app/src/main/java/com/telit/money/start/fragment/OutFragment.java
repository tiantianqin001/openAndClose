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

public class OutFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;

    private Handler handler=new Handler();
    private List<AdviceBean> adviceBeans = new ArrayList<>();

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_staile_content = root.findViewById(R.id.rv_staile_content);
    }


    @Override
    protected void initData() {
        super.initData();

        List<AdviceBean> adviceBeans = NumUtil.getListInfo(getContext(), "out.json");

        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "外立面");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int road, String type, boolean isOpen,String adress,int position) {
        if (type.equals("外立面")){
            String sendInfoAreess = NumUtil.getSendInfoAreess(road, adress, isOpen);
            QZXTools.logD(sendInfoAreess);
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
                                    QZXTools. moveAdevice(getIp, 8080,"关机");
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
