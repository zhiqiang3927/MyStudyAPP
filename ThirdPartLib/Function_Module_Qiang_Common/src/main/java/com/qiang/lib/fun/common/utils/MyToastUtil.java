package com.qiang.lib.fun.common.utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.qiang.lib.fun.common.base.BaseContextUtils;

import es.dmoral.toasty.Toasty;

/**
 * <pre>
 *      Date            ： 2018/7/5 15:57
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： MyToastUtil
 *      Deprecation     ： 全局Toast显示工具类，更多参考：https://github.com/GrenderG/Toasty
 * </pre>
 */

public class MyToastUtil {
    /**
     * 显示一个错误的Toast
     *
     * @param textID   文本ID
     * @param isCenter 是否居中显示
     */
    public static void showErrorToast(int textID, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.error(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID), Toast.LENGTH_LONG, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个错误的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     */
    public static void showErrorToast(CharSequence charSequence, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.error(BaseContextUtils.getContext(), charSequence, Toast.LENGTH_LONG, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个成功的Toast
     *
     * @param textID   文本ID
     * @param isCenter 是否居中显示
     */
    public static void showSuccessToast(int textID, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.success(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID), Toast.LENGTH_SHORT, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个成功的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     */
    public static void showSuccessToast(CharSequence charSequence, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.success(BaseContextUtils.getContext(), charSequence, Toast.LENGTH_SHORT, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个信息的Toast
     *
     * @param textID   文本ID
     * @param isCenter 是否居中显示
     */
    public static void showInfoToast(int textID, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.info(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID), Toast.LENGTH_SHORT, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个信息的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     */
    public static void showInfoToast(CharSequence charSequence, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.info(BaseContextUtils.getContext(), charSequence, Toast.LENGTH_SHORT, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个警告的Toast
     *
     * @param textID   文本ID
     * @param isCenter 是否居中显示
     */
    public static void showWarningToast(int textID, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.warning(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID), Toast.LENGTH_LONG, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个警告的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     */
    public static void showWarningToast(CharSequence charSequence, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.warning(BaseContextUtils.getContext(), charSequence, Toast.LENGTH_LONG, true);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个普通的Toast
     *
     * @param textID   文本ID
     * @param isCenter 是否居中显示
     */
    public static void showNormalToast(int textID, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.normal(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID));
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个普通的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     */
    public static void showNormalToast(CharSequence charSequence, boolean isCenter) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.normal(BaseContextUtils.getContext(), charSequence);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个自定义的Toast
     *
     * @param textID     文本ID
     * @param isCenter   是否居中显示
     * @param icon       提示图标
     * @param tintColor  背景色
     * @param duration   时长
     * @param withIcon   是否显示图标
     * @param shouldTint 是否背景色
     */
    public static void showCustomToast(int textID, boolean isCenter, Drawable icon, int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.custom(BaseContextUtils.getContext(), BaseContextUtils.getContext().getResources().getString(textID), icon, tintColor, duration, withIcon, shouldTint);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示一个自定义的Toast
     *
     * @param charSequence 文本
     * @param isCenter     是否居中显示
     * @param icon         提示图标
     * @param tintColor    背景色
     * @param duration     时长
     * @param withIcon     是否显示图标
     * @param shouldTint   是否背景色
     */
    public static void showCustomToast(CharSequence charSequence, boolean isCenter, Drawable icon, int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toasty.custom(BaseContextUtils.getContext(), charSequence, icon, tintColor, duration, withIcon, shouldTint);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        });
    }
}
