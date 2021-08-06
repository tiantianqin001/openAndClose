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

public class VisualFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;

    private List<AdviceBean> adviceBeans = new ArrayList<>();
    @Override
    protected void addressInfo(List<XmlBean> xmlBeans) {
        super.addressInfo(xmlBeans);
        addressList.clear();
        for (XmlBean xmlBean : xmlBeans) {
            String area = xmlBean.getArea();
            if (area.equals("视觉区")){
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
        AdviceBean adviceBean = new AdviceBean("visual_one","灯带1路(地址1，第6路)",  "6","01",false);
        AdviceBean adviceBean1 = new AdviceBean( "visual_two","所有插座1路(追光音乐+动感火柴人+CV专项+智能导览机器人插座(地址1，第7路))", "7","01",false);
        AdviceBean adviceBean2 = new AdviceBean("visual_three","顶部软膜灯1路(地址1,第8路)",  "8","01",false);

        adviceBeans.add(adviceBean);
        adviceBeans.add(adviceBean1);
        adviceBeans.add(adviceBean2);




        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "视觉区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int position, String type, boolean isOpen,String adress) {

        if (type.equals("视觉区")){
            String sendInfoAreess = NumUtil.getSendInfoAreess(position, adress, isOpen);
            QZXTools.logD(sendInfoAreess);
        }
    }
}
