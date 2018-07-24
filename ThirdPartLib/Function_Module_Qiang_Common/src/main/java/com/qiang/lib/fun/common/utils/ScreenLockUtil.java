package com.qiang.lib.fun.common.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

import java.util.HashMap;

/**
 * <pre>
 *      Date            ： 2018/7/24 16:03
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： 用于保持屏幕高亮的工具
 * </pre>
 */

public class ScreenLockUtil {
    private static final String TAG = "ScreenLockUtil";

    private ScreenLockUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    static private HashMap<Activity, WakeLock> mWakeLockArray = new HashMap<>();
    static private HashMap<Activity, Boolean> mIsUnlockArray = new HashMap<>();


    /**
     * 保持屏幕常亮
     *
     * @param activity you know
     */
    public static void keepScreenOn(Activity activity) {
        WakeLock wakeLock = mWakeLockArray.get(activity);
        if (wakeLock == null) {
            PowerManager powerManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK,
                    activity.getClass().getName());
        }

        if (!wakeLock.isHeld()) {
            wakeLock.acquire();
        }

        mWakeLockArray.put(activity, wakeLock);

        cancelLockScreen(activity);

        Log.i(TAG, "开启屏幕常亮");
    }


    /**
     * 取消屏幕常亮
     *
     * @param activity you know
     */
    public static void cancelKeepScreen(Activity activity) {
        WakeLock wakeLock = mWakeLockArray.get(activity);
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                wakeLock.release();
            }
        }

        Log.i(TAG, "取消屏幕常亮");
    }

    /**
     * 取消锁屏限制
     *
     * @param activity you know
     */
    private static void cancelLockScreen(Activity activity) {
        Boolean isUnlock = mIsUnlockArray.get(activity);
        if (isUnlock != null && isUnlock) {
            return;
        }
        KeyguardManager mKeyguardManager = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardLock mKeyguardLock = mKeyguardManager.newKeyguardLock(activity.getClass().getName());
        mKeyguardLock.disableKeyguard();

        mIsUnlockArray.put(activity, true);
    }
}
