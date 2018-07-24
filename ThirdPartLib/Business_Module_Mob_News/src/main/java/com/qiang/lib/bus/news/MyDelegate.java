package com.qiang.lib.bus.news;

import android.support.annotation.Keep;

import com.qiang.lib.fun.common.base.MyViewManager;
import com.qiang.lib.fun.common.base.IApplicationDelegate;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:24
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
//        Logger.addLogAdapter(new LogAdapter() {
//            @Override
//            public boolean isLoggable(int priority, @Nullable String tag) {
//                return false;
//            }
//
//            @Override
//            public void log(int priority, @Nullable String tag, @NonNull String message) {
//
//            }
//        });
        //主动添加
        MyViewManager.getInstance().addFragment(0, NewsFragment.newInstance());
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
