package com.qiang.lib.fun.common.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.qiang.lib.fun.common.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 *      Date            ： 2018/7/4 11:04
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： MyViewManager
 *      Deprecation     ： 全局管理Activity、BaseFragment
 * </pre>
 */

@Keep
public class MyViewManager {
    /**
     * 应用中的Activity
     */
    private static Stack<Activity> activityStack;
    /**
     * 应用中的BaseFragment
     */
    private static List<BaseFragment> fragmentList;

    /**
     * MyViewManager 实体
     */
    private static MyViewManager mMyViewManager = null;

    private MyViewManager() {
    }

    public static MyViewManager getInstance() {
        synchronized (MyViewManager.class) {
            if (mMyViewManager == null) {
                mMyViewManager = new MyViewManager();
            }
        }
        return mMyViewManager;
    }

    /**
     * 添加BaseFragment
     *
     * @param fragment BaseFragment
     */
    public void addFragment(BaseFragment fragment) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(fragment);
    }

    /**
     * 添加BaseFragment到指定位置
     *
     * @param index    位置
     * @param fragment BaseFragment
     */
    public void addFragment(int index, BaseFragment fragment) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(index, fragment);
    }

    /**
     * 获取指定位置的BaseFragment
     *
     * @param index 位置
     * @return BaseFragment
     */
    public BaseFragment getFragment(int index) {
        if (fragmentList != null) {
            return fragmentList.get(index);
        }
        return null;
    }

    /**
     * 获取所有的BaseFragment
     *
     * @return BaseFragment集合
     */
    public List<BaseFragment> getAllFragment() {
        if (fragmentList != null) {
            return fragmentList;
        }
        return null;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     *
     * @return activity
     */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishCurrentActivity() {
        Activity activity = activityStack.lastElement();
        finishTargetActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity activity
     */
    public void finishTargetActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定Class的Activity
     *
     * @param cls activity的class
     */
    public void finishClassActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishTargetActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束全部的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     *
     * @param context context
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            //System.exit(0);
        } catch (Exception e) {
            Log.e("ActivityManager", "app exit" + e.getMessage());
        }
    }
}
