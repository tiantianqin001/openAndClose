package com.telit.money.start.adapter;

import android.content.Context;
import android.os.Handler;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.hjq.toast.ToastUtils;
import com.telit.money.start.R;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllLightCloseAndOpenAdapter extends RecyclerView.Adapter<AllLightCloseAndOpenAdapter.CloseAndOpenHolder> {
    private Context context;

    private List<AdviceBean> addall;
    private boolean isOpen;

    private ExecutorService executorService;
    private TextView tv_all_close_and_open;
    private Handler mHandler = new Handler();

    public AllLightCloseAndOpenAdapter(Context context, List<AdviceBean> addall, boolean isOpen) {
        this.context = context;
        this.addall = addall;
        this.isOpen = isOpen;
        executorService = Executors.newSingleThreadExecutor();
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

        holder.setIsRecyclable(false);
        AdviceBean adviceBean = addall.get(position);

        int road = Integer.valueOf(adviceBean.getRoad());
        String address = adviceBean.getAdress();
        String sendInfoAreess = NumUtil.getSendInfoAreess(road, address, isOpen);

        if (isOpen) {
            String name = adviceBean.getName();
            String obj = "" + adviceBean.getArea() + "....." + name + "...第" + +road + "路开......" + sendInfoAreess;
            tv_all_close_and_open.setText(obj);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            String name = adviceBean.getName();
            String obj = "" + adviceBean.getArea() + "....." + name + "...第" + +road + "路关......" + sendInfoAreess;
            tv_all_close_and_open.setText(obj);
            List<AdviceBean.Computer> computerList = adviceBean.getComputerList();
            if (computerList != null && computerList.size() > 0) {
                //这个是关电脑
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (AdviceBean.Computer computer : computerList) {
                            String getIp = computer.getUrl();

                            if (TextUtils.isEmpty(getIp)) {
                                ToastUtils.show("ip和端口不能为空");
                                return;
                            }
                            QZXTools.moveAdevice(getIp);
                        }
                    }
                }, 40);

                //这个是关灯
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                    }
                }, 1000 * 60);
            } else {
                SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
            }


        }

    }

    @Override
    public int getItemCount() {
        return addall.size();
    }

    public void OnDestroy() {
        if (executorService!=null){
            executorService.shutdownNow();
          //  mHandler.removeCallbacks();
        }

    }

    public class CloseAndOpenHolder extends RecyclerView.ViewHolder {

        public CloseAndOpenHolder(@NonNull View itemView) {
            super(itemView);

            tv_all_close_and_open = itemView.findViewById(R.id.tv_all_close_and_open);

        }
    }

}
