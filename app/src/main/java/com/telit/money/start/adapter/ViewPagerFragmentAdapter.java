package com.telit.money.start.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;


import com.telit.money.start.fragment.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ***************************************************************************
 * author: 刘玉
 * time: 2016/8/27 8:40
 * name:
 * overview:
 * usage:viewPager的适配器
 * ***************************************************************************
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Context context;

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerFragmentAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
