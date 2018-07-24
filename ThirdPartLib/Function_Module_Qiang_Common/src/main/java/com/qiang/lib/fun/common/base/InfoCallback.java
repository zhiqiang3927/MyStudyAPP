package com.qiang.lib.fun.common.base;

import android.support.annotation.Keep;


/**
 * <pre>
 *      Date            ： 2018/7/4 10:28
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： InfoCallback
 *      Deprecation     ： 数据回调接口：成功、失败
 * </pre>
 */
@Keep
public interface InfoCallback<T> {

    void onSuccess(T info);

    void onError(int code, String message);

}
