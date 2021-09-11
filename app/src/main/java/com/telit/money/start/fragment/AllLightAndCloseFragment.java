package com.telit.money.start.fragment;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.AllLightCloseAndOpenAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 云盘微课
 * ******************************************************************
 */
public class AllLightAndCloseFragment extends Fragment implements View.OnClickListener {

    private TextView home_one_close;
    private TextView home_one_open;
    private RecyclerView rv_all_open_and_close;
    private AllLightCloseAndOpenAdapter adapter;


    /**
     * 创建新实例
     *
     * @return
     */
    public static AllLightAndCloseFragment newInstance() {
        AllLightAndCloseFragment fragment = new AllLightAndCloseFragment();
        return fragment;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_cloudmicrolecture;
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        home_one_close = (TextView) root.findViewById(R.id.home_one_close);
        home_one_open = (TextView) root.findViewById(R.id.home_one_open);
        rv_all_open_and_close = (RecyclerView) root.findViewById(R.id.rv_all_open_and_close);
    }

    @Override
    protected void initView() {
        super.initView();
    }



    @Override
    protected void initData() {
        super.initData();

    }
    @Override
    protected void initListener() {
        super.initListener();
        home_one_close.setOnClickListener(this);
        home_one_open.setOnClickListener(this);

    }
    @Override
    public void onDestroy() {
        if (adapter!=null){
            adapter.OnDestroy();
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_one_close:
                //全部关机
                getAllClose();
                break;
            case R.id.home_one_open:
                //全部开机
                getOpenAll();
                break;
        }
    }

    private List<AdviceBean> addall = new ArrayList<>();
    private List<AdviceBean> prefaces = new ArrayList<>();
    private List<AdviceBean> visuals = new ArrayList<>();
    private List<AdviceBean> voices = new ArrayList<>();
    private List<AdviceBean> ecologys = new ArrayList<>();
    private List<AdviceBean> tails = new ArrayList<>();
    private List<AdviceBean> casuals = new ArrayList<>();
    private List<AdviceBean> outs = new ArrayList<>();

    private List<AdviceBean> adviceBeans = new ArrayList<>();

    private void getOpenAll() {
        addall.clear();
        adviceBeans.clear();


        prefaces.clear();
        List<AdviceBean> preface_listInfo = NumUtil.getListInfo(getContext(), "preface.json");
        for (int i = 0; i < preface_listInfo.size(); i++) {
            AdviceBean adviceBean = preface_listInfo.get(i);
            adviceBean.setIsOpen(true);
            prefaces.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }



        //视觉区
        visuals.clear();
        List<AdviceBean> visual_listInfo = NumUtil.getListInfo(getContext(), "visual.json");
        for (int i = 0; i < visual_listInfo.size(); i++) {
            AdviceBean adviceBean = visual_listInfo.get(i);
            adviceBean.setIsOpen(true);
            visuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //语音区
        voices.clear();
        List<AdviceBean> voice_listInfo = NumUtil.getListInfo(getContext(), "voice.json");
        for (int i = 0; i < voice_listInfo.size(); i++) {
            AdviceBean adviceBean = voice_listInfo.get(i);
            adviceBean.setIsOpen(true);
            voices.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //生态区
        ecologys.clear();
        List<AdviceBean> ecology_listInfo = NumUtil.getListInfo(getContext(), "ecology.json");
        for (int i = 0; i < ecology_listInfo.size(); i++) {
            AdviceBean adviceBean = ecology_listInfo.get(i);
            adviceBean.setIsOpen(true);
            ecologys.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //尾厅区
        tails.clear();
        List<AdviceBean> tail_listInfo = NumUtil.getListInfo(getContext(), "tail.json");
        for (int i = 0; i < tail_listInfo.size(); i++) {
            AdviceBean adviceBean = tail_listInfo.get(i);
            adviceBean.setIsOpen(true);
            tails.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //休闲区
        casuals.clear();
        List<AdviceBean> casual_listInfo = NumUtil.getListInfo(getContext(), "casual.json");
        for (int i = 0; i < casual_listInfo.size(); i++) {
            AdviceBean adviceBean = casual_listInfo.get(i);
            adviceBean.setIsOpen(true);
            casuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //外立面
        outs.clear();

        List<AdviceBean> out_listInfo = NumUtil.getListInfo(getContext(), "out.json");
        for (int i = 0; i < out_listInfo.size(); i++) {
            AdviceBean adviceBean = out_listInfo.get(i);
            adviceBean.setIsOpen(true);
            outs.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }

        //这里是全部开机
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

        QZXTools.logE(adviceBeans.toString(),null);




        initAinm();
        rv_all_open_and_close.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AllLightCloseAndOpenAdapter(getActivity(),adviceBeans,true);


        rv_all_open_and_close.setAdapter(adapter);



    }
    private Handler handler=new Handler();



    //全部关机
    private void getAllClose() {
        addall.clear();
        adviceBeans.clear();
        //序厅区
        prefaces.clear();
        List<AdviceBean> preface_listInfo = NumUtil.getListInfo(getContext(), "preface.json");
        for (int i = 0; i < preface_listInfo.size(); i++) {
            AdviceBean adviceBean = preface_listInfo.get(i);
            prefaces.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //视觉区
        visuals.clear();
        List<AdviceBean> visual_listInfo = NumUtil.getListInfo(getContext(), "visual.json");
        for (int i = 0; i < visual_listInfo.size(); i++) {
            AdviceBean adviceBean = visual_listInfo.get(i);
            visuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //语音区
        voices.clear();
        List<AdviceBean> voice_listInfo = NumUtil.getListInfo(getContext(), "voice.json");
        for (int i = 0; i < voice_listInfo.size(); i++) {
            AdviceBean adviceBean = voice_listInfo.get(i);
            voices.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //生态区
        ecologys.clear();
        List<AdviceBean> ecology_listInfo = NumUtil.getListInfo(getContext(), "ecology.json");
        for (int i = 0; i < ecology_listInfo.size(); i++) {
            AdviceBean adviceBean = ecology_listInfo.get(i);
            ecologys.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //尾厅区
        tails.clear();
        List<AdviceBean> tail_listInfo = NumUtil.getListInfo(getContext(), "tail.json");
        for (int i = 0; i < tail_listInfo.size(); i++) {
            AdviceBean adviceBean = tail_listInfo.get(i);
            tails.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //休闲区
        casuals.clear();
        List<AdviceBean> casual_listInfo = NumUtil.getListInfo(getContext(), "casual.json");
        for (int i = 0; i < casual_listInfo.size(); i++) {
            AdviceBean adviceBean = casual_listInfo.get(i);
            casuals.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //外立面
        outs.clear();

        List<AdviceBean> out_listInfo = NumUtil.getListInfo(getContext(), "out.json");
        for (int i = 0; i < out_listInfo.size(); i++) {
            AdviceBean adviceBean = out_listInfo.get(i);
            outs.add(adviceBean);
            MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
        }
        //这里是全部开机
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

        QZXTools.logE(adviceBeans.toString(),null);

        initAinm();
        rv_all_open_and_close.setLayoutManager(new LinearLayoutManager(getContext()));
        AllLightCloseAndOpenAdapter adapter = new AllLightCloseAndOpenAdapter(getActivity(),adviceBeans,false);
        rv_all_open_and_close.setAdapter(adapter);
    }

    public void sendMessage(){
        QZXTools.logD("wobei diaoyong ke ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void initAinm() {
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.in_fade_right);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(5f);
        //为ListView设置LayoutAnimationController属性；
        rv_all_open_and_close.setLayoutAnimation(lac);
    }






}
