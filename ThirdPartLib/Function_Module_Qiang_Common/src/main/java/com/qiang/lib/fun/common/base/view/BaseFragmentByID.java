package com.qiang.lib.fun.common.base.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiang.lib.fun.common.R;
import com.qiang.lib.fun.common.base.BaseApplication;
import com.qiang.lib.fun.common.utils.MyScreenUtil;
/**
 * <pre>
 *      Date            ： 2018/7/20 11:10
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： 基类BaseFragment by id
 * </pre>
 */

public abstract class BaseFragmentByID extends BaseFragment implements View.OnClickListener {
    public Toolbar function_common_toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //屏幕适配
        MyScreenUtil.autoRotation(BaseApplication.getInstance(), 160 * 7);
        View inflate = inflater.inflate(loadViewLayout(), container, false);
        if (isLoadTopTab()) {
            loadTopTab(inflate);
        }
        initView(inflate);
        return inflate;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    protected void initView(View view) {
        findViewById(view);
        viewParam(view);
        setListener();
    }

    /**
     * 初始化头部布局
     *
     * @param inflate
     */
    protected void loadTopTab(View inflate) {
        //底部导航
        function_common_toolbar = inflate.findViewById(R.id.function_common_toolbar);

        if (function_common_toolbar != null) {
            setToolBar(function_common_toolbar);
        } else {
            Log.e("BaseFragmentByID", "onClick(BaseFragmentByID.java:66)" + "u need in layout xml include function_common_navigation_view.xml and function_common_layout_toolbar.xml");
        }
    }

    @Override
    public void onClick(View v) {
        onClickEvent(v);
    }

    /**
     * 布局文件ID
     */
    public abstract int loadViewLayout();

    /**
     * 是否加载头部导航
     */
    public abstract boolean isLoadTopTab();

    /**
     * 查找控件
     *
     * @param view
     */
    public abstract void findViewById(View view);

    /**
     * 设置监听
     */
    public abstract void setListener();

    /**
     * 初始化数据
     *
     * @param view
     */
    public abstract void viewParam(View view);

    /**
     * 处理监听
     */
    public abstract void onClickEvent(View view);
}
