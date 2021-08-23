package com.telit.money.start.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.AllLightCloseAndOpenAdapter;
import com.telit.money.start.adapter.ZhanXiangAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.bean.ZhXiangBean;
import com.telit.money.start.dialoge.ChangeIpDialog;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;
import com.telit.money.start.utils.SharedPreferenceUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    protected List<XmlBean> prefaceList = new ArrayList<>();
    protected List<XmlBean> visualList = new ArrayList<>();
    protected List<XmlBean> voiceList = new ArrayList<>();
    protected List<XmlBean> ecologyList = new ArrayList<>();
    protected List<XmlBean> tailList = new ArrayList<>();
    protected List<XmlBean> casualList = new ArrayList<>();
    protected List<XmlBean> outList = new ArrayList<>();


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

        //解析数据
        try {
            InputStream inputStream = getContext().getAssets().open("address.xml");
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
    @Override
    protected void initListener() {
        super.initListener();
        home_one_close.setOnClickListener(this);
        home_one_open.setOnClickListener(this);

    }


    @Override
    public void onDestroy() {
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

    private List<List<AdviceBean>> addall = new ArrayList<>();
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
        //
        AdviceBean preface_one = new AdviceBean("序厅区","preface_one","筒灯1路(地址1,第1路)", "1","01",true);
        AdviceBean preface_two = new AdviceBean("序厅区","preface_two","展项+吊顶灯带1路(地址1,第2路)", "2","01",true);
        AdviceBean preface_three = new AdviceBean("序厅区","preface_three","环绕灯带1路(地址1，第3路)", "3","01",true);
        AdviceBean preface_fore = new AdviceBean("序厅区","preface_fore","所有插座1路(AI迎宾墙+墙体插座(地址1，第4路))", "4","01",true);
        AdviceBean preface_five = new AdviceBean("序厅区","preface_five","轨道灯1路(地址1，第5路)", "5","01",true);

        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_three);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_fore);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_five);

        prefaces.clear();
        prefaces.add(preface_one);
        prefaces.add(preface_two);
        prefaces.add(preface_three);
        prefaces.add(preface_fore);
        prefaces.add(preface_five);
        //视觉区
        AdviceBean visual_one = new AdviceBean("视觉区","visual_one","灯带1路(地址1，第6路)",  "6","01",true);
        AdviceBean visual_two = new AdviceBean( "视觉区","visual_two","所有插座1路(追光音乐+动感火柴人+CV专项+智能导览机器人插座(地址1，第7路))", "7","01",true);
        AdviceBean visual_three = new AdviceBean("视觉区","visual_three","顶部软膜灯1路(地址1,第8路)",  "8","01",true);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_three);
        visuals.clear();
        visuals.add(visual_one);
        visuals.add(visual_two);
        visuals.add(visual_three);
        //语音区
        AdviceBean voice_one = new AdviceBean("语音区","voice_one","轨道灯1路(地址1，第9路)",  "9","01",true);
        AdviceBean voice_two = new AdviceBean( "语音区","voice_two","所有插座1路(说好普通话+智能转写+AI互动魔墙(地址1，第10路))", "10","01",true);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(voice_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(voice_two);
        voices.clear();
        voices.add(voice_one);
        voices.add(voice_two);
        //生态区
        AdviceBean ecology_one = new AdviceBean("生态区","ecology_one","灯带1路(地址1，第11路)",  "11","01",true);
        AdviceBean ecology_two = new AdviceBean( "生态区","ecology_two","轨道灯1路(地址1，第12路)", "12","01",true);
        AdviceBean ecology_three = new AdviceBean( "生态区","ecology_three","所有插座1路(世界聊得来+变声电话亭+AI生活、AI教育、AI城市+地插(地址2,第1路))", "1","02",true);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_three);
        ecologys.clear();
        ecologys.add(ecology_one);
        ecologys.add(ecology_two);
        ecologys.add(ecology_three);
        //尾厅区
        AdviceBean tail_one = new AdviceBean("尾厅区","tail_one","轨道灯1路(地址2，第2路)",  "2","02",true);
        AdviceBean tail_two = new AdviceBean( "尾厅区","tail_two","灯带1路(地址2，第3路)", "3","02",true);
        AdviceBean tail_three = new AdviceBean( "尾厅区","tail_three","所有插座1路(留言墙+地插(地址2，第4路))", "4","02",true);
        AdviceBean tail_fore = new AdviceBean("尾厅区", "tail_fore","弱点箱1路(地址2，第5路)", "5","02",true);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_three);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_fore);
        tails.clear();
        tails.add(tail_one);
        tails.add(tail_two);
        tails.add(tail_three);
        tails.add(tail_fore);
        //休闲区
        AdviceBean casual_one = new AdviceBean("休闲区","casual_one","轨道灯1路(地址2，第6路)" , "6","02",true);
        AdviceBean casual_two = new AdviceBean( "休闲区","casual_two","灯带1路(地址2，第7路)", "7","02",true);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(casual_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(casual_two);
        casuals.clear();
        casuals.add(casual_one);
        casuals.add(casual_two);
        //外立面

        AdviceBean out_one = new AdviceBean("外立面","out_one","轨道灯+灯带1路(地址2，第8路)" , "8","02",true);
        outs.clear();
        outs.add(out_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(out_one);
        //这里是全部开机
        addall.add(prefaces);
        addall.add(visuals);
        addall.add(voices);
        addall.add(ecologys);
        addall.add(tails);

        addall.add(casuals);
        addall.add(outs);

        //应该再这个类获取
        for (int i = 0; i < addall.size(); i++) {
            for (int j = 0; j < addall.get(i).size(); j++) {
                adviceBeans.add(addall.get(i).get(j));
            }
        }

        QZXTools.logE(adviceBeans.toString(),null);




        addall.add(outs);
        rv_all_open_and_close.setLayoutManager(new LinearLayoutManager(getContext()));
        AllLightCloseAndOpenAdapter adapter = new AllLightCloseAndOpenAdapter(getActivity(),adviceBeans,true);

        rv_all_open_and_close.setAdapter(adapter);



    }

    //全部关机
    private void getAllClose() {
        addall.clear();
        adviceBeans.clear();
        //序厅区
        AdviceBean preface_one = new AdviceBean("序厅区","preface_one","筒灯1路(地址1,第1路)", "1","01",false);
        AdviceBean preface_two = new AdviceBean("序厅区","preface_two","展项+吊顶灯带1路(地址1,第2路)", "2","01",false);
        AdviceBean preface_three = new AdviceBean("序厅区","preface_three","环绕灯带1路(地址1，第3路)", "3","01",false);
        AdviceBean preface_fore = new AdviceBean("序厅区","preface_fore","所有插座1路(AI迎宾墙+墙体插座(地址1，第4路))", "4","01",false);
        AdviceBean preface_five = new AdviceBean("序厅区","preface_five","轨道灯1路(地址1，第5路)", "5","01",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_three);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_fore);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(preface_five);

        prefaces.clear();
        prefaces.add(preface_one);
        prefaces.add(preface_two);
        prefaces.add(preface_three);
        prefaces.add(preface_fore);
        prefaces.add(preface_five);
        //视觉区
        AdviceBean visual_one = new AdviceBean("视觉区","visual_one","灯带1路(地址1，第6路)",  "6","01",false);
        AdviceBean visual_two = new AdviceBean( "视觉区","visual_two","所有插座1路(追光音乐+动感火柴人+CV专项+智能导览机器人插座(地址1，第7路))", "7","01",false);
        AdviceBean visual_three = new AdviceBean("视觉区","visual_three","顶部软膜灯1路(地址1,第8路)",  "8","01",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(visual_three);
        visuals.clear();
        visuals.add(visual_one);
        visuals.add(visual_two);
        visuals.add(visual_three);
        //语音区
        AdviceBean voice_one = new AdviceBean("语音区","voice_one","轨道灯1路(地址1，第9路)",  "9","01",false);
        AdviceBean voice_two = new AdviceBean( "语音区","voice_two","所有插座1路(说好普通话+智能转写+AI互动魔墙(地址1，第10路))", "10","01",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(voice_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(voice_two);
        voices.clear();
        voices.add(voice_one);
        voices.add(voice_two);
        //生态区
        AdviceBean ecology_one = new AdviceBean("生态区","ecology_one","灯带1路(地址1，第11路)",  "11","01",false);
        AdviceBean ecology_two = new AdviceBean( "生态区","ecology_two","轨道灯1路(地址1，第12路)", "12","01",false);
        AdviceBean ecology_three = new AdviceBean( "生态区","ecology_three","所有插座1路(世界聊得来+变声电话亭+AI生活、AI教育、AI城市+地插(地址2,第1路))", "1","02",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(ecology_three);
        ecologys.clear();
        ecologys.add(ecology_one);
        ecologys.add(ecology_two);
        ecologys.add(ecology_three);
        //尾厅区
        AdviceBean tail_one = new AdviceBean("尾厅区","tail_one","轨道灯1路(地址2，第2路)",  "2","02",false);
        AdviceBean tail_two = new AdviceBean( "尾厅区","tail_two","灯带1路(地址2，第3路)", "3","02",false);
        AdviceBean tail_three = new AdviceBean( "尾厅区","tail_three","所有插座1路(留言墙+地插(地址2，第4路))", "4","02",false);
        AdviceBean tail_fore = new AdviceBean("尾厅区", "tail_fore","弱点箱1路(地址2，第5路)", "5","02",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_two);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_three);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(tail_fore);
        tails.clear();
        tails.add(tail_one);
        tails.add(tail_two);
        tails.add(tail_three);
        tails.add(tail_fore);
        //休闲区
        AdviceBean casual_one = new AdviceBean("休闲区","casual_one","轨道灯1路(地址2，第6路)" , "6","02",false);
        AdviceBean casual_two = new AdviceBean( "休闲区","casual_two","灯带1路(地址2，第7路)", "7","02",false);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(casual_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(casual_two);
        casuals.clear();
        casuals.add(casual_one);
        casuals.add(casual_two);
        //外立面
        AdviceBean out_one = new AdviceBean("外立面","out_one","轨道灯+灯带1路(地址2，第8路)" , "8","02",false);
        outs.clear();
        outs.add(out_one);
        MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(out_one);
        //这里是全部开机
        addall.add(prefaces);
        addall.add(visuals);
        addall.add(voices);
        addall.add(ecologys);
        addall.add(tails);
        addall.add(casuals);
        addall.add(outs);

        //应该再这个类获取
        for (int i = 0; i < addall.size(); i++) {
            for (int j = 0; j < addall.get(i).size(); j++) {
                adviceBeans.add(addall.get(i).get(j));
            }
        }

        QZXTools.logE(adviceBeans.toString(),null);


        rv_all_open_and_close.setLayoutManager(new LinearLayoutManager(getContext()));
        AllLightCloseAndOpenAdapter adapter = new AllLightCloseAndOpenAdapter(getActivity(),adviceBeans,false);
        rv_all_open_and_close.setAdapter(adapter);

        //设置所有的设备关机可以马上关，但是关灯也就是关电要等1分钟

        closeAdveces();
    }
    //关闭所有的电脑
    private void closeAdveces() {
        //序厅区
        for (int i = 0; i < prefaceList.size(); i++) {
            XmlBean xmlBean = prefaceList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //视觉区
        for (int i = 0; i < visualList.size(); i++) {
            XmlBean xmlBean = visualList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //语音区
        for (int i = 0; i < voiceList.size(); i++) {
            XmlBean xmlBean = voiceList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //生态区
        for (int i = 0; i < ecologyList.size(); i++) {
            XmlBean xmlBean = ecologyList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //尾厅区
        for (int i = 0; i < tailList.size(); i++) {
            XmlBean xmlBean = tailList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //休闲区
        for (int i = 0; i < casualList.size(); i++) {
            XmlBean xmlBean = casualList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }
        //外立面
        for (int i = 0; i < outList.size(); i++) {
            XmlBean xmlBean = outList.get(i);
            String getIp = xmlBean.getUrl();
            int getPort = xmlBean.getPort();
            QZXTools. moveAdevice(getIp, getPort, "关机");
        }

    }

    public void sendMessage(){
        QZXTools.logD("wobei diaoyong ke ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
