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


            protected List<XmlBean> prefaceList = new ArrayList<>();
            protected List<XmlBean> visualList = new ArrayList<>();
            protected List<XmlBean> voiceList = new ArrayList<>();
            protected List<XmlBean> ecologyList = new ArrayList<>();
            protected List<XmlBean> tailList = new ArrayList<>();
            protected List<XmlBean> casualList = new ArrayList<>();
            protected List<XmlBean> outList = new ArrayList<>();

            public AllLightCloseAndOpenAdapter(Context context, List<AdviceBean> addall, boolean isOpen) {
                this.context = context;
                this.addall = addall;
                this.isOpen = isOpen;
                executorService= Executors.newSingleThreadExecutor();
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
                    QZXTools.logD("qin989.。。.." + adviceBean);
                    String area = adviceBean.getArea();
                    String obj = "" + area + "...第" + road + "路关......" + sendInfoAreess;
                    tv_all_close_and_open.setText(obj);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(5000);
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

            public class CloseAndOpenHolder extends RecyclerView.ViewHolder {

                public CloseAndOpenHolder(@NonNull View itemView) {
                    super(itemView);

                    tv_all_close_and_open = itemView.findViewById(R.id.tv_all_close_and_open);

                }
            }

    }
