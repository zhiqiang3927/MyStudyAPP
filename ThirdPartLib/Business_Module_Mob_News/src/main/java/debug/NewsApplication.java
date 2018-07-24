package debug;

import com.orhanobut.logger.Logger;
import com.qiang.lib.bus.news.Constants;
import com.qiang.lib.bus.news.data.bean.StoryList;
import com.qiang.lib.fun.common.base.conts.BaseApplication;
import com.qiang.lib.fun.common.utils.MyHttpUtils;
import com.qiang.lib.fun.common.net.okhttp.OnResultListener;

/**
 * <pre>
 *      Date            ： 2018/7/4 15:33
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ： NewsApplication
 *      Deprecation     ：
 * </pre>
 */

public class NewsApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        login();
    }


    /**
     * 在这里模拟登陆，然后拿到sessionId或者Token
     * 这样就能够在组件请求接口了
     */
    private void login() {
        MyHttpUtils.getDataFromOKHttpByJSONObject(Constants.ZHIHU_DAILY_BEFORE_MESSAGE,"20170419",null,StoryList.class,new OnResultListener<StoryList>(){
            @Override
            public void onSuccess(StoryList result) {
                Logger.e(result.toString());
            }

            @Override
            public void onError(int code, String message) {
                Logger.e(message);
            }

            @Override
            public void onFailure(String message) {
                Logger.e(message);
            }
        });
    }
}
