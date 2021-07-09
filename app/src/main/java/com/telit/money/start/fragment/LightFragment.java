package com.telit.money.start.fragment;

import com.telit.money.start.R;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 本地微课
 * ******************************************************************
 */
public class LightFragment extends Fragment  {





    /**
     * 创建新实例
     *
     * @return
     */
    public static LightFragment newInstance() {
       LightFragment fragment = new LightFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_localmicrolecture;
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
