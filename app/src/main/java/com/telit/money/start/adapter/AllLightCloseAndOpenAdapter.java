package com.telit.money.start.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.R;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AllLightCloseAndOpenAdapter extends RecyclerView.Adapter<AllLightCloseAndOpenAdapter.CloseAndOpenHolder> {
    private Context context;
    private List<List<AdviceBean>> addall;
    private boolean isOpen;
    private Handler handler = new Handler();
    protected List<XmlBean> prefaceList = new ArrayList<>();
    protected List<XmlBean> visualList = new ArrayList<>();
    protected List<XmlBean> voiceList = new ArrayList<>();
    protected List<XmlBean> ecologyList = new ArrayList<>();
    protected List<XmlBean> tailList = new ArrayList<>();
    protected List<XmlBean> casualList = new ArrayList<>();
    protected List<XmlBean> outList = new ArrayList<>();

    public AllLightCloseAndOpenAdapter(Context context, List<List<AdviceBean>> addall, boolean isOpen) {
        this.context = context;
        this.addall = addall;
        this.isOpen = isOpen;
        //解析数据
        try {
            InputStream inputStream = context.getAssets().open("address.xml");
            List<XmlBean> xmlBeans = NumUtil.getUrls(inputStream);
            QZXTools.logE(xmlBeans.toString(), null);
            prefaceList.clear();
            visualList.clear();
            voiceList.clear();
            ecologyList.clear();
            tailList.clear();
            casualList.clear();
            outList.clear();
            for (XmlBean xmlBean : xmlBeans) {
                String area = xmlBean.getArea();
                if (area.equals("序厅区")) {
                    prefaceList.add(xmlBean);
                } else if (area.equals("视觉区")) {
                    visualList.add(xmlBean);
                } else if (area.equals("语音区")) {
                    voiceList.add(xmlBean);
                } else if (area.equals("生态区")) {
                    ecologyList.add(xmlBean);
                } else if (area.equals("尾厅区")) {
                    tailList.add(xmlBean);
                } else if (area.equals("休闲区")) {
                    casualList.add(xmlBean);
                } else if (area.equals("外立面")) {
                    outList.add(xmlBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public CloseAndOpenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_close_and_open, parent, false);
        CloseAndOpenHolder closeAndOpenHolder = new CloseAndOpenHolder(view);
        return closeAndOpenHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CloseAndOpenHolder holder, int position) {
        switch (position) {
            case 0:
                //序厅区
                List<AdviceBean> adviceBeans = addall.get(position);
                for (int i = 0; i < adviceBeans.size(); i++) {
                    //第4路要设置设备的关只关设备开机是通电自己就开机
                    if (position == 3 && !isOpen) {
                        XmlBean xmlBean = prefaceList.get(i);
                        String getIp = xmlBean.getUrl();
                        int getPort = xmlBean.getPort();

                        QZXTools.moveAdevice(getIp, getPort, "关机");
                    }

                    //控住设备的开和关
                    int road = Integer.valueOf(adviceBeans.get(i).getRoad());
                    String address = adviceBeans.get(i).getAdress();
                    String sendInfoAreess = NumUtil.getSendInfoAreess(road, address, isOpen);
                    QZXTools.logD(sendInfoAreess);
                    //先判断是不是在线
                    boolean connected = SimpleClientNetty.getInstance().isConnected();
                    if (connected) {
                        //发送消息
                        //退出班级,服务端会主动关闭连接
                        //如果当前是关灯，要先关电脑等30秒在关电
                        if (!isOpen) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                                }
                            }, 1000 * 30);
                        } else {

                            SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                        }
                    } else {
                        ToastUtils.show("当前设备不在线");
                    }
                }

                break;
        }


    }

    @Override
    public int getItemCount() {
        return addall.size();
    }

    protected class CloseAndOpenHolder extends RecyclerView.ViewHolder {

        public CloseAndOpenHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.tv_all_close_and_open);
        }
    }
}
