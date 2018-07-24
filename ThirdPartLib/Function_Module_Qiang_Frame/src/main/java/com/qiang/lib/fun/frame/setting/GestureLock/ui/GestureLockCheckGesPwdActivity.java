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
 *      Date            ： 2018/7/5 12:50
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */
@Route(path = BaseARouterPath.path_fun_common_gesture_check)
public class GestureLockCheckGesPwdActivity extends BaseActivityByID {

    private TextView mTvTip;
    private GestureLockView mGlvSet;

    @Override
    public int loadViewLayout() {
        return R.layout.function_common_gesture_activity_check_pwd_layout;
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
        // 把之前设置好存起来的手势密码设置为当前页面的密码的key
        // 九个点分别对应着数字的  012345678  第一个点即为0，最后一个点为8，横向数起

        // 这里去出来的密码肯定是正常的，不会是 no
        mGlvSet.setKey(SPCacheUtils.getString(GestureLockCheckGesPwdActivity.this, BaseConst.GESTRUE_PWD_KEY, "no"));
        mGlvSet.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String gestureCode) {
                if (success) {
                    Toast.makeText(GestureLockCheckGesPwdActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    SPCacheUtils.setBoolean(GestureLockCheckGesPwdActivity.this, BaseConst.GESTRUE_HAS_INPUT_PWD, true);
                    finish();
                } else {
                    Toast.makeText(GestureLockCheckGesPwdActivity.this, "密码错误，错误10次地球将来10分钟后毁灭！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClickEvent(View view) {

    }
}
