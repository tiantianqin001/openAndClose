package com.telit.money.start.fragment;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.adapter.ZhanXiangAdapter;
import com.telit.money.start.bean.ZhXiangBean;
import com.telit.money.start.dialoge.ChangeIpDialog;
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
public class AllLightAndCloseFragment extends Fragment  {
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

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
