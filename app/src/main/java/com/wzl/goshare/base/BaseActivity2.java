package com.wzl.goshare.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzl.goshare.utils.ClassUtil;

/**
 * 作者：Create on 2019/9/5 17:10  by  wzl
 * 描述：
 * 最近修改：2019/9/5 17:10 modify by wzl
 */
public abstract class BaseActivity2<VM extends ViewModel, SV extends ViewDataBinding> extends AppCompatActivity {
    // ViewModel
    protected VM viewModel;
    // 布局view
    protected SV bindingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {}

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.viewModel = ViewModelProviders.of(this).get(viewModelClass);
        }
    }






}
