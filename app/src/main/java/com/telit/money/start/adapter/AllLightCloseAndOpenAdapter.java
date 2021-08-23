package com.telit.money.start.adapter;

import android.content.Context;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.telit.money.start.R;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.netty.SimpleClientNetty;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllLightCloseAndOpenAdapter extends RecyclerView.Adapter<AllLightCloseAndOpenAdapter.CloseAndOpenHolder> {
    private Context context;

    private List<AdviceBean> addall;
    private boolean isOpen;

    private  ExecutorService executorService;
    private  TextView tv_all_close_and_open;
    private Handler mHandler = new Handler();


            public AllLightCloseAndOpenAdapter(Context context, List<AdviceBean> addall, boolean isOpen) {
                this.context = context;
                this.addall = addall;
                this.isOpen = isOpen;
                executorService= Executors.newSingleThreadExecutor();


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
                    String obj = "" + adviceBean.getArea() + "...第" + road + "路开......" + sendInfoAreess;
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
                    //关闭电要等一分钟 先关闭了电脑再关闭电
                    QZXTools.logD("qin989.。。.." + adviceBean);
                    String area = adviceBean.getArea();
                    String obj = "" + area + "...第" + road + "路关......" + sendInfoAreess;
                    tv_all_close_and_open.setText(obj);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000 * 3);
                                        SimpleClientNetty.getInstance().sendMsgToServer(sendInfoAreess);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    },1000 * 90);

                }

            }

            @Override
            public int getItemCount() {
                return addall.size();
            }

            public class CloseAndOpenHolder extends RecyclerView.ViewHolder {

                public CloseAndOpenHolder(@NonNull View itemView) {
                    super(itemView);

                    tv_all_close_and_open = itemView.findViewById(R.id.tv_all_close_and_open);

                }
            }

    }
