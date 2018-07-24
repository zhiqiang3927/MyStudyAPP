package com.qiang.lib.fun.common.utils;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;
/**
 * <pre>
 *      Date            ： 2018/6/29 16:44
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      ModuleName      ： 公共模块
 *      FunctionName    ： 屏幕适配
 *      Deprecation     ： 以设计图的宽度为标准适配
 * </pre>
 */
public class MyScreenUtil {
    private static float sNonCompatDensity;
    private static float sNonCompatScaledDensity;
    /**
     *
     * @param context
     * @param srcW  源 宽度
     * @param srcH  源 高度
     * @param srcDensity 源Density 源dpi 设置的越小，文本等越小
     */
    public static void autoRotation(final Context context, float srcW, float srcH, float srcDensity){
        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int rotation = manager.getDefaultDisplay().getRotation();
        if(rotation== Surface.ROTATION_0||rotation== Surface.ROTATION_180){
            setWidthDensity(context,srcW/srcDensity);
        }else{
            setWidthDensity(context,srcH/srcDensity);
        }
    }

    public static void autoRotation(final Activity context, float srcW, float srcH, float srcDensity){
        WindowManager manager= context.getWindowManager();
        int rotation = manager.getDefaultDisplay().getRotation();
        if(rotation== Surface.ROTATION_0||rotation== Surface.ROTATION_180){
            setWidthDensity(context,srcW/srcDensity);
        }else{
            setWidthDensity(context,srcH/srcDensity);
        }
    }

    /**
     * @param context
     * @param srcDpi  源dpi 源dpi 设置的越小，文本等越大
     */
    public static void autoRotation(Context context, float srcDpi){
        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int rotation = manager.getDefaultDisplay().getRotation();
        if(rotation== Surface.ROTATION_0||rotation== Surface.ROTATION_180){
            setWidthDensity(context,srcDpi);
        }else{
            setHeightDensity(context,srcDpi);
        }
    }

    /**
     * @param context
     * @param aimDpi 源dpi 设置的越小，文本等越大
     */
    public static void autoRotation(Activity context, float aimDpi){
        WindowManager manager= context.getWindowManager();
        int rotation = manager.getDefaultDisplay().getRotation();
        if(rotation== Surface.ROTATION_0||rotation== Surface.ROTATION_180){
            setWidthDensity(context,aimDpi);
        }else{
            setHeightDensity(context,aimDpi);
        }
    }


    /**
     * 固定屏幕方向的，根据屏幕的宽度来计算的！
     * @param context
     * @param wDpi
     */
    public static void setWidthDensity(final Context context, float wDpi) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (sNonCompatDensity == 0) {
            sNonCompatDensity = displayMetrics.density;
            sNonCompatScaledDensity = displayMetrics.scaledDensity;
            context.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConf) {
                    if (newConf != null && newConf.fontScale > 0) {
                        sNonCompatScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        displayMetrics.density = displayMetrics.widthPixels / wDpi;
        displayMetrics.scaledDensity = displayMetrics.density * (sNonCompatScaledDensity / sNonCompatDensity);
        displayMetrics.densityDpi = (int) (160 * displayMetrics.density);
    }

    /**
     * 固定屏幕方向的，根据屏幕的高度来计算的！
     * @param context
     * @param hDpi
     */
    public static void setHeightDensity(final Context context, float hDpi) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (sNonCompatDensity == 0) {
            sNonCompatDensity = displayMetrics.density;
            sNonCompatScaledDensity = displayMetrics.scaledDensity;
            context.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConf) {
                    if (newConf != null && newConf.fontScale > 0) {
                        sNonCompatScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        displayMetrics.density = displayMetrics.heightPixels / hDpi;
        displayMetrics.scaledDensity = displayMetrics.density * (sNonCompatScaledDensity / sNonCompatDensity);
        displayMetrics.densityDpi = (int) (160 * displayMetrics.density);
    }
}
