package com.qiang.lib.bus.news.main;


import com.qiang.lib.bus.news.data.NewsDataSource;
import com.qiang.lib.bus.news.data.bean.StoryList;
import com.qiang.lib.bus.news.data.source.RemoteNewsDataSource;
import com.qiang.lib.fun.common.base.InfoCallback;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:23
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.View mView;
    private NewsDataSource mDataSource;

    public NewsListPresenter(NewsListContract.View view) {
        mView = view;
        mDataSource = new RemoteNewsDataSource();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getNewMessages(int page, int size, String date) {
        mDataSource.getNewsList(date, new InfoCallback<StoryList>() {
            @Override
            public void onSuccess(StoryList info) {
                if (mView.isActive()) {
                    mView.showNewsList(info);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }
}
