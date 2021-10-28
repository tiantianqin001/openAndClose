package com.telit.money.start.adapter;

import android.app.Activity;
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
    private Activity context;

    private List<AdviceBean> addall;
    private boolean isOpen;

    private ExecutorService executorService;
    private TextView tv_all_close_and_open;
    private Handler mHandler = new Handler();


    public AllLightCloseAndOpenAdapter(Activity context, List<AdviceBean> addall, boolean isOpen) {
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

        QZXTools.logD("tianqin.....线程2。。关有电脑的灯111111111111111。。。addall="+addall.size()+".........."+addall);

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
                        QZXTools.logD("tianqin....."+obj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            String name = adviceBean.getName();
            String obj = "" + adviceBean.getArea() + "....." + name + "...第" + +road + "路关......" + sendInfoAreess;
            tv_all_close_and_open.setText(obj);
            QZXTools.logD("tianqin.....线程2。。关有电脑的灯22222222222222222。。。"+adviceBean);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<AdviceBean.Computer> computerList = adviceBean.getComputerList();
                        Thread.sleep(3000);
                        QZXTools.logD("tianqin.....线程2。。有电脑的灯。。。"+obj);
                        //发送关机的指令
                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });


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
