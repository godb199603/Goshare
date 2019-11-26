package com.wzl.goshare.wan.collect;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wzl.goshare.http.Retrofit2Helper;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.api.WanApi;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.User;
import com.wzl.goshare.wan.bean.collect.CollectBean;
import com.wzl.goshare.wan.bean.collect.CollectListData;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * 作者：Create on 2019/9/6 16:28  by  wzl
 * 描述：
 * 最近修改：2019/9/6 16:28 modify by wzl
 */
public class CollectViewModel extends ViewModel{


    public static MutableLiveData<CollectListData> liveData7 = new MutableLiveData<>();

    public  MutableLiveData<CollectListData>  getCollect(int curPage) {
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.getCollectList(curPage-1).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<CollectListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody<CollectListData> collectListDataResponseBody) {
                        liveData7.setValue(collectListDataResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return liveData7;
    }




//////////////////////////////收藏站内
    public LiveData<CollectListData> collect(int id){
     final MutableLiveData<CollectListData> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.collect(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<CollectListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody<CollectListData> collectListDataResponseBody) {
                        liveData.setValue(collectListDataResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }

/////////////////////////收藏站外


    public LiveData<CollectListData> collect(String title,String author,String link){
        final MutableLiveData<CollectListData> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.collect(title,author,link).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<CollectListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody<CollectListData> collectListDataResponseBody) {
                        liveData.setValue(collectListDataResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    public LiveData<ResponseBody> unCollect(int id){
        final MutableLiveData<ResponseBody> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.unCollect(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        liveData.setValue(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }


    public LiveData<ResponseBody> unMyCollect(int id,int originId){
        final MutableLiveData<ResponseBody> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.unMyCollect(id,originId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        liveData.setValue(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }





    public LiveData<CollectListData> collect(HomeArticleDataBean data) {
        int id = data.getId();
        //收藏站内文章
        if (id > 0) {
            return collect(id);
        }
        //收藏站外文章
        return collect(data.getTitle(), data.getAuthor(), data.getLink());

    }








}
