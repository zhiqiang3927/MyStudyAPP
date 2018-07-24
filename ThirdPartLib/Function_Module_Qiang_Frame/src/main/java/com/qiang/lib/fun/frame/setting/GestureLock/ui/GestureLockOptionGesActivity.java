package com.qiang.lib.fun.frame.setting.GestureLock.ui;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.fun.common.arouter.BaseARouterPath;
import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.common.arouter.MyARouterUtils;
import com.qiang.lib.fun.frame.R;


/**
 * <pre>
 *      Date            ： 2018/7/5 11:47
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： 设置手势密码入口
 * </pre>
 */
@Route(path = BaseARouterPath.path_fun_common_gesture_opt)
public class GestureLockOptionGesActivity extends BaseActivityByID {
    private TextView mTvSetGes;
    private TextView mTvCleanGes;
    private String appGesKey;

    @Override
    public int loadViewLayout() {
        return R.layout.function_common_gesture_activity_option_layout;
    }

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public void findViewById() {
        mTvSetGes = (TextView) findViewById(R.id.mTvSetGes);
        mTvCleanGes = (TextView) findViewById(R.id.mTvCleanGes);
    }

    @Override
    public void viewParam() {

    }

    @Override
    public void setListener() {
        mTvSetGes.setOnClickListener(this);
        mTvCleanGes.setOnClickListener(this);
    }

    @Override
    public void onClickEvent(View view) {
        int i = view.getId();
        if (i == R.id.mTvSetGes) {
            MyARouterUtils.startActivity("/common/gesture/set");
        } else if (i == R.id.mTvCleanGes) {
            MyARouterUtils.startActivity("/common/gesture/del");
        }
    }
}
