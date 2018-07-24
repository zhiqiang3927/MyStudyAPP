package com.qiang.lib.fun.frame;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.common.base.view.BaseFragment;
import com.qiang.lib.fun.common.base.BaseViewManager;
import com.qiang.lib.fun.common.base.ClassUtils;
import com.qiang.lib.fun.common.base.IViewDelegate;
import com.qiang.lib.fun.common.widget.NoScrollViewPager;

import java.util.List;


/**
 * <pre>
 *      Date            ： 2018/7/4 12:40
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Frame
 *      FunctionName    ： BottomNavigationActivity
 *      Deprecation     ：
 * </pre>
 */

public class BottomNavigationActivity extends BaseActivityByID {

    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;


    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    public void findViewById() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
    }

    @Override
    public void viewParam() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClickEvent(View view) {

    }

    private void initViewPager() {
        mFragments = BaseViewManager.getInstance().getAllFragment();//这几个Fragment是主动添加到ViewManager中的
        BaseFragment newsFragment = getNewsFragment();//主动寻找
        mFragments.add(newsFragment);
        mPager = (NoScrollViewPager) findViewById(R.id.container_pager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);
    }


    /**
     * 在News模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getNewsFragment() {
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.qiang.lib.bus.news");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getFragment("");
        }
        return newsFragment;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };

}
