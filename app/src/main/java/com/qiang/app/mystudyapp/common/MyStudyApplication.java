package com.qiang.app.mystudyapp.common;

import com.qiang.lib.fun.common.base.conts.BaseApplication;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:28
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

//@ReportsCrashes(
//        mailTo = "zhiqiang2008.happy@163.com",
//        mode = ReportingInteractionMode.DIALOG,
//        customReportContent = {
//                ReportField.APP_VERSION_NAME,
//                ReportField.ANDROID_VERSION,
//                ReportField.PHONE_MODEL,
//                ReportField.CUSTOM_DATA,
//                ReportField.BRAND,
//                ReportField.STACK_TRACE,
//                ReportField.LOGCAT,
//                ReportField.USER_COMMENT},
//        resToastText = R.string.crash_toast_text,
//        resDialogText = R.string.crash_dialog_text,
//        resDialogTitle = R.string.crash_dialog_title)
public class MyStudyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        MyStudyApplication.SingletonHolder.instance = this;
//        initMobSDK();


        //崩溃日志记录初始化
//        ACRA.init(this);
//        ACRA.getErrorReporter().removeAllReportSenders();
//        ACRA.getErrorReporter().setReportSender(new CrashReportSender());
    }

//    private void initMobSDK() {
//        MobSDK.init(this);
//    }

    /**
     * 发送崩溃日志
     */
//    private class CrashReportSender implements ReportSender {
//        CrashReportSender() {
//            ACRA.getErrorReporter().putCustomData("PLATFORM", "ANDROID");
//            ACRA.getErrorReporter().putCustomData("BUILD_ID", android.os.Build.ID);
//            ACRA.getErrorReporter().putCustomData("DEVICE_NAME", android.os.Build.PRODUCT);
//        }
//
//        @Override
//        public void send(Context context, CrashReportData crashReportData) throws ReportSenderException {
//            EmailIntentSender emailSender = new EmailIntentSender(getApplicationContext());
//            emailSender.send(context, crashReportData);
//        }
//    }
}
