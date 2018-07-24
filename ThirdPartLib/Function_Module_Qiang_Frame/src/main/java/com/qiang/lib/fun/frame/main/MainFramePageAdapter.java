package com.qiang.lib.fun.frame.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 模块：
 * 功能：
 * 作者：周志强
 * 时间：2018年05月24日17时32分
 */
public class MainFramePageAdapter extends FragmentPagerAdapter {
    private List<MainFrameItemEntity> mainFrameItemEntities;
    public MainFramePageAdapter(FragmentManager fm, List<MainFrameItemEntity> mainFrameItemEntities) {
        super(fm);
        this.mainFrameItemEntities = mainFrameItemEntities;
    }

    @Override
    public Fragment getItem(int position) {
        return mainFrameItemEntities.get(position).getBaseMainActivityFragment();
    }

    @Override
    public int getCount() {
        return mainFrameItemEntities.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mainFrameItemEntities.get(position).getTitleText();
//    }
}
