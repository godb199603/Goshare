package com.wzl.goshare.wan.navigation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.wzl.goshare.http.Retrofit2Helper;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.api.WanApi;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.NaviBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Create on 2019/9/7 02:49  by  wzl
 * 描述：
 * 最近修改：2019/9/7 02:49 modify by wzl
 */
public class NavigationViewModel extends ViewModel{

    // content数据
    public final MutableLiveData<List<HomeArticleDataBean>> data = new MutableLiveData<>();
    // title数据
    public final MutableLiveData<List<NaviBean>> dataTitle = new MutableLiveData<>();

    // content 的 title position  外面的i对应的 titlePositions.get(i)
    public final MutableLiveData<List<Integer>> titlePositions = new MutableLiveData<>();
    // 用来滑动定位 第一个Integer为内容的position，第二个Integer为对应的分类position
    public HashMap<Integer, Integer> hashMap=new HashMap<Integer, Integer>();

    public MutableLiveData<List<HomeArticleDataBean>> getData() {
        return data;
    }

    public MutableLiveData<List<NaviBean>> getDataTitle() {
        return dataTitle;
    }

    public MutableLiveData<List<Integer>> getTitlePositions() {
        return titlePositions;
    }

    public HashMap<Integer, Integer> getHashMap() {
        return hashMap;
    }


    public int getNavigation() {

        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.getNavi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<List<NaviBean>>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(ResponseBody<List<NaviBean>> listResponseBody) {

//                        if (listResponseBody != null
//                                && listResponseBody.getData() != null
//                                && listResponseBody.getData().size() > 0) {
                            Log.i("uuuIIY", listResponseBody.getData().toString());
                            // title
                            Log.i("oppp", dataTitle.toString());
                            dataTitle.setValue(listResponseBody.getData());
                            Log.i("oppp", dataTitle.toString());
                            // content
                            ArrayList<HomeArticleDataBean> list = new ArrayList<HomeArticleDataBean>();
                            // content部分对应分类的position
                            ArrayList<Integer> positions = new ArrayList<Integer>();
                            for (int i = 0; i < listResponseBody.getData().size(); i++) {
                                NaviBean dataBean = listResponseBody.getData().get(i);
                                Log.i("oppp2", dataBean.getName().toString());
                                HomeArticleDataBean bean = new HomeArticleDataBean();
                                bean.setNavigationName(dataBean.getName());
                                Log.i("oppp4", dataBean.getName().toString());
                                positions.add(list.size());
                                if (i != 0) {
                                    // 最后一个item可能有一个或两个
                                    hashMap.put(list.size() - 1, i - 1);
                                    hashMap.put(list.size() - 2, i - 1);
                                }
                                hashMap.put(list.size(), i);
                                Log.i("oppp7", hashMap.toString());
                                list.add(bean);
                                list.addAll(dataBean.getArticles());
                            }
                            data.setValue(list);
                            titlePositions.setValue(positions);
//                           }else {
//                                data.setValue(null);
//                                dataTitle.setValue(null);
//                            }

                        }//if结束
//                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

return 1;
    }







}//总结书
