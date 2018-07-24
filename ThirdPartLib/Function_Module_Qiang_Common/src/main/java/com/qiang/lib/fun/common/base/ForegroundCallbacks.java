package com.qiang.lib.fun.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *      Date            ： 2018/7/3 15:59
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： ForegroundCallbacks
 *      Deprecation     ： activity 前后台的监听
 * </pre>
 */

public class ForegroundCallbacks implements Application.ActivityLifecycleCallbacks {
    public static final long CHECK_DELAY = 500;
    private static ForegroundCallbacks instance;
    private boolean foreground = false, paused = true;
    private Handler handler = new Handler();
    private List<Listener> listeners = new CopyOnWriteArrayList<>();
    private Runnable runnable;

    public static ForegroundCallbacks init(Application application) {
        if (instance == null) {
            instance = new ForegroundCallbacks();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Context context) {
        if (instance == null) {
            Context appCtx = context.getApplicationContext();
            if (appCtx instanceof Application) {
                init((Application) appCtx);
            }
            throw new IllegalStateException(
                    "Foreground is not initialised and " +
                            "cannot obtain the Application object");
        }
        return instance;
    }

    public static ForegroundCallbacks get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - invoke " +
                            "at least once with parameterised init/getData");
        }
        return instance;
    }

    public boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }


    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        boolean wasBackground = !foreground;
        foreground = true;
        if (runnable != null)
            handler.removeCallbacks(runnable);
        if (wasBackground) {
            Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "went foreground");
            for (Listener l : listeners) {
                try {
                    l.onBecameForeground();
                } catch (Exception exc) {
                    Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "Listener threw exception!:" + exc.toString());
                }
            }
        } else {
            Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "still foreground");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "went background");
                    for (Listener l : listeners) {
                        try {
                            l.onBecameBackground();
                        } catch (Exception exc) {
                            Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "Listener threw exception!:" + exc.toString());
                        }
                    }
                } else {
                    Log.e("ForegroundCallbacks", "onActivityResumed(ForegroundCallbacks.java:90)" + "still foreground");
                }
            }
        }, CHECK_DELAY);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.e("ForegroundCallbacks","onActivityCreated(ForegroundCallbacks.java:130)"+"onActivityCreated");
        MyViewManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e("ForegroundCallbacks", "onActivityStarted(ForegroundCallbacks.java:136)" + "onActivityStarted");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e("ForegroundCallbacks", "onActivityStopped(ForegroundCallbacks.java:141)" + "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e("ForegroundCallbacks", "onActivitySaveInstanceState(ForegroundCallbacks.java:146)" + "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e("ForegroundCallbacks", "onActivityDestroyed(ForegroundCallbacks.java:151)" + "onActivityDestroyed");
        MyViewManager.getInstance().finishTargetActivity(activity);
    }

    /**
     * 单个 activity 添加监听
     * 在 onCreate 方法中调用
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * 单个 activity 解除监听
     * 在 onDestroy 方法中调用
     */
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onBecameForeground();

        void onBecameBackground();
    }
}
