package com.qiang.lib.fun.frame.module;

import android.content.Intent;
import android.view.View;

import com.qiang.lib.fun.common.arouter.BaseARouterPath;
import com.qiang.lib.fun.common.arouter.MyARouterUtils;
import com.qiang.lib.fun.frame.BottomNavigationActivity;
import com.qiang.lib.fun.frame.R;
import com.qiang.lib.fun.frame.base.BaseMainActivityFragment;

public class MyTestFragment extends BaseMainActivityFragment {

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
        inflate.findViewById(R.id.news_button).setOnClickListener(this);
        inflate.findViewById(R.id.girls_button).setOnClickListener(this);
        inflate.findViewById(R.id.fragment_button).setOnClickListener(this);
        inflate.findViewById(R.id.GestureLock_button).setOnClickListener(this);
        inflate.findViewById(R.id.Dialog_button).setOnClickListener(this);
    }

    @Override
    protected void viewMainActivityFragmentParam() {

    }

    @Override
    protected void setMainActivityFragmentListener() {

    }

    @Override
    protected void onMainActivityFragmentClickEvent(View view) {
        if (view.getId() == R.id.news_button) {
            //跳转到NewsCenterActivity
            MyARouterUtils.startActivity("/news/center");
        } else if (view.getId() == R.id.girls_button) {
            //跳转到GirlsActivity
            MyARouterUtils.startActivity("/girls/list");
        } else if (view.getId() == R.id.Dialog_button) {
            MyARouterUtils.startActivity("/common/dialog");
        } else if (view.getId() == R.id.GestureLock_button) {
            //跳转到GestureLock
            MyARouterUtils.startActivity(BaseARouterPath.path_fun_common_frame_main);
        } else if (view.getId() == R.id.fragment_button) {
            startActivity(new Intent(getMyActivity(), BottomNavigationActivity.class));
        }
    }
}
