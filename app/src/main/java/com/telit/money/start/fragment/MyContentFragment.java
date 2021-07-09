package com.telit.money.start.fragment;

import android.os.Bundle;

import android.view.View;


import androidx.viewpager.widget.ViewPager;

import com.telit.money.start.R;
import com.telit.money.start.adapter.ViewPagerFragmentAdapter;
import com.telit.money.start.customview.SlidingTabLayout;
import com.telit.money.start.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 我的微课
 * ******************************************************************
 */
public class MyContentFragment extends Fragment implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    SlidingTabLayout stl_microLectureMy;

    ViewPager vp_microLectureMy;



    private ViewPagerFragmentAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private LightFragment localMicroLectureFragment;


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        stl_microLectureMy=  root.findViewById(R.id.stl_microLectureMy);
        vp_microLectureMy=  root.findViewById(R.id.vp_microLectureMy);

    }

    /**
     * 创建新实例
     *
     * @return
     */
    public static MyContentFragment newInstance() {
        MyContentFragment fragment = new MyContentFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mymicrolecture;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        titles.add("展台");
        titles.add("灯光");

        fragments.add(ExhibitionItemFragment.newInstance());
        localMicroLectureFragment = LightFragment.newInstance();
        fragments.add(localMicroLectureFragment);
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();


        adapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), getActivity(), fragments, titles);
        vp_microLectureMy.setAdapter(adapter);

        stl_microLectureMy.setViewPager(vp_microLectureMy);
    }

    @Override
    protected void initListener() {
        super.initListener();
        stl_microLectureMy.setOnTabSelectListener(this);
        vp_microLectureMy.setOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        vp_microLectureMy.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        stl_microLectureMy.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
