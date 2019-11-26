package com.wzl.goshare.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.wzl.goshare.utils.StringUtils;
import com.wzl.goshare.utils.ToastUtils;

/**
 * 作者：Create on 2019/8/27 23:24  by  wzl
 * 描述：
 * 最近修改：2019/8/27 23:24 modify by wzl
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public Context getContext(){
        return this;
    }


    public void showToast(CharSequence text){
        if(StringUtils.isNotBlank(text)) {
            ToastUtils.showToast(getContext(), text);
        }
    }

    public void showToast(@StringRes int resId){
        ToastUtils.showToast(getContext(),resId);
    }


}
