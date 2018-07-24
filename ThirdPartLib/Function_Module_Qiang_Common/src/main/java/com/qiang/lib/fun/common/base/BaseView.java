package com.qiang.lib.fun.common.base;


import android.support.annotation.Keep;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:07
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： View接口的基类
 * </pre>
 */

@Keep
public interface BaseView<T> {

    void setPresenter(T presenter);

}
