package com.wzl.goshare.wan.login;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.wzl.goshare.Constants;
import com.wzl.goshare.MainActivity;
import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseActivity;
import com.wzl.goshare.databinding.ActivityLoginBinding;
import com.wzl.goshare.utils.AES;
import com.wzl.goshare.utils.SharedPreferencesUtils;
import com.wzl.goshare.utils.ToastUtils;
import com.wzl.goshare.wan.bean.User;
import com.wzl.goshare.wan.ui.HomeFragment2;
import com.wzl.goshare.wan.viewmodel.LoginViewModel;

import static android.widget.Toast.LENGTH_SHORT;
import static java.security.AccessController.getContext;

/**
 * 作者：Create on 2019/9/5 17:05  by  wzl
 * 描述：
 * 最近修改：2019/9/5 17:05 modify by wzl
 */
public class LoginActivity extends BaseActivity{

    private User user;
    private ActivityLoginBinding bindingView;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        setContentView(bindingView.getRoot());
//        bindingView = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_login, null, false);
        bindingView=DataBindingUtil.setContentView(this,R.layout.activity_login);
        initViewModel();


//        bindingView.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        bindingView.etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bindingView.etUsername.setSelected(!TextUtils.isEmpty(s));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        clickRightClear(bindingView.etUsername);

        Log.i("rrrr",bindingView.etUsername.getText().toString());
        bindingView.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("rrrrrr","uuuuuuu");
                clickLogin();
            }
        });



    }//create方法结束

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void initViewModel(){
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        bindingView.setViewModel(loginViewModel);
    }

    private void clickLogin() {
        if (checkInput(bindingView.etUsername, R.string.tips_username_is_empty) && checkInput(bindingView.etPassword, R.string.tips_password_is_empty)) {
            String username = bindingView.etUsername.getText().toString();
            String password = bindingView.etPassword.getText().toString();
            loginViewModel.login(username, password).observe(this, new Observer<User>() {
                @Override
                public void onChanged(@Nullable User user) {
                    login(user);
                    Toast.makeText(LoginActivity.this,"登录成功",LENGTH_SHORT);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
             }
            });
        }//subscribe结束

    }
    public boolean checkInput(TextView tv,@StringRes int msg){
        return checkInput(tv,msg,true);
    }
        public boolean checkInput(TextView tv, @StringRes int msg,boolean isShake){
        if(TextUtils.isEmpty(tv.getText())){
            Toast.makeText(this,tv.getText(),LENGTH_SHORT);
            if(isShake){
            startShake(tv);
            }
            return false;
        }
        return true;
    }
//
//    public void showToast(@StringRes int resId){
//        ToastUtils.showToast(getContext(),resId);
//    }
//
//    public boolean checkInput(TextView tv){
//        return !TextUtils.isEmpty(tv.getText());
//    }
//
//    public boolean checkInput(TextView tv,@StringRes int msg){
//        return checkInput(tv,msg,true);
//    }
//
    public void startShake(View view){
            view.requestFocus();
            view.startAnimation( AnimationUtils.loadAnimation(this,R.anim.shake));
        }



    public void clickRightClear(final TextView tv){
       tv.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent event) {
               switch (event.getAction()){
                   case MotionEvent.ACTION_UP:
                       return clickRightClear(tv,event);
               }
               return false;

           }
       });


    }
    private boolean clickRightClear(TextView tv, MotionEvent event){
        Drawable drawableRight = tv.getCompoundDrawables()[2] ;
        if (drawableRight != null && event.getRawX() >= (tv.getRight() - drawableRight.getBounds().width())) {
            tv.setText(null);
            return true;
        }
        return false;
    }
///////////////////////////////////////////////////////////////
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



}//总结书

