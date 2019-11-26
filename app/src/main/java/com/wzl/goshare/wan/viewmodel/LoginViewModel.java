package com.wzl.goshare.wan.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wzl.goshare.http.Retrofit2Helper;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.api.WanApi;
import com.wzl.goshare.wan.bean.BannerItemBean;
import com.wzl.goshare.wan.bean.User;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * 作者：Create on 2019/9/5 17:37  by  wzl
 * 描述：
 * 最近修改：2019/9/5 17:37 modify by wzl
 */
public class LoginViewModel extends ViewModel{

   public static MutableLiveData<User> liveData = new MutableLiveData<>();
   public static MutableLiveData<User> liveData2 = new MutableLiveData<>();


    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    public LiveData<User> register(String username, String password){
        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.register(username,password,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody<User> userResponseBody) {
                        liveData.setValue(userResponseBody.getData());
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



    public LiveData<User> login(String username,String password){

        WanApi newsApi = Retrofit2Helper.createretrofit().create(WanApi.class);
        newsApi.login(username,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody<User> userResponseBody) {
                        liveData2.setValue(userResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData2;
    }






}
