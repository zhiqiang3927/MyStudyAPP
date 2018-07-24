package com.qiang.lib.fun.common.utils;

import android.os.Handler;
import android.os.Looper;
/**
 * <pre>
 *      Date            ： 2018/7/24 16:02
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： 线程工具类
 * </pre>
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
