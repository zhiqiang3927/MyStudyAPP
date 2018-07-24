package com.qiang.lib.bus.alarmClock.data.source;


import com.qiang.lib.bus.alarmClock.Constants;
import com.qiang.lib.bus.alarmClock.data.GirlsDataSource;
import com.qiang.lib.bus.alarmClock.data.bean.GirlsParser;
import com.qiang.lib.fun.common.utils.MyHttpUtils;
import com.qiang.lib.fun.common.net.okhttp.OnResultListener;
/**
 * <pre>
 *      Date            ： 2018/7/4 15:42
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

public class RemoteGirlsDataSource implements GirlsDataSource {

    @Override
    public void getGirls(int size, int page, final LoadGirlsCallback callback) {
        MyHttpUtils.getDataFromOKHttpByJSONObject(Constants.GAN_HUO_API,"福利/" + size + "/" + page,null,GirlsParser.class,new OnResultListener<GirlsParser>(){
            @Override
            public void onSuccess(GirlsParser result) {
                callback.onGirlsLoaded(result);
            }

            @Override
            public void onError(int code, String message) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onFailure(String message) {
                callback.onDataNotAvailable();
            }
        });
    }
}
