package com.qiang.lib.bus.news.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.bus.news.R;
import com.qiang.lib.fun.common.base.view.BaseActivityByID;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
@Route(path = "/news/center")
public class NewsCenterActivity extends BaseActivityByID {

    protected TabLayout mTabLayout;
    protected Toolbar mToolBar;
    protected ViewPager mViewPager;
    private NewsListViewAdapter mListAdapter;

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void findViewById() {
        mToolBar = (Toolbar) findViewById(R.id.news_title_bar);
        setToolBar(mToolBar);
        setTitle(R.string.news_activity_title);

        mTabLayout = (TabLayout) findViewById(R.id.date_tab);
        mViewPager = (ViewPager) findViewById(R.id.message_pager);
    }

    @Override
    public void viewParam() {
        mListAdapter = new NewsListViewAdapter(getMessageListViews(), getWeekDate());
        mViewPager.setAdapter(mListAdapter);
        //setupWithViewPager必须在ViewPager.setAdapter()之后调用
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void setListener() {

    }

    @Override
    public void onClickEvent(View view) {

    }

    /**
     * 获取ViewPager的viewList
     */
    private List<NewsListView> getMessageListViews() {
        List<NewsListView> viewList = new ArrayList<>();
        List<String> weekDate = getWeekDate();
        if (weekDate != null) {
            for (String tab : weekDate) {
                viewList.add(new NewsListView(this, tab));
            }
        }
        return viewList;
    }


    /**
     * 获取过去7天的时期，格式为yymmdd
     **/
    public static List<String> getWeekDate() {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1 - i);
            dates.add(simpleDateFormat.format(calendar.getTime()));
        }
        return dates;
    }
}
