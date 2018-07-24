package com.qiang.lib.fun.common.net.okhttp;

/**
 * <pre>
 *      Date            ： 2018/7/4 13:18
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： OnResultListener
 *      Deprecation     ： 在Retrofit中接口会导致泛型擦除，所以这里回调使用Class
 * </pre>
 */

public class OnResultListener<T> {

    /**
     * 请求成功
     *
     * @param result 需要解析的解析类
     */
    public void onSuccess(T result) {
    }

    /**
     * 响应成功，但是出错
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public void onError(int code, String message) {
    }

    /**
     * 请求失败
     *
     * @param message 失败信息
     */
    public void onFailure(String message) {
    }

}
