package com.qiang.lib.fun.common.base.view;

import android.os.Bundle;
import android.view.View;

import com.qiang.lib.fun.common.base.BaseApplication;
import com.qiang.lib.fun.common.utils.MyScreenUtil;


/**
 * <pre>
 *      Date            ： 2018/7/3 10:20
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： BaseActivityByView
 *      Deprecation     ： 基类BaseActivity不同代码方式的实现（布局View）：支持竖屏、屏幕适配、标题栏
 * </pre>
 */

public abstract class BaseActivityByView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*屏幕适配*/
        MyScreenUtil.autoRotation(BaseApplication.getInstance(), 160*7);
        /*竖屏*/
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(loadViewLayout());
        if (isLoadTopTab()) {
            loadTopTab();
        }
        initView();
    }

    /**
     * 初始化头部布局，并设置返回监听
     */
    protected void loadTopTab() {
        setToolBar(null);
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        viewParam();
    }

    /**
     * 布局文件View
     */
    public abstract View loadViewLayout();

    /**
     * 是否加载头部导航
     */
    public abstract boolean isLoadTopTab();

    /**
     * 初始化数据
     */
    public abstract void viewParam();

}
