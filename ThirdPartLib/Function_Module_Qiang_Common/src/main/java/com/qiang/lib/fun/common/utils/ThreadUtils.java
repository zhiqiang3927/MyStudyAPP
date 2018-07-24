package com.qiang.lib.fun.common.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * 模块：线程工具类
 * 功能：
 * 作者：李海
 * 时间：2016年11月03日13时44分
 */
public class ThreadUtils {

    /**
     * 代码运行在子线程
     * @param runnable
     */
    public static void runInSubThread(Runnable runnable){
        new Thread(runnable).start();
    }

    /**
     * 代码运行在主线程
     * @param runnable
     */
    public static void runInUiThread(Runnable runnable){
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
