package com.qiang.lib.fun.frame.base;

import android.view.View;

import com.qiang.lib.fun.common.base.view.BaseFragmentByID;

/**
 * 模块：
 * 功能：CommonWebActivity 包含的Fragment
 * 作者：周志强
 * 时间：2017年12月05日17时20分
 */
public abstract class BaseMainActivityFragment extends BaseFragmentByID {
    @Override
    public int loadViewLayout() {
        return initMainActivityFragmentLayout();
    }

    @Override
    public boolean isLoadTopTab() {
        return getMainActivityFragmentLoadTopTab();
    }

    @Override
    public void findViewById(View inflate) {
        findMainActivityFragmentViewById(inflate);
    }


    @Override
    public void viewParam(View inflate) {
        viewMainActivityFragmentParam();
    }


    @Override
    public void setListener() {
        setMainActivityFragmentListener();
    }


    @Override
    public void onClickEvent(View paramView) {
        onMainActivityFragmentClickEvent(paramView);
    }

    protected abstract boolean getMainActivityFragmentLoadTopTab();
    protected abstract int initMainActivityFragmentLayout();
    protected abstract void findMainActivityFragmentViewById(View inflate);
    protected abstract void viewMainActivityFragmentParam();
    protected abstract void setMainActivityFragmentListener();
    protected abstract void onMainActivityFragmentClickEvent(View paramView);
}
