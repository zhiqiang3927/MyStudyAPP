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
@Route(path = BaseARouterPath.path_fun_common_gesture_set)
public class GestureLockSetGesPwdActivity extends BaseActivityByID {
    private TextView mTvTip;
    private GestureLockView mGlvSet;

    private String tempKey = null;
    private boolean hasInput = false;

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
        mGlvSet.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String gestureCode) {
                if (!hasInput) { // 如果当前还没有设置过第一次密码
                    tempKey = gestureCode;
                    hasInput = true;
                    mGlvSet.setUpDiyColor(true);
                    //Toast.makeText(GestureLockSetGesPwdActivity.this,"已设置一次，请重新设置第二次密码",Toast.LENGTH_SHORT).show();
                    mTvTip.setText("已设置一次，请重新设置第二次密码");
                } else {// 如果输入过第一次密码，那么做比较
                    if (tempKey.equals(gestureCode)) {
                        mGlvSet.setUpDiyColor(true);

                        // 把手势密码缓存起来
                        SPCacheUtils.setString(GestureLockSetGesPwdActivity.this, BaseConst.GESTRUE_PWD_KEY, gestureCode);
                        SPCacheUtils.setBoolean(GestureLockSetGesPwdActivity.this, BaseConst.GESTRUE_IS_LIVE, true);
                        Toast.makeText(GestureLockSetGesPwdActivity.this, "设置密码成功", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(GestureLockSetGesPwdActivity.this,GestureLockCheckGesPwdActivity.class));
                        finish();
                    } else {
                        mGlvSet.setUpDiyColor(false);
                        //Toast.makeText(GestureLockSetGesPwdActivity.this, "设置密码失败，请重新设置两次相同的密码", Toast.LENGTH_SHORT).show();
                        mTvTip.setText("两次密码不一致");
                        tempKey = "";
                    }
                    hasInput = false;
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
