package com.lsj.gankapp.http;

import com.lsj.gankapp.model.GankData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ClassName: GankService
 * Desc:
 * Created by lsj on 16/7/28.
 */

public interface GankService {

    @GET(value = "data/{type}/{count}/{page}")
    Observable<GankData> getGank(
        @Path("type") String type,
        @Path("count") int count,
        @Path("page") int page);


    //请求某天干货数据
    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

}
