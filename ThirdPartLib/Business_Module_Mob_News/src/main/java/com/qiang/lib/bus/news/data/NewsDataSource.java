package com.qiang.lib.bus.news.data;

import com.qiang.lib.bus.news.data.bean.MessageDetail;
import com.qiang.lib.bus.news.data.bean.StoryList;
import com.qiang.lib.fun.common.base.InfoCallback;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:22
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

public interface NewsDataSource {


    /**
     * 获取当天的新闻列表
     *
     * @param date     日期
     * @param callback 回调
     */
    void getNewsList(String date, InfoCallback<StoryList> callback);

    /**
     * 获取某条新闻详情
     *
     * @param id       新闻Id
     * @param callback 回调
     */
    void getNewsDetail(String id, InfoCallback<MessageDetail> callback);

}

