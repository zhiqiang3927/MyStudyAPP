package com.qiang.lib.fun.common.arouter;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * <pre>
 *      Date            ： 2018/7/5 13:30
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： ARouter 跳转工具
 * </pre>
 */

public class MyARouterUtils {
    /**
     * 跳转到指定的 Activity
     * @param path Activity 路由地址
     */
    public static void startActivity(String path){
        ARouter.getInstance().build(path).navigation();
    }
//    public static void startActivity(String path){
//        ARouter.getInstance().build(path)
//                .withLong("key1", 666L)
//                .withString("key3", "888")
//                .withObject("key4", new Test("Jack", "Rose"))
//                .navigation();
//    }
}
