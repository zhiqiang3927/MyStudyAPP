package com.qiang.lib.fun.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.qiang.lib.fun.common.R;

/**
 * <pre>
 *      Date            ： 2018/7/5 21:20
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： MyUiUtils
 *      Deprecation     ： 沉浸式设计
 * </pre>
 */

public class MyUiUtils {
    public static void setTranslucentNavigation(Activity activity) {
        if (MySystemUtils.is_Over_KITKAT_and_low_LOLLIPOP()) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static void setOrChangeTranslucentColor(Activity activity, Toolbar toolbar, View bottomNavigationBar, int translucentPrimaryColor) {
        //判断版本如果[4.4,5.0),就设置状态栏和导航栏为透明
        if (MySystemUtils.is_Over_KITKAT_and_low_LOLLIPOP()) {
            if (toolbar != null) {
                //1.先设置Toolbar高度
                ViewGroup.LayoutParams params = toolbar.getLayoutParams();
                int statusBarHeight = getStatusBarHeight(activity);
                params.height += statusBarHeight;
                toolbar.setLayoutParams(params);
                //2.设置paddingTop,已达到状态栏不遮挡Toolbar内容
                toolbar.setPadding(
                        toolbar.getPaddingLeft(),
                        toolbar.getPaddingTop() + getStatusBarHeight(activity),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
                //设置顶部的颜色
                toolbar.setBackgroundColor(translucentPrimaryColor);
            }
            if (bottomNavigationBar != null) {
                //解决低版本4.4+ 的虚拟导航栏的
                if (hasNavigationBarShow(activity.getWindowManager())) {
                    ViewGroup.LayoutParams p = bottomNavigationBar.getLayoutParams();
                    p.height += getNavigationBarHeight(activity);
                    bottomNavigationBar.setLayoutParams(p);
                    //设置底部导航栏颜色
                    bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
                } else {
                    bottomNavigationBar.setVisibility(View.GONE);
                }
            }
        } else if (MySystemUtils.is_Over_LOLLIPOP()) {
            if (bottomNavigationBar != null) {
                activity.getWindow().setNavigationBarColor(translucentPrimaryColor);
                activity.getWindow().setStatusBarColor(translucentPrimaryColor);
            } else {
                activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color.colorPrimary));
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
            }

        } else {
            //<4.4的不做处理
        }
    }


    /**
     * 获取虚拟导航的高度
     */
    public static int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(context, "navigation_bar_height");
    }

    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        // 反射手机运行的类 android.R.dimen.status_bar_height.
        return getSystemComponentDimen(context, "status_bar_height");
    }

    private static int getSystemComponentDimen(Context context, String dimenName) {
        // 反射手机运行的类 android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @SuppressLint("NewApi")
    private static boolean hasNavigationBarShow(WindowManager wm) {
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        //获取内容展示部分的高度
        outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int heightPixels2 = outMetrics.heightPixels;
        int widthPixels2 = outMetrics.widthPixels;
        int w = widthPixels - widthPixels2;
        int h = heightPixels - heightPixels2;
        return w > 0 || h > 0;//横屏和竖屏的两种情况
    }

    public static void setFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        window.setAttributes(params);
    }

    /**
     * 沉侵设计不需要toolbar
     */
    public static void setToolbarGone(Context context,Toolbar common_toolbar){
        if (MySystemUtils.is_Over_KITKAT_and_low_LOLLIPOP()) {
            common_toolbar.setTitle("");
            common_toolbar.setNavigationIcon(null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MyUiUtils.getStatusBarHeight(context));
            common_toolbar.setLayoutParams(layoutParams);
        } else {
            common_toolbar.setVisibility(View.GONE);
        }
    }

}
