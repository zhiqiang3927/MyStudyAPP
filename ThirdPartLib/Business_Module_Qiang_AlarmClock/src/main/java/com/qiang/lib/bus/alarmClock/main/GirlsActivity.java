package com.qiang.lib.bus.alarmClock.main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.bus.alarmClock.R;
import com.qiang.lib.fun.common.base.view.BaseActivityByView;
/**
 * <pre>
 *      Date            ： 2018/7/4 17:26
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */
@Route(path = "/girls/list")
public class GirlsActivity extends BaseActivityByView {

    private GirlsView mView;
    private GirlsContract.Presenter mPresenter;

    @Override
    public boolean isLoadTopTab() {
        return true;
    }

    @Override
    public View loadViewLayout() {
        mView = new GirlsView(this);
        return mView;
    }

    @Override
    public void viewParam() {
        mPresenter = new GirlsPresenter(mView);
        mPresenter.start();
        setTitle(R.string.girls_activity_title);
    }


//    @Override
//    protected int setTitleId() {
//        return R.string.girls_activity_title;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mView = new GirlsView(this);
//        setContentView(mView);
//        mPresenter = new GirlsPresenter(mView);
//        mPresenter.start();
//    }
}
