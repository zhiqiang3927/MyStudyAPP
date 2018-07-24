package com.qiang.lib.bus.news.detail;


import com.qiang.lib.bus.news.data.bean.MessageDetail;
import com.qiang.lib.fun.common.base.BasePresenter;
import com.qiang.lib.fun.common.base.BaseView;


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

public interface NewsDetailContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showNewsDetail(MessageDetail detail);

    }

    interface Presenter extends BasePresenter {

        /**
         * 获取最新列表
         *
         * @param newsId 新闻id
         */
        void getNewsDetail(String newsId);

    }

}
