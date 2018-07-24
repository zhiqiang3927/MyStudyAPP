package com.qiang.lib.fun.common.base;


import android.support.annotation.Keep;
import android.view.View;

import com.qiang.lib.fun.common.base.view.BaseFragment;

/**
 * <p>类说明</p>
 *
 * @author 周志强 2018/1/4 22:10
 * @version V2.8.3
 * @name IFragmentDelegate
 */
/**
 * <pre>
 *      Date            ： 2018/7/4 10:33
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： IViewDelegate
 *      Deprecation     ： View的委托类：BaseFragment
 * </pre>
 */
@Keep
public interface IViewDelegate {

    BaseFragment getFragment(String name);

    View getView(String name);

}
