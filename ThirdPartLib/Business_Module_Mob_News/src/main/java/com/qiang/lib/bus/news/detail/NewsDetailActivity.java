package com.qiang.lib.bus.news.detail;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.fun.common.base.view.BaseActivityByView;


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
@Route(path = "/news/detail")
public class NewsDetailActivity extends BaseActivityByView {

    private NewsDetailView detailView;

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public View loadViewLayout() {
        detailView = new NewsDetailView(this);
        return detailView;
    }

    @Override
    public void viewParam() {
        String id = getIntent().getStringExtra("id");
        new NewsDetailPresenter(detailView).getNewsDetail(id);
    }

}
