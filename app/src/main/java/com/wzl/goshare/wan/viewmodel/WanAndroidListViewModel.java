package com.wzl.goshare.wan.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import android.util.Log;

import com.wzl.goshare.base.baseadapter.BaseViewModel;
import com.wzl.goshare.http.Retrofit2Helper;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.api.WanApi;
import com.wzl.goshare.wan.bean.BannerItemBean;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.HomeArticleListDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Create on 2019/8/31 13:29  by  wzl
 * 描述：
 * 最近修改：2019/8/31 13:29 modify by wzl
 */
public class WanAndroidListViewModel extends ViewModel {



//  public  final MutableLiveData<ResponseBody<HomeArticleListDataBean>> listData2 = new MutableLiveData<>();

  public  static MutableLiveData<List<HomeArticleDataBean>> listData5 = new MutableLiveData<>();
  public  static MutableLiveData<HomeArticleListDataBean> listData = new MutableLiveData<>();
  public  static MutableLiveData<List<BannerItemBean>> listData2 = new MutableLiveData<>();


    public void loadData(){
//        getArticle();
        getArticle2();
    }


    public void getArticle2() {
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.getHomeArticleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<HomeArticleListDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                       addDisposable(d);
                    }

                    @Override
                    public void onNext(ResponseBody<HomeArticleListDataBean> homeArticleListDataBeanResponseBody) {

//                        List<HomeArticleDataBean> list=homeArticleListDataBeanResponseBody.getData().getDatas();
                        List<HomeArticleDataBean> list=new ArrayList<HomeArticleDataBean>();
                        Log.i("OOO",homeArticleListDataBeanResponseBody.getData().getDatas().get(15).getTitle());
                        Log.i("III",homeArticleListDataBeanResponseBody.getData().getDatas().size()+"");

//                      listData5.setValue(homeArticleListDataBeanResponseBody.getData().getDatas());
//                      list.add(homeArticleListDataBeanResponseBody.getData().getDatas().get(0));
                        listData5.setValue(homeArticleListDataBeanResponseBody.getData().getDatas());

                    }

                    @Override
                    public void onError(Throwable e) {
//                         listData2.setValue(null);
//                         listData5.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
       }



    public MutableLiveData<HomeArticleListDataBean> getArticle3(int curPage) {
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.getHomeArticleListData(curPage -1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<HomeArticleListDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                       addDisposable(d);
                    }
                    @Override
                    public void onNext(ResponseBody<HomeArticleListDataBean> homeArticleListDataBeanResponseBody) {

                        listData.setValue(homeArticleListDataBeanResponseBody.getData());

                    }

                    @Override
                    public void onError(Throwable e) {
//                         listData2.setValue(null);
//                         listData5.setValue(null);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return listData;
    }



    public MutableLiveData<List<BannerItemBean>>  getBanner() {

        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<List<BannerItemBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody<List<BannerItemBean>> listResponseBody) {
                        listData2.setValue(listResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return listData2;
    }
















    }//全部结束











