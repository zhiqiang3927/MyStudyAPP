package com.qiang.lib.bus.alarmClock.main;


import com.qiang.lib.bus.alarmClock.data.GirlsDataSource;
import com.qiang.lib.bus.alarmClock.data.bean.GirlsParser;
import com.qiang.lib.bus.alarmClock.data.source.RemoteGirlsDataSource;

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

public class GirlsPresenter implements GirlsContract.Presenter {

    private GirlsContract.View mView;
    private GirlsDataSource mDataSource;

    public GirlsPresenter(GirlsContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mDataSource = new RemoteGirlsDataSource();
    }

    @Override
    public void getGirls(int size, int page, final boolean isRefresh) {
        mDataSource.getGirls(size, page, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsParser girlsParser) {
                if (isRefresh) {
                    mView.refresh(girlsParser.getResults());
                } else {
                    mView.load(girlsParser.getResults());
                }
                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    mView.showError();
                }
            }
        });
    }

    @Override
    public void start() {
        getGirls(20, 1, true);
    }
}
