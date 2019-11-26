package com.wzl.goshare.gank.api;



import com.wzl.goshare.BuildConfig;
import com.wzl.goshare.http.cookie.CookieManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Create on 2019/8/8 23:24  by  wzl
 * 描述：
 * 最近修改：2019/8/8 23:24 modify by wzl
 */
public class Retrofit2Helper2 {
    public static Retrofit mRetrofit;
    public static final String BASE_URL = "http://gank.io/api/";

    public static Retrofit createretrofit() {
        if (mRetrofit == null) {
            CookieManager cookieManager = new CookieManager();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cookieJar(cookieManager);
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
