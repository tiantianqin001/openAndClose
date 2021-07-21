package com.telit.money.start.fragment;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.ZhanXiangAdapter;
import com.telit.money.start.bean.ZhXiangBean;
import com.telit.money.start.dialoge.ChangeIpDialog;
import com.telit.money.start.dialoge.UrlUpdateDialog;
import com.telit.money.start.utils.QZXTools;
import com.telit.money.start.utils.SharedPreferenceUtil;

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
public class ExhibitionItemFragment extends Fragment implements ZhanXiangAdapter.onClickListerner, ChangeIpDialog.onCallCallback {

    private RecyclerView rv_list_zan_tai;

    private List<ZhXiangBean> zhXiangBeans = new ArrayList<>();
    private String getIp;
    private String getPort;
    private String type;

    /**
     * 创建新实例
     *
     * @return
     */
    public static ExhibitionItemFragment newInstance() {
        ExhibitionItemFragment fragment = new ExhibitionItemFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_cloudmicrolecture;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rv_list_zan_tai = root.findViewById(R.id.rv_list_zan_tai);
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected void initData() {
        super.initData();
        zhXiangBeans.clear();

        ZhXiangBean zhXiangBean1 = new ZhXiangBean();
        zhXiangBean1.setNum(0);
        zhXiangBean1.setClose("第一展台关机");
        zhXiangBean1.setRebot("第一展台开机");
        zhXiangBeans.add(zhXiangBean1);

        ZhXiangBean zhXiangBean2 = new ZhXiangBean();
        zhXiangBean2.setNum(1);
        zhXiangBean2.setClose("第二展台关机");
        zhXiangBean2.setRebot("第二展台开机");
        zhXiangBeans.add(zhXiangBean2);

        ZhXiangBean zhXiangBean3 = new ZhXiangBean();
        zhXiangBean3.setNum(2);
        zhXiangBean3.setClose("第三展台关机");
        zhXiangBean3.setRebot("第三展台开机");
        zhXiangBeans.add(zhXiangBean3);

        ZhXiangBean zhXiangBean4 = new ZhXiangBean();
        zhXiangBean4.setNum(3);
        zhXiangBean4.setClose("第四展台关机");
        zhXiangBean4.setRebot("第四展台开机");
        zhXiangBeans.add(zhXiangBean4);
        ZhXiangBean zhXiangBean5 = new ZhXiangBean();
        zhXiangBean5.setNum(4);
        zhXiangBean5.setClose("第五展台关机");
        zhXiangBean5.setRebot("第五展台开机");
        zhXiangBeans.add(zhXiangBean5);

        ZhXiangBean zhXiangBean6 = new ZhXiangBean();
        zhXiangBean6.setNum(5);
        zhXiangBean6.setClose("第六展台关机");
        zhXiangBean6.setRebot("第六展台开机");
        zhXiangBeans.add(zhXiangBean6);

        ZhXiangBean zhXiangBean7 = new ZhXiangBean();
        zhXiangBean7.setNum(6);
        zhXiangBean7.setClose("第七展台关机");
        zhXiangBean7.setRebot("第七展台开机");
        zhXiangBeans.add(zhXiangBean7);

        ZhXiangBean zhXiangBean8 = new ZhXiangBean();
        zhXiangBean8.setNum(7);
        zhXiangBean8.setClose("第八展台关机");
        zhXiangBean8.setRebot("第八展台开机");
        zhXiangBeans.add(zhXiangBean8);

        ZhXiangBean zhXiangBean9 = new ZhXiangBean();
        zhXiangBean9.setNum(8);
        zhXiangBean9.setClose("第九展台关机");
        zhXiangBean9.setRebot("第九展台开机");
        zhXiangBeans.add(zhXiangBean9);

        ZhXiangBean zhXiangBean10 = new ZhXiangBean();
        zhXiangBean10.setNum(9);
        zhXiangBean10.setClose("第十展台关机");
        zhXiangBean10.setRebot("第十展台开机");
        zhXiangBeans.add(zhXiangBean10);


        ZhanXiangAdapter zhanXiangAdapter = new ZhanXiangAdapter(getActivity(), zhXiangBeans);
        rv_list_zan_tai.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_list_zan_tai.setAdapter(zhanXiangAdapter);
        zhanXiangAdapter.setOnCliclListener(this);
    }


    @Override
    protected void initListener() {
        super.initListener();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(int position, String type) {
        this.type = type;
        //展台的点击事件
       // ToastUtils.show(position + "......." + type);
        //判断这台ip  是不是已经设置
        getIp = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverIp" + position);
        getPort = SharedPreferenceUtil.getInstance(MyApplication.getInstance()).getString("serverPort" + position);
        if (TextUtils.isEmpty(getIp)) {
            //设置每一台设备的ip 地址
            setDeviceIp(position);
        } else {
            //处理设备
           QZXTools. moveAdevice(getIp, getPort, type);
        }


    }



    private void setDeviceIp(int position) {
        //进入修改服务和通讯IP界面
        ChangeIpDialog changeIpDialog = new ChangeIpDialog(position);
        changeIpDialog.show(getChildFragmentManager(), ChangeIpDialog.class.getSimpleName());
        changeIpDialog.setOnCallCallback(this);
    }


    @Override
    public void call(String ip, String port) {
        //处理设备
        QZXTools.moveAdevice(ip, port, type);
    }
}
