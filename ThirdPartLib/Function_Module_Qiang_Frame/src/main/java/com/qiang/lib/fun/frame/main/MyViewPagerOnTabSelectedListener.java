package com.qiang.lib.fun.frame.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

/**
 * 模块：
 * 功能：
 * 作者：周志强
 * 时间：2018年05月31日16时33分
 */
public class MyViewPagerOnTabSelectedListener extends TabLayout.ViewPagerOnTabSelectedListener {
    private Context context;
    public MyViewPagerOnTabSelectedListener(Context context, ViewPager viewPager) {
        super(viewPager);
        this.context = context;
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        super.onTabReselected(tab);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        super.onTabSelected(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        super.onTabUnselected(tab);
    }
}
