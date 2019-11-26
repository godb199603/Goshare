package com.wzl.goshare.wan.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseBindAdapter;
import com.wzl.goshare.base.BaseFragment;

import com.wzl.goshare.databinding.FragmentWanAndroidBinding;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.HomeArticleListDataBean;
import com.wzl.goshare.wan.ui.adapter.HomeAdapter;
import com.wzl.goshare.wan.ui.adapter.HomeAdapter2;
import com.wzl.goshare.wan.viewmodel.WanAndroidListViewModel;

import java.util.List;

/**
 * 作者：Create on 2019/8/29 16:37  by  wzl
 * 描述：
 * 最近修改：2019/8/29 16:37 modify by wzl
 */
public class HomeFragment extends
       BaseFragment<WanAndroidListViewModel, FragmentWanAndroidBinding> {

//
//    //实现懒加载
//    private boolean mIsPrepared;
    private FragmentWanAndroidBinding bindingView;
    public WanAndroidListViewModel wanAndroidViewModel;
    public HomeAdapter2 mAdapter;





    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_wan_android;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        // 准备就绪
//        mIsPrepared = true;
//        loadData();
        initRecyclerView();
        subscribeUI();
    }


    private void subscribeUI(){

        wanAndroidViewModel.getArticle2();
//        wanAndroidViewModel.listData5.observe(this,  new Observer<ResponseBody<HomeArticleListDataBean>>(){
//            @Override
//            public void onChanged(@Nullable ResponseBody<HomeArticleListDataBean> homeArticleListDataBeanResponseBody) {
////                mAdapter.setData(homeArticleListDataBeanResponseBody);
//                mAdapter.setNewData(homeArticleListDataBeanResponseBody);
//            }
//        });
        wanAndroidViewModel.listData5.observe(this, new Observer<List<HomeArticleDataBean>>() {
            @Override
            public void onChanged(@Nullable List<HomeArticleDataBean> homeArticleDataBeans) {
                mAdapter.setNewData(homeArticleDataBeans);
            }
        });

    }


    private void initRecyclerView() {
        bindingView.homeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        bindingView.homeRecyclerview.setNestedScrollingEnabled(false);
        bindingView.homeRecyclerview.setHasFixedSize(false);
        bindingView.homeRecyclerview.setItemAnimator(new DefaultItemAnimator());

        HomeAdapter2 mAdapter = new HomeAdapter2(R.layout.article_item_cardview_layout2);
        bindingView.homeRecyclerview.setAdapter(mAdapter);
}//initRecyclerview结束




}