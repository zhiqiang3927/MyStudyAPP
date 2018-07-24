package com.qiang.lib.fun.frame.setting.GestureLock.ui;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.fun.common.arouter.BaseARouterPath;
import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.common.base.BaseConst;
import com.qiang.lib.fun.common.utils.SPCacheUtils;
import com.qiang.lib.fun.frame.setting.GestureLock.lib.GestureLockView;
import com.qiang.lib.fun.frame.R;


/**
 * <pre>
 *      Date            ： 2018/7/5 12:51
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */
@Route(path = BaseARouterPath.path_fun_common_gesture_del)
public class GestureLockDelGesPwdActivity extends BaseActivityByID {

    private TextView mTvTip;
    private GestureLockView mGlvSet;

    @Override
    public int loadViewLayout() {
        return R.layout.function_common_gesture_activity_set_pwd_layout;
    }

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public void findViewById() {
        mTvTip = (TextView) findViewById(R.id.mTvTip);
        mGlvSet = (GestureLockView) findViewById(R.id.mGlvSet);
    }

    @Override
    public void viewParam() {
    }

    @Override
    public void setListener() {
        mGlvSet.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String gestureCode) {
                String liveGesPwd = SPCacheUtils.getString(GestureLockDelGesPwdActivity.this, BaseConst.GESTRUE_PWD_KEY, "no");
                if (gestureCode.equals(liveGesPwd)) {
                    Toast.makeText(GestureLockDelGesPwdActivity.this, "验证通过，密码已经删除", Toast.LENGTH_SHORT).show();
                    SPCacheUtils.setBoolean(GestureLockDelGesPwdActivity.this, BaseConst.GESTRUE_IS_LIVE, false);
                    finish();
                } else {
                    Toast.makeText(GestureLockDelGesPwdActivity.this, "密码错误，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickEvent(View view) {
    }
}
