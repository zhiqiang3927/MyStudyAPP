package com.qiang.lib.fun.frame.module;

import android.view.View;

import com.qiang.lib.fun.frame.R;
import com.qiang.lib.fun.frame.base.BaseMainActivityFragment;

public class MyFocusFragment extends BaseMainActivityFragment {

    @Override
    protected boolean getMainActivityFragmentLoadTopTab() {
        return false;
    }

    @Override
    protected int initMainActivityFragmentLayout() {
        return R.layout.mytest_activity_main;
    }

    @Override
    protected void findMainActivityFragmentViewById(View inflate) {

    }

    @Override
    protected void viewMainActivityFragmentParam() {

    }

    @Override
    protected void setMainActivityFragmentListener() {

    }

    @Override
    protected void onMainActivityFragmentClickEvent(View paramView) {

    }
}
