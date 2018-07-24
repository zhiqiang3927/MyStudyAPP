package com.qiang.lib.bus.alarmClock.girl;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.bus.alarmClock.Constants;
import com.qiang.lib.bus.alarmClock.data.bean.Girls;
import com.qiang.lib.fun.common.base.view.BaseActivityByView;
import com.qiang.lib.fun.common.widget.HackyViewPager;

import java.util.List;

/**
 * <pre>
 *      Date            ： 2018/7/4 12:40
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Business_Module_Qiang_AlarmClock
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */
@Route(path = "/girls/detail")
public class GirlActivity extends BaseActivityByView {

    private HackyViewPager viewPager;

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public View loadViewLayout() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        viewPager = new HackyViewPager(this);
        return viewPager;

    }

    @Override
    public void viewParam() {
        if (getIntent() != null) {
            List<Girls> mData = getIntent().getParcelableArrayListExtra(Constants.INTENT_GIRLS);
            int mCurrentIndex = getIntent().getIntExtra(Constants.INTENT_INDEX, 0);
            GirlAdapter adapter = new GirlAdapter(this, mData);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(mCurrentIndex);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

}
