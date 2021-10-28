package com.telit.money.start.utils;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.MyApplication;
import com.telit.money.start.bean.AdviceBean;

import java.util.ArrayList;
import java.util.List;

public class AdviceUtils {
    private List<AdviceBean> addall = new ArrayList<>();
    private List<AdviceBean> prefaces = new ArrayList<>();
    private List<AdviceBean> prefaces_compater = new ArrayList<>();


    private List<AdviceBean> visuals = new ArrayList<>();
    private List<AdviceBean> visuals_compater = new ArrayList<>();

    private List<AdviceBean> voices = new ArrayList<>();
    private List<AdviceBean> voices_compater = new ArrayList<>();

    private List<AdviceBean> ecologys = new ArrayList<>();
    private List<AdviceBean> ecologys_compater = new ArrayList<>();

    private List<AdviceBean> tails = new ArrayList<>();
    private List<AdviceBean> tails_compater = new ArrayList<>();

    private List<AdviceBean> casuals = new ArrayList<>();
    private List<AdviceBean> casuals_compater = new ArrayList<>();

    private List<AdviceBean> outs = new ArrayList<>();
    private List<AdviceBean> outs_compater = new ArrayList<>();

    private List<AdviceBean> adviceBeans = new ArrayList<>();


    private AdviceUtils() {
    }

    private static volatile AdviceUtils instance;


    public static AdviceUtils getInstances() {
        if (instance == null) {
            synchronized (AdviceUtils.class) {
                if (instance == null) {
                    instance = new AdviceUtils();
                }
            }
        }
        return instance;
    }

    public List<AdviceBean> getAllClose(Context context) {
        //序厅区
        prefaces.clear();
        prefaces_compater.clear();

        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().deleteAll();

        areaData("preface.json", prefaces_compater, prefaces, context);

        //语音区
        voices.clear();
        voices_compater.clear();
        areaData("voice.json", voices_compater, voices, context);
        //视觉区
        visuals.clear();
        visuals_compater.clear();

        areaData("visual.json", visuals_compater, visuals, context);

        //生态区
        ecologys.clear();
        ecologys_compater.clear();
        areaData("ecology.json", ecologys_compater, ecologys, context);
        //尾厅区
        tails.clear();
        tails_compater.clear();
        areaData("tail.json", tails_compater, tails, context);
        //休闲区
        casuals.clear();
        casuals_compater.clear();
        areaData("casual.json", casuals_compater, casuals, context);
        //外立面
        outs.clear();
        outs_compater.clear();
        areaData("out.json", outs_compater, outs, context);
        //全部清除
        addall.clear();
        adviceBeans.clear();
        //清除后添加
        //这里是全部关机
        addall.addAll(prefaces);
        addall.addAll(visuals);
        addall.addAll(voices);
        addall.addAll(ecologys);
        addall.addAll(tails);
        addall.addAll(casuals);
        addall.addAll(outs);

        addall.addAll(prefaces_compater);
        addall.addAll(visuals_compater);
        addall.addAll(voices_compater);
        addall.addAll(ecologys_compater);
        addall.addAll(tails_compater);
        addall.addAll(casuals_compater);
        addall.addAll(outs_compater);

        QZXTools.logD("tianqin.....线程2。。关有电脑的灯33333333333333333333。。。ecologys_compater=" + ecologys_compater.size() + ".........." + ecologys_compater);

        //应该再这个类获取
        for (int i = 0; i < addall.size(); i++) {
            adviceBeans.add(addall.get(i));
        }
        return adviceBeans;
    }


    private void areaData(String json, List compater, List others, Context context) {
        List<AdviceBean> listInfo = NumUtil.getListInfo(context, json);
        for (int i = 0; i < listInfo.size(); i++) {
            AdviceBean adviceBean = listInfo.get(i);
            adviceBean.setIsOpen(false);

            if (adviceBean.getComputerList() != null && adviceBean.getComputerList().size() > 0) {
                compater.add(adviceBean);
            } else {
                others.add(adviceBean);
            }


            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
    }


    //这里是先电脑
    public void fistCompaterGames() {

        // 在这里关电脑
        if (prefaces_compater != null && prefaces_compater.size() > 0) {
            for (AdviceBean adviceBean : prefaces_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。先关电脑。。prefaces_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (visuals_compater != null && visuals_compater.size() > 0) {
            for (AdviceBean adviceBean : visuals_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。先关电脑。visuals_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (voices_compater != null && voices_compater.size() > 0) {
            for (AdviceBean adviceBean : voices_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。先关电脑。voices_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (ecologys_compater != null && ecologys_compater.size() > 0) {
            for (AdviceBean adviceBean : ecologys_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。。。。。。先关电脑。。ecologys_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (tails_compater != null && tails_compater.size() > 0) {
            for (AdviceBean adviceBean : tails_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。。。。。。。。。先关电脑。tails_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (casuals_compater != null && casuals_compater.size() > 0) {
            for (AdviceBean adviceBean : casuals_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。。。。。。。。。。先关电脑。。casuals_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
        if (outs_compater != null && outs_compater.size() > 0) {
            for (AdviceBean adviceBean : outs_compater) {
                for (AdviceBean.Computer computer : adviceBean.getComputerList()) {
                    String getIp = computer.getUrl();

                    if (TextUtils.isEmpty(getIp)) {
                        ToastUtils.show("ip和端口不能为空");
                        return;
                    }
                    QZXTools.logD("tianqin.....线程2。。。。。。。。。。先关电脑。outs_compater。。" + getIp);
                    QZXTools.moveAdevice(getIp);
                }

            }
        }
    }


    public List<AdviceBean> getOpenAll(Context context) {

        prefaces.clear();
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().deleteAll();
        List<AdviceBean> preface_listInfo = NumUtil.getListInfo(context, "preface.json");
        for (int i = 0; i < preface_listInfo.size(); i++) {
            AdviceBean adviceBean = preface_listInfo.get(i);
            adviceBean.setIsOpen(true);
            prefaces.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //视觉区
        visuals.clear();
        List<AdviceBean> visual_listInfo = NumUtil.getListInfo(context, "voice.json");
        for (int i = 0; i < visual_listInfo.size(); i++) {
            AdviceBean adviceBean = visual_listInfo.get(i);
            adviceBean.setIsOpen(true);
            visuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //语音区
        voices.clear();
        List<AdviceBean> voice_listInfo = NumUtil.getListInfo(context, "visual.json");
        for (int i = 0; i < voice_listInfo.size(); i++) {
            AdviceBean adviceBean = voice_listInfo.get(i);
            adviceBean.setIsOpen(true);
            voices.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //生态区
        ecologys.clear();
        List<AdviceBean> ecology_listInfo = NumUtil.getListInfo(context, "ecology.json");
        for (int i = 0; i < ecology_listInfo.size(); i++) {
            AdviceBean adviceBean = ecology_listInfo.get(i);
            adviceBean.setIsOpen(true);
            ecologys.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //尾厅区
        tails.clear();
        List<AdviceBean> tail_listInfo = NumUtil.getListInfo(context, "tail.json");
        for (int i = 0; i < tail_listInfo.size(); i++) {
            AdviceBean adviceBean = tail_listInfo.get(i);
            adviceBean.setIsOpen(true);
            tails.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //休闲区
        casuals.clear();
        List<AdviceBean> casual_listInfo = NumUtil.getListInfo(context, "casual.json");
        for (int i = 0; i < casual_listInfo.size(); i++) {
            AdviceBean adviceBean = casual_listInfo.get(i);
            adviceBean.setIsOpen(true);
            casuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //外立面
        outs.clear();

        List<AdviceBean> out_listInfo = NumUtil.getListInfo(context, "out.json");
        for (int i = 0; i < out_listInfo.size(); i++) {
            AdviceBean adviceBean = out_listInfo.get(i);
            adviceBean.setIsOpen(true);
            outs.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //这里是全部开机
        addall.clear();
        adviceBeans.clear();
        //清除后添加
        addall.addAll(prefaces);
        addall.addAll(visuals);
        addall.addAll(voices);
        addall.addAll(ecologys);
        addall.addAll(tails);

        addall.addAll(casuals);
        addall.addAll(outs);
        //应该再这个类获取
        for (int i = 0; i < addall.size(); i++) {
            adviceBeans.add(addall.get(i));
        }
        return adviceBeans;

    }


}
