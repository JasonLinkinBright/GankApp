package com.lsj.gankapp.http;

import com.lsj.gankapp.config.Apis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * ClassName: GankRetrofit
 * Desc:
 * Created by lsj on 16/7/28.
 */

public class GankRetrofit {

    private static Retrofit sRetrofit;

    public static Retrofit getRetrofit() {
        // 创建单例
        if (sRetrofit == null) {
            synchronized (GankRetrofit.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(Apis.GANK_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()) //加入Gson格式转换器
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //加入RxJava
                            .build();
                }
            }
        }
        return sRetrofit;

    }
}
