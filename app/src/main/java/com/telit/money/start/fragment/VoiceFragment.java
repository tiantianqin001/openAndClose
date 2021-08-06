package com.telit.money.start.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.R;
import com.telit.money.start.adapter.PrefaceAdapter;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.util.ArrayList;
import java.util.List;

public class VoiceFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;

    private List<AdviceBean> adviceBeans = new ArrayList<>();
    @Override
    protected void addressInfo(List<XmlBean> xmlBeans) {
        super.addressInfo(xmlBeans);
        addressList.clear();
        for (XmlBean xmlBean : xmlBeans) {
            String area = xmlBean.getArea();
            if (area.equals("语音区")){
                addressList.add(xmlBean);
            }
        }
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_staile_content = root.findViewById(R.id.rv_staile_content);
    }


    @Override
    protected void initData() {
        super.initData();
        AdviceBean adviceBean = new AdviceBean("voice_one","轨道灯1路(地址1，第9路)",  "9","01",false);
        AdviceBean adviceBean1 = new AdviceBean( "voice_two","所有插座1路(说好普通话+智能转写+AI互动魔墙(地址1，第10路))", "10","01",false);


        adviceBeans.add(adviceBean);
        adviceBeans.add(adviceBean1);

        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "语音区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int position, String type, boolean isOpen,String adress) {
        if (type.equals("语音区")){
            String sendInfoAreess = NumUtil.getSendInfoAreess(position, adress, isOpen);
            QZXTools.logD(sendInfoAreess);
        }
    }
}
