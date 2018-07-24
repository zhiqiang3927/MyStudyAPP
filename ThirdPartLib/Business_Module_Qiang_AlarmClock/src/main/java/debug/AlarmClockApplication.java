package debug;

import com.orhanobut.logger.Logger;
import com.qiang.lib.bus.alarmClock.Constants;
import com.qiang.lib.fun.common.base.conts.BaseApplication;
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

public class AlarmClockApplication extends BaseApplication {

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
        MyHttpUtils.getDataFromOKHttpByString(Constants.GAN_HUO_API,"福利/10/1",null,new OnResultListener<String>(){
            @Override
            public void onSuccess(String result) {
                Logger.e(result);
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
