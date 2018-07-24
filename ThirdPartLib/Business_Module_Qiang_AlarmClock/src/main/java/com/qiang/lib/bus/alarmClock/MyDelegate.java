package com.qiang.lib.bus.alarmClock;

import android.support.annotation.Keep;

import com.qiang.lib.fun.common.base.IApplicationDelegate;
import com.qiang.lib.fun.common.base.BaseViewManager;


/**
 * <pre>
 *      Date            ： 2018/7/4 17:26
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

@Keep
public class MyDelegate implements IApplicationDelegate {

    @Override
    public void onCreate() {
        //主动添加
        BaseViewManager.getInstance().addFragment(0, GirlsFragment.newInstance());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
