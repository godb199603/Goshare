package com.wzl.goshare.gank.api;

import com.wzl.goshare.gank.bean.GankIoDataBean;
import com.wzl.goshare.gank.bean.GankIoDayBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：Create on 2019/9/12 01:34  by  wzl
 * 描述：
 * 最近修改：2019/9/12 01:34 modify by wzl
 */
public interface GankApi {


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);

    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2015/08/06
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankIoDayBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
