package com.qiang.lib.fun.common.net.okhttp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.orhanobut.logger.Logger;
import com.qiang.lib.fun.common.R;
import com.qiang.lib.fun.common.base.BaseContextUtils;
import com.qiang.lib.fun.common.utils.MyNetworkUtils;
import com.qiang.lib.fun.common.utils.MyToastUtil;
import com.qiang.lib.fun.common.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * <pre>
 *      Date            ： 2018/7/4 13:20
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ： Function_Module_Qiang_Common
 *      FunctionName    ： HttpClient
 *      Deprecation     ： 网络请求
 * </pre>
 */

public class HttpClient {
    /*The certificate's password*/
    private static final String STORE_PASS = "6666666";
    private static final String STORE_ALIAS = "666666";
    /*用户设置的BASE_URL*/
    private static String BASE_URL = "";
    /*本地使用的baseUrl*/
    private String baseUrl = "";
    /*请求的客户端*/
    private static OkHttpClient okHttpClient;
    /*Retrofit*/
    private Retrofit retrofit;
    private Call<ResponseBody> mCall;
    private static final Map<String, Call> CALL_MAP = new HashMap<>();

    /**
     * 获取HttpClient的单例
     */
    private static HttpClient mHttpClient = null;

    private HttpClient() {
        //TODO ClearableCookieJar
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseContextUtils.getContext()));
        //HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(BaseContextUtils.getContext(), R.raw.cer,STORE_PASS , STORE_ALIAS);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                // .hostnameVerifier(HttpsUtil.getHostnameVerifier())
                .addInterceptor(new LoggerInterceptor(null, true))
                .cookieJar(cookieJar)
                .build();
    }

    public static HttpClient getInstance() {
        synchronized (HttpClient.class) {
            if (mHttpClient == null) {
                mHttpClient = new HttpClient();
            }
        }
        return mHttpClient;
    }

    /**
     * HttpClient构造类
     */
    private Builder mBuilder;

    public Builder getBuilder() {
        return mBuilder;
    }

    private void setBuilder(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * 发起POST请求
     *
     * @param onResultListener 请求结果回调
     */
    public void postData(final OnResultListener onResultListener) {
        Builder builder = getBuilder();
        mCall = retrofit.create(ApiService.class)
                .executePost(builder.subUrl, builder.paramsMap);
        putCall(builder, mCall);
        request(builder, onResultListener);
    }

    /**
     * 发起GET请求
     *
     * @param onResultListener 请求结果回调
     */
    public void getData(final OnResultListener onResultListener) {
        Builder builder = getBuilder();
        if (!builder.paramsMap.isEmpty()) {
            String value = "";
            for (Map.Entry<String, String> entry : builder.paramsMap.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                String span = value.equals("") ? "" : "&";
                String part = StringUtils.buffer(span, mapKey, "=", mapValue);
                value = StringUtils.buffer(value, part);
            }
            builder.subUrl(StringUtils.buffer(builder.subUrl, "?", value));
        }
        mCall = retrofit.create(ApiService.class).executeGet(builder.subUrl);
        putCall(builder, mCall);
        request(builder, onResultListener);
    }

    /**
     * 网络请求
     *
     * @param builder
     * @param onResultListener
     */
    private void request(final Builder builder, final OnResultListener onResultListener) {
        if (!MyNetworkUtils.isConnected()) {
            MyToastUtil.showErrorToast(R.string.common_current_internet_invalid,false);
            onResultListener.onFailure(BaseContextUtils.getString(R.string.common_current_internet_invalid));
            return;
        }
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (200 == response.code()) {
                    try {
                        String result = response.body().string();
                        parseData(result, builder.clazz, builder.bodyType, onResultListener);
                    } catch (IOException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                if (!response.isSuccessful() || 200 != response.code()) {
                    onResultListener.onError(response.code(), response.message());
                }
                if (null != builder.tag) {
                    removeCall(builder.subUrl);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                onResultListener.onFailure(t.getMessage());
                if (null != builder.tag) {
                    removeCall(builder.subUrl);
                }
            }

        });
    }

    /**
     * 添加某个请求
     */
    private synchronized void putCall(Builder builder, Call call) {
        if (builder.tag == null)
            return;
        synchronized (CALL_MAP) {
            CALL_MAP.put(builder.tag.toString() + builder.subUrl, call);
        }
    }

    /**
     * 取消某个界面都所有请求，或者是取消某个tag的所有请求;
     * 如果要取消某个tag单独请求，tag需要传入tag+subUrl
     *
     * @param tag 请求标签
     */
    public synchronized void cancel(Object tag) {
        if (tag == null)
            return;
        List<String> list = new ArrayList<>();
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.startsWith(tag.toString())) {
                    CALL_MAP.get(key).cancel();
                    list.add(key);
                }
            }
        }
        for (String s : list) {
            removeCall(s);
        }

    }

    /**
     * 移除某个请求
     *
     * @param url 添加的url
     */
    private synchronized void removeCall(String url) {
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(url)) {
                    url = key;
                    break;
                }
            }
            CALL_MAP.remove(url);
        }
    }

    /**
     * Build a new HttpClient.
     * subUrl is required before calling. All other methods are optional.
     */
    public static final class Builder {
        private String baseUrl = "";
        private String subUrl;
        private Object tag;
        private Map<String, String> paramsMap = new HashMap<>();
        /*返回数据的类型,默认是string类型*/
        @DataTypeOfServerBack.Type
        private int bodyType = DataTypeOfServerBack.STRING;
        /*解析类*/
        private Class clazz;

        public Builder() {
        }

        /**
         * 请求地址的baseUrl，最后会被赋值给HttpClient的静态变量BASE_URL；
         *
         * @param baseUrl 请求地址的baseUrl
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * 除baseUrl以外的部分，
         * 例如："mobile/login"
         *
         * @param subUrl path路径
         */
        public Builder subUrl(String subUrl) {
            this.subUrl = subUrl;
            return this;
        }

        /**
         * 给当前网络请求添加标签，用于取消这个网络请求
         *
         * @param tag 标签
         */
        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }
        /**
         * 添加请求参数
         */
        public Builder paramsMap(Map<String, String> paramsMap) {
            this.paramsMap = paramsMap;
            return this;
        }

        /**
         * 响应体类型设置,如果要响应体类型为STRING，请不要使用这个方法
         *
         * @param bodyType 响应体类型，分别:STRING，JSON_OBJECT,JSON_ARRAY,XML
         * @param clazz    指定的解析类
         * @param <T>      解析类
         */
        public <T> Builder bodyType(@DataTypeOfServerBack.Type int bodyType, @NonNull Class<T> clazz) {
            this.bodyType = bodyType;
            this.clazz = clazz;
            return this;
        }

        public HttpClient build() {
            if (!TextUtils.isEmpty(baseUrl)) {
                BASE_URL = baseUrl;
            }
            HttpClient client = HttpClient.getInstance();
            client.getRetrofit();
            client.setBuilder(this);
            return client;
        }
    }

    /**
     * 获取的Retrofit的实例，
     * 引起Retrofit变化的因素只有静态变量BASE_URL的改变。
     */
    private void getRetrofit() {
        if (!BASE_URL.equals(baseUrl) || retrofit == null) {
            baseUrl = BASE_URL;
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }

    /**
     * 数据解析方法
     *
     * @param data             要解析的数据
     * @param clazz            解析类
     * @param bodyType         解析数据类型
     * @param onResultListener 回调方数据接口
     */
    @SuppressWarnings("unchecked")
    private void parseData(String data, Class clazz, @DataTypeOfServerBack.Type int bodyType, OnResultListener onResultListener) {
        switch (bodyType) {
            case DataTypeOfServerBack.STRING:
                onResultListener.onSuccess(data);
                break;
            case DataTypeOfServerBack.JSON_OBJECT:
                onResultListener.onSuccess(DataParseUtil.parseObject(data, clazz));
                break;
            case DataTypeOfServerBack.JSON_ARRAY:
                onResultListener.onSuccess(DataParseUtil.parseToArrayList(data, clazz));
                break;
            case DataTypeOfServerBack.XML:
                onResultListener.onSuccess(DataParseUtil.parseXml(data, clazz));
                break;
            default:
                Logger.e("http parse tip:", "if you want return object, please use bodyType() set data type");
                break;
        }
    }

}
