package com.qiang.lib.fun.common.base;

import android.support.annotation.Keep;

/**
 * <pre>
 *      Date            ： 2018/7/4 10:24
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： IApplicationDelegate
 *      Deprecation     ： Application的委托类
 * </pre>
 */

@Keep
public interface IApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
