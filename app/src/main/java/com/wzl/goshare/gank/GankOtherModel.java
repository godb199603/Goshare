package com.wzl.goshare.gank;



import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jingbin on 2017/1/17.
 * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页  的Model
 * 好处之一是请求数据接口可以统一，不用每个地方都写请求的接口，更换接口方便。
 * 其实代码量也没有减少多少，但维护起来方便。
 */

public class GankOtherModel {

    private String id;
    private int page;
    private int perPage;

    public void setData(String id, int page, int perPage) {
        this.id = id;
        this.page = page;
        this.perPage = perPage;
    }
}
//    public void getGankIoData(final RequestImpl listener) {
//        HttpClient.Builder.getGankIOServer().getGankIoData(id, page, perPage)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GankIoDataBean>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//                        listener.loadFailed();
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        listener.addSubscription(d);
//                    }
//
//                    @Override
//                    public void onNext(GankIoDataBean gankIoDataBean) {
//                        listener.loadSuccess(gankIoDataBean);
//                    }
//                });
//    }
//}
