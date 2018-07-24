package com.qiang.lib.bus.alarmClock.data;


import com.qiang.lib.bus.alarmClock.data.bean.GirlsParser;
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

public interface GirlsDataSource {

    interface LoadGirlsCallback {

        void onGirlsLoaded(GirlsParser girlsParser);

        void onDataNotAvailable();
    }

    void getGirls(int size, int page, LoadGirlsCallback callback);

}
