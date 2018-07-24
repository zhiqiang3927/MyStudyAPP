package com.qiang.lib.fun.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * <pre>
 *      Date            ： 2018/7/24 16:03
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： SP 工具类
 * </pre>
 */

public class SPCacheUtils {
    private final static String SP_NAME = "AM_GES_LOCK";
    private static SharedPreferences mPreferences;        // SharedPreferences的实例

    private static SharedPreferences getSp(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }

        return mPreferences;
    }

    /**
     * 通过SP获得boolean类型的数据，没有默认为false
     *
     * @param context : 上下文
     * @param key     : 存储的key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key, false);
    }

    /**
     * 通过SP获得boolean类型的数据，没有默认为false
     *
     * @param context  : 上下文
     * @param key      : 存储的key
     * @param defValue : 默认值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 设置int的缓存数据
     *
     * @param context
     * @param key     :缓存对应的key
     * @param value   :缓存对应的值
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();// 获取编辑器
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getInt(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getString(key, defValue);
    }

    /**
     * 设置int的缓存数据
     *
     * @param context
     * @param key     :缓存对应的key
     * @param value   :缓存对应的值
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();// 获取编辑器
        edit.putInt(key, value);
        edit.commit();
    }


    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();// 获取编辑器
        edit.putString(key, value);
        edit.commit();
    }


    public static void setInt(Context context, String key, String value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();// 获取编辑器
        edit.putString(key, value);
        edit.commit();
    }

}