package com.qiang.lib.fun.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;



public class MyDensityUtil {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    /**
     * UI设计图的宽度是360的多少倍（dp）
     * @param application Application 引用
     * @param activity    Activity 引用
     */
    public static void setCustomDensity(@NonNull final Application application, @NonNull Activity activity) {
        final DisplayMetrics appDisplayMetrice = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrice.density;
            sNoncompatScaledDensity = appDisplayMetrice.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        /**
         px = density * dp;
         density = dpi / 160;
         px = dp * (dpi / 160);
         */

        final float targetDensity = appDisplayMetrice.widthPixels / 360f;
        final float targetscaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrice.density = targetDensity;
        appDisplayMetrice.scaledDensity = targetscaledDensity;
        appDisplayMetrice.densityDpi = targetDensityDpi;
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetscaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
