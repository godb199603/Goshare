package com.wzl.goshare.gank;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wzl.goshare.gank.api.GankApi;
import com.wzl.goshare.gank.api.Retrofit2Helper2;
import com.wzl.goshare.gank.bean.GankIoDataBean;
import com.wzl.goshare.http.Retrofit2Helper;
import com.wzl.goshare.wan.api.WanApi;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Create on 2019/9/12 01:19  by  wzl
 * 描述：
 * 最近修改：2019/9/12 01:19 modify by wzl
 */
public class WelfareViewModel extends ViewModel {

    /**
     * 图片url集合
     */
    private ArrayList<String> imgList = new ArrayList<>();
    /**
     * 图片标题集合，用于保存图片时使用
     */
    private ArrayList<String> imageTitleList = new ArrayList<>();
    /**
     * 传递给Activity数据集合
     */
    private ArrayList<ArrayList<String>> allList = new ArrayList<>();
    private final MutableLiveData<ArrayList<ArrayList<String>>> allListData = new MutableLiveData<>();



    public MutableLiveData<ArrayList<ArrayList<String>>> getImageAndTitle() {
        return allListData;
    }


    private int mPage = 1;

    public MutableLiveData<GankIoDataBean> loadWelfareData() {

        final MutableLiveData<GankIoDataBean> data = new MutableLiveData<>();

        GankApi gankApi = Retrofit2Helper2.createretrofit().create(GankApi.class);
        gankApi.getGankIoData("福利", mPage, 20).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean gankIoDataBean) {
                        handleImageList(gankIoDataBean);
                        data.setValue(gankIoDataBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



return data;
    }



    /**
     * 异步处理用于图片详情显示的数据
     *
     * @param gankIoDataBean 原数据
     */
    private void handleImageList(final GankIoDataBean gankIoDataBean) {
        Observable
                .create(new ObservableOnSubscribe<ArrayList<ArrayList<String>>>() {
                    @Override
                    public void subscribe(ObservableEmitter<ArrayList<ArrayList<String>>> emitter) throws Exception {
                        imgList.clear();
                        imageTitleList.clear();
                        for (int i = 0; i < gankIoDataBean.getResults().size(); i++) {
                            imgList.add(gankIoDataBean.getResults().get(i).getUrl());
                            imageTitleList.add(gankIoDataBean.getResults().get(i).getDesc());
                        }
                        allList.clear();
                        allList.add(imgList);
                        allList.add(imageTitleList);
                        emitter.onNext(allList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ArrayList<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ArrayList<String>> arrayLists) {
                        allListData.setValue(arrayLists);
                    }

                    @Override
                    public void onError(Throwable e) {
                        allListData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }











}//zongjieshu