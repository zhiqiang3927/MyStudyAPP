package com.qiang.lib.fun.common.base.view;

import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.qiang.lib.fun.common.R;
import com.qiang.lib.fun.common.base.BaseContextUtils;


/**
 * <pre>
 *      Date            ： 2018/7/4 11:23
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： BaseActivity
 *      Deprecation     ： 基础类BaseActivity：导航条的返回、BaseFragment的处理
 * </pre>
 */
@Keep
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 导航条
     */
    private ActionBar mActionBar;

    /**
     * 获取导航条，供子类加菜单
     * @return
     */
    public ActionBar getmActionBar() {
        return mActionBar;
    }

    /**
     * 导航条返回的处理
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * 设置 toolbar
     *
     * @param toolbar toolbar
     */
    protected void setToolBar(Toolbar toolbar) {
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.mipmap.function_common_back);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 设置头部标题
     */
    public void setTitle(int toolbar_center_titleID) {
        if (mActionBar != null) {
            mActionBar.setTitle(toolbar_center_titleID);
        } else {
            throw new UnsupportedOperationException("u need set isLoadTopTab() method return true");
        }
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        BaseContextUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        BaseContextUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 移除fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}