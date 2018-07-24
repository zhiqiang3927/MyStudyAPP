package com.qiang.lib.bus.news.data.source;


import com.qiang.lib.bus.news.Constants;
import com.qiang.lib.bus.news.data.NewsDataSource;
import com.qiang.lib.bus.news.data.bean.MessageDetail;
import com.qiang.lib.bus.news.data.bean.StoryList;
import com.qiang.lib.fun.common.base.InfoCallback;
import com.qiang.lib.fun.common.utils.MyHttpUtils;
import com.qiang.lib.fun.common.net.okhttp.OnResultListener;

/**
 * <pre>
 *      Date            ： 2018/7/4 15:26
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ： RemoteNewsDataSource
 *      Deprecation     ：
 * </pre>
 */

public class RemoteNewsDataSource implements NewsDataSource {

    @Override
    public void getNewsList(String date, final InfoCallback<StoryList> callback) {
        MyHttpUtils.getDataFromOKHttpByJSONObject(Constants.ZHIHU_DAILY_BEFORE_MESSAGE,date,null,StoryList.class,new OnResultListener<StoryList>(){
            @Override
            public void onSuccess(StoryList result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(int code, String message) {
                callback.onError(code, message);
            }

            @Override
            public void onFailure(String message) {
                callback.onError(0, message);
            }
        });
    }


    @Override
    public void getNewsDetail(String id, final InfoCallback<MessageDetail> callback) {
        MyHttpUtils.getDataFromOKHttpByJSONObject(Constants.ZHIHU_DAILY_BEFORE_MESSAGE_DETAIL,id,null,MessageDetail.class,new OnResultListener<MessageDetail>(){
            @Override
            public void onSuccess(MessageDetail result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(int code, String message) {
                callback.onError(code, message);
            }

            @Override
            public void onFailure(String message) {
                callback.onError(0, message);
            }
        });
    }
}
