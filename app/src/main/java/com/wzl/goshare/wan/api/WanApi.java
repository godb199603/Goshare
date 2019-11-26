package com.wzl.goshare.wan.api;

import com.wzl.goshare.wan.bean.BannerItemBean;
import com.wzl.goshare.wan.bean.HomeArticleListDataBean;
import com.wzl.goshare.wan.bean.NaviBean;
import com.wzl.goshare.wan.bean.User;
import com.wzl.goshare.wan.bean.collect.CollectListData;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 作者：Create on 2019/8/30 02:43  by  wzl
 * 描述：
 * 最近修改：2019/8/30 02:43 modify by wzl
 */
public interface WanApi {
    /**
     * 获取首页文章数据列表
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListDataBean>> getHomeArticleListData(@Path("pageNum")int pageNum );


    /**
     * 获取首页Banner数据
     * @return
     */
    @GET("/banner/json")
    Observable<ResponseBody<List<BannerItemBean>>>getBanner();



    @GET("/article/list/0/json")
    Observable<ResponseBody<HomeArticleListDataBean>> getHomeArticleList();


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResponseBody<User>> login(@Field("username") String username, @Field("password")String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<ResponseBody<User>> register(@Field("username")String username, @Field("password")String password, @Field("repassword")String repassword);



    /**
     * 获取收藏列表
     * @param curPage 从第0页开始
     * @return
     */
    @GET("lg/collect/list/{curPage}/json")
    Observable<ResponseBody<CollectListData>> getCollectList(@Path("curPage") int curPage);

    /**
     * 导航数据
     */
    @GET("navi/json")
    Observable<ResponseBody<List<NaviBean>>> getNavi();







    /**
     * 收藏站内文章
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<ResponseBody<CollectListData>> collect(@Path("id") int id);


    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    @FormUrlEncoded
    @POST("lg/collect/add/json")
    Observable<ResponseBody<CollectListData>> collect(@Field("title")String title,@Field("author")String author,@Field("link")String link);

    /**
     * 取消文章列表中的收藏
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<ResponseBody> unCollect(@Path("id") int id);

    /**
     * 取消我的收藏中的收藏
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    Observable<ResponseBody> unMyCollect(@Path("id") int id,@Field("originId")int originId);

    /**
     * 文章列表 页面调用  取消收藏
     * @param articleId 文章id:拼接在链接上
     * @return
     */
    @POST("/lg/uncollect_originId/{articleId}/json")
    Observable<ResponseBody<String>>cancelCollectArticleListData(
            @Path("articleId") int articleId
    );

}
