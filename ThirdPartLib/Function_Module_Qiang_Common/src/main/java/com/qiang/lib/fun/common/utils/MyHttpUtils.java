package com.qiang.lib.fun.common.utils;

import android.support.annotation.NonNull;

import com.qiang.lib.fun.common.net.okhttp.DataTypeOfServerBack;
import com.qiang.lib.fun.common.net.okhttp.HttpClient;
import com.qiang.lib.fun.common.net.okhttp.OnResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *      Date            ： 2018/7/4 15:10
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： MyHttpUtils
 *      Deprecation     ： 网络请求的公共类
 * </pre>
 */

public class MyHttpUtils {
    public static final int GET = 0;
    public static final int POST = 1;

    /**
     * 获取数据的方式为：OKHttp-GET-JSONObject
     *
     * @param baseUrl          基本URL
     * @param subUrl           接口URL
     * @param paramsMap        参数Map
     * @param clazz            数据实体类
     * @param onResultListener 获取数据回调
     * @param <T>              数据实体类型
     */
    public static <T> void getDataFromOKHttpByJSONObject(String baseUrl, String subUrl, Map<String, String> paramsMap, @NonNull Class<T> clazz, OnResultListener onResultListener) {
        handleData(baseUrl,subUrl,DataTypeOfServerBack.JSON_OBJECT,clazz,paramsMap,MyHttpUtils.GET,onResultListener);

    }

    /**
     * 获取数据的方式为：OKHttp-POST-JSONObject
     *
     * @param baseUrl          基本URL
     * @param subUrl           接口URL
     * @param paramsMap        参数Map
     * @param clazz            数据实体类
     * @param onResultListener 获取数据回调
     * @param <T>              数据实体类型
     */
    public static <T> void postDataFromOKHttpByJSONObject(String baseUrl, String subUrl, Map<String, String> paramsMap, @NonNull Class<T> clazz, OnResultListener onResultListener) {
        handleData(baseUrl,subUrl,DataTypeOfServerBack.JSON_OBJECT,clazz,paramsMap,MyHttpUtils.POST,onResultListener);
    }

    /**
     * 获取数据的方式为：OKHttp-GET-String
     *
     * @param baseUrl          基本URL
     * @param subUrl           接口URL
     * @param paramsMap        参数Map
     * @param onResultListener 获取数据回调
     */
    public static void getDataFromOKHttpByString(String baseUrl, String subUrl, Map<String, String> paramsMap, OnResultListener onResultListener) {
        handleData(baseUrl,subUrl,DataTypeOfServerBack.STRING,String.class,paramsMap,MyHttpUtils.GET,onResultListener);
    }

    /**
     * 获取数据的方式为：OKHttp-POST-String
     *
     * @param baseUrl          基本URL
     * @param subUrl           接口URL
     * @param paramsMap        参数Map
     * @param onResultListener 获取数据回调
     */
    public static void postDataFromOKHttpByString(String baseUrl, String subUrl, Map<String, String> paramsMap, OnResultListener onResultListener) {
        handleData(baseUrl,subUrl,DataTypeOfServerBack.STRING,String.class,paramsMap,MyHttpUtils.POST,onResultListener);
    }

    private static void handleData(String baseUrl, String subUrl,@DataTypeOfServerBack.Type int bodyType,@NonNull Class clazz,Map<String, String> paramsMap,int mathodType, OnResultListener onResultListener){

        if (paramsMap == null) {
            paramsMap = new HashMap<>();
        }
        HttpClient client = new HttpClient.Builder()
                .baseUrl(baseUrl)
                .subUrl(subUrl)
                .paramsMap(paramsMap)
                .tag(baseUrl + "-" + subUrl)
                .bodyType(bodyType, clazz)
                .build();
        if (mathodType == MyHttpUtils.GET){
            client.getData(onResultListener);
        } else if (mathodType == MyHttpUtils.POST){
            client.postData(onResultListener);
        }
    }
}
