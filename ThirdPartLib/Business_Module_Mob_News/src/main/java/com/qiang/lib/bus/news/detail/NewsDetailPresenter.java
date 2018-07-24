package com.qiang.lib.bus.news.detail;


import com.qiang.lib.bus.news.data.NewsDataSource;
import com.qiang.lib.bus.news.data.bean.MessageDetail;
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

public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    private NewsDetailContract.View mView;
    private NewsDataSource mDataSource;

    public NewsDetailPresenter(NewsDetailContract.View view) {
        mView = view;
        mDataSource = new RemoteNewsDataSource();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void getNewsDetail(String newsId) {
        mDataSource.getNewsDetail(newsId, new InfoCallback<MessageDetail>() {
            @Override
            public void onSuccess(MessageDetail detail) {
                if (mView.isActive()) {
                    mView.showNewsDetail(detail);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

}
