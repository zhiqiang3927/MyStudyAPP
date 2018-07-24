package com.qiang.lib.fun.common.net.okhttp;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *      Date            ： 2018/7/4 13:21
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： DataTypeOfServerBack
 *      Deprecation     ： 服务端响应的数据类型
 * </pre>
 */

public class DataTypeOfServerBack {

    /*返回数据为String*/
    public static final int STRING = 1;
    /*返回数据为xml类型*/
    public static final int XML = 2;
    /*返回数据为json对象*/
    public static final int JSON_OBJECT = 3;
    /*返回数据为json数组*/
    public static final int JSON_ARRAY = 4;

    /**
     * 自定义一个播放器状态注解
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STRING, XML, JSON_OBJECT, JSON_ARRAY})
    public @interface Type {
    }

}
