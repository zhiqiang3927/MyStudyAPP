package com.qiang.lib.fun.common.base.conts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.qiang.lib.fun.common.arouter.BaseARouterPath;
import com.qiang.lib.fun.common.arouter.MyARouterUtils;
import com.qiang.lib.fun.common.base.BaseContextUtils;
import com.qiang.lib.fun.common.base.ClassUtils;
import com.qiang.lib.fun.common.base.ForegroundCallbacks;
import com.qiang.lib.fun.common.base.IApplicationDelegate;
import com.qiang.lib.fun.common.utils.CrashHandler;
import com.qiang.lib.fun.common.utils.SPCacheUtils;

import java.util.List;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:BaseContextUtils.getContext()，不允许其他写法；
 */

/**
 * <pre>
 *      Date            ： 2018/7/3 12:04
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： BaseApplication
 *      Deprecation     ： 最基础的应用程序类
 * </pre>
 */

public class BaseApplication extends MultiDexApplication {
    /**
     * BaseApplication Instance
     */
    private static BaseApplication mBaseApplication = null;

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }

    /**
     * 根包名
     */
    public static final String ROOT_PACKAGE = "com.qiang";

    /**
     * Application的委托类
     */
    private List<IApplicationDelegate> mAppDelegateList;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initCrash();
        initGlobalContext();
        initARouter();
        initForeground();
        initLog();
        initDelegate();
    }

    private void initCrash() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    /**
     * 获取依赖
     */
    private void initDelegate() {
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
    }

    /**
     * 获取全局Context
     */
    private void initGlobalContext() {
        //全局Context
        BaseContextUtils.init(this);
    }

    /**
     * Logger 日志
     */
    private void initLog() {
        //解决Android Studio 3.1.3版本合并同一个tag导致日志格式有变
        class MyLogcatLogStrategy extends LogcatLogStrategy {
            String[] logTag = {". ", " ."};
            int index = 0;
            @Override
            public void log(int priority, @Nullable String tag, @NonNull String message) {
                index = (index + 1) % 2;
                Log.println(priority, logTag[index] + tag, message);
            }
        }
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new MyLogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("QiangLog")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    /**
     * ali路由
     */
    private void initARouter() {
        if (BaseContextUtils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    /**
     * 应用程序前后台监听
     */
    private void initForeground() {
        ForegroundCallbacks.init(this);
        ForegroundCallbacks.get().addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                if (SPCacheUtils.getBoolean(getApplicationContext(), BaseConst.GESTRUE_IS_LIVE)) {
                    MyARouterUtils.startActivity(BaseARouterPath.path_fun_common_gesture_check);
                } else {

                }
            }

            @Override
            public void onBecameBackground() {
            }
        });
    }

    @Override
    public void onTerminate() {
        Log.e("BaseApplication", "onTerminate(BaseApplication.java:73)" + "onTerminate : app stop");
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        Log.e("BaseApplication", "onLowMemory(BaseApplication.java:82)" + "onLowMemory : app low memory");
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        Log.e("BaseApplication", "onTrimMemory(BaseApplication.java:91)" + "onTrimMemory : app memory clean");
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
}
