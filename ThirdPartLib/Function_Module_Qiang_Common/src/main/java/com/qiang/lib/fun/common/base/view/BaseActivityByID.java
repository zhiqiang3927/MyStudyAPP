package com.qiang.lib.fun.common.base.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.qiang.lib.fun.common.R;
import com.qiang.lib.fun.common.base.BaseApplication;
import com.qiang.lib.fun.common.utils.MyScreenUtil;
import com.qiang.lib.fun.common.utils.MyUiUtils;


/**
 * <pre>
 *      Date            ： 2018/7/3 10:20
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： BaseActivityByID
 *      Deprecation     ： 基类BaseActivity不同代码方式的实现（布局ID）：支持竖屏、沉浸式设计、屏幕适配
 * </pre>
 */

public abstract class BaseActivityByID extends BaseActivity implements View.OnClickListener {

    /**
     * 头部导航Toolbar
     */
    public Toolbar function_common_toolbar;
    /**
     * 底部导航适配view
     */
    private View function_common_navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*屏幕适配*/
        MyScreenUtil.autoRotation(BaseApplication.getInstance(), 160*7);
        /*竖屏*/
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*沉浸式设计*/
        MyUiUtils.setTranslucentNavigation(this);
        setContentView(loadViewLayout());
        if (isLoadTopTab()) {
            loadTopTab();
        }
        /*沉浸式设计*/
        MyUiUtils.setOrChangeTranslucentColor(this, function_common_toolbar, function_common_navigation_view, getResources().getColor(R.color.colorPrimary));
        initView();
    }

    /**
     * 初始化头部布局，并设置返回监听
     */
    protected void loadTopTab() {
        //底部导航
        function_common_navigation_view = findViewById(R.id.function_common_navigation_view);
        //头部导航
        function_common_toolbar = findViewById(R.id.function_common_toolbar);
        if (function_common_toolbar != null) {
            setToolBar(function_common_toolbar);
        } else {
            Log.e("BaseActivityByID", "onClick(BaseActivityByID.java:66)" + "u need in layout xml include function_common_navigation_view.xml and function_common_layout_toolbar.xml");
        }
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        findViewById();
        viewParam();
        setListener();
    }

    @Override
    public void onClick(View view) {
        onClickEvent(view);
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
     */
    public abstract void findViewById();

    /**
     * 设置监听
     */
    public abstract void setListener();

    /**
     * 初始化数据
     */
    public abstract void viewParam();

    /**
     * 处理监听
     */
    public abstract void onClickEvent(View view);

}
