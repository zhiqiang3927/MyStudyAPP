package com.qiang.lib.fun.common.base.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.qiang.lib.fun.common.base.BaseContextUtils;


/**
 * <p>Fragment的基类</p>
 *
 * @author 周志强
 * @name BaseFragment
 */
@Keep
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    /**
     * 导航条
     */
    private ActionBar mActionBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    /**
     * 获取宿主Activity
     *
     * @return BaseActivityByID
     */
    protected BaseActivity getMyActivity() {
        return mActivity;
    }

    /**
     * 设置 toolbar
     *
     * @param toolbar toolbar
     */
    protected void setToolBar(Toolbar toolbar) {
        if (toolbar != null) {
            setHasOptionsMenu(true);
            getMyActivity().setSupportActionBar(toolbar);
        }
        mActionBar = (getMyActivity()).getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(null);
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
        getMyActivity().addFragment(fragment, frameId);

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        BaseContextUtils.checkNotNull(fragment);
        getMyActivity().replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getMyActivity().hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getMyActivity().showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        BaseContextUtils.checkNotNull(fragment);
        getMyActivity().removeFragment(fragment);

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getMyActivity().popFragment();
    }

}
