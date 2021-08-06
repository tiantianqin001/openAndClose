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

public class EcologyFragment extends Fragment implements PrefaceAdapter.onClickListener {

    private RecyclerView rv_staile_content;

    private List<AdviceBean> adviceBeans = new ArrayList<>();
    @Override
    protected void addressInfo(List<XmlBean> xmlBeans) {
        super.addressInfo(xmlBeans);
        addressList.clear();
        for (XmlBean xmlBean : xmlBeans) {
            String area = xmlBean.getArea();
            if (area.equals("生态区")){
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
        AdviceBean adviceBean = new AdviceBean("ecology_one","灯带1路(地址1，第11路)",  "11","01",false);
        AdviceBean adviceBean1 = new AdviceBean( "ecology_two","轨道灯1路(地址1，第12路)", "12","01",false);
        AdviceBean adviceBean2 = new AdviceBean( "ecology_three","所有插座1路(世界聊得来+变声电话亭+AI生活、AI教育、AI城市+地插(地址2,第1路))", "1","02",false);


        adviceBeans.add(adviceBean);
        adviceBeans.add(adviceBean1);
        adviceBeans.add(adviceBean2);
        rv_staile_content.setLayoutManager(new LinearLayoutManager(getContext()));
        PrefaceAdapter prefaceAdapter = new PrefaceAdapter(getContext(), adviceBeans, "生态区");
        prefaceAdapter.setonClickListener(this);
        rv_staile_content.setAdapter(prefaceAdapter);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.staile_count;
    }

    @Override
    public void onClick(int position, String type, boolean isOpen,String adress) {
        if (type.equals("生态区")){
            String sendInfoAreess = NumUtil.getSendInfoAreess(position, adress, isOpen);
            QZXTools.logD(sendInfoAreess);
        }
    }
}
