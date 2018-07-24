package com.qiang.lib.fun.common.utils;

import android.os.Build;

/**
 * 模块：系统工具
 * 功能：
 * 作者：周志强
 * 时间：2018年05月29日17时46分
 */
public class MySystemUtils {
    /**
     * 系统版本范围：[4.4,5.0)
     */
    public static boolean is_Over_KITKAT_and_low_LOLLIPOP(){
        return  Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 系统版本范围：[5.0,)
     */
    public static boolean is_Over_LOLLIPOP(){
        return  Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
