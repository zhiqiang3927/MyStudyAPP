package com.qiang.lib.fun.frame.splash;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.frame.R;

public class SplashActivity extends BaseActivityByID {

    private ViewPager function_common_frame_splash_viewPager;
    private TabLayout function_common_frame_splash_tabLayout;

    @Override
    public int loadViewLayout() {
        return R.layout.function_common_frame_activity_splash_layout;
    }

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public void findViewById() {
        function_common_frame_splash_viewPager = findViewById(R.id.function_common_frame_splash_viewPager);
        function_common_frame_splash_tabLayout = findViewById(R.id.function_common_frame_splash_tabLayout);

    }

    @Override
    public void setListener() {

    }

    @Override
    public void viewParam() {

    }

    @Override
    public void onClickEvent(View view) {

    }
}
