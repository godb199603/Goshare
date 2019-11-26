package com.wzl.goshare;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.wzl.goshare.utils.AES;
import com.wzl.goshare.utils.SharedPreferencesUtils;
import com.wzl.goshare.wan.bean.User;

/**
 * 作者：Create on 2019/9/6 17:47  by  wzl
 * 描述：
 * 最近修改：2019/9/6 17:47 modify by wzl
 */
public class App extends Application{
    private static App mInstance=null;
    public static synchronized App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }













    private User user;

    public User getUser() {
        return user;
    }

    public boolean isLogin(){
        return user!=null;
    }

    private void cacheUser(User user){
        SharedPreferencesUtils.getSharedPreferences(this,Constants.KEY_USER).edit()
                .putInt(Constants.KEY_USER_ID,user.getId())
                .putString(Constants.KEY_USERNAME,user.getUsername())
                .putString(Constants.KEY_PASSWORD, AES.INSTANCE.encrypt(user.getPassword()))
                .putString(Constants.KEY_EMAIL,user.getEmail())
                .putString(Constants.KEY_ICON,user.getIcon())
                .putInt(Constants.KEY_TYPE,user.getType())
                .commit();
    }

    public User getCacheUser(){
        User user = null;
        SharedPreferences cache = SharedPreferencesUtils.getSharedPreferences(this,Constants.KEY_USER);
        if(cache!=null && cache.contains(Constants.KEY_USERNAME)){
            user = new User();
            user.setId(cache.getInt(Constants.KEY_ID,0));
            user.setUsername(cache.getString(Constants.KEY_USERNAME,null));
            user.setEmail(cache.getString(Constants.KEY_EMAIL,null));
            user.setPassword(AES.INSTANCE.decrypt(cache.getString(Constants.KEY_PASSWORD,null)));
            user.setIcon(cache.getString(Constants.KEY_ICON,null));
            user.setType(cache.getInt(Constants.KEY_TYPE,0));
        }
        return user;
    }


    /**
     * 登录
     * @param user
     */
    public void login(@NonNull User user){
        login(user,true);
    }

    /**
     * 登录
     * @param user
     * @param isCache
     */
    public void login(@NonNull User user,boolean isCache){
        this.user = user;
        if(isCache){
            cacheUser(user);
        }
    }

    /**
     * 退出登录
     */
    public void logout(){
        user = null;
        SharedPreferencesUtils.getSharedPreferences(this,Constants.KEY_USER).edit().clear().commit();
//        App.getCookieJar().clear();

    }



}
