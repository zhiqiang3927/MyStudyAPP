package com.qiang.lib.bus.news;

import android.support.annotation.Keep;
import android.view.View;

import com.qiang.lib.fun.common.base.view.BaseFragment;
import com.qiang.lib.fun.common.base.IViewDelegate;


/**
 * <pre>
 *      Date            ： 2018/7/4 17:24
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

@Keep
public class MyViewDelegate implements IViewDelegate {

    @Override
    public BaseFragment getFragment(String name) {
        return NewsFragment.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
