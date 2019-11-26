package com.wzl.goshare.gank.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.R;
import com.wzl.goshare.base.baseadapter.OnItemClickListener;
import com.wzl.goshare.databinding.FragmentWelfareBinding;
import com.wzl.goshare.film.ViewBigImageActivity;
import com.wzl.goshare.gank.WelfareAdapter;
import com.wzl.goshare.gank.WelfareViewModel;
import com.wzl.goshare.gank.bean.GankIoDataBean;
import com.wzl.goshare.wan.viewmodel.WanAndroidListViewModel;

import java.util.ArrayList;

/**
 * 作者：Create on 2019/9/12 00:58  by  wzl
 * 描述：
 * 最近修改：2019/9/12 00:58 modify by wzl
 */
public class WelfareFragment extends Fragment {

private FragmentWelfareBinding mBinding;
private WelfareViewModel welfareViewModel;
private WelfareAdapter mWelfareAdapter;
    private ArrayList<String> imgList = new ArrayList<>();
    private ArrayList<String> imgTitleList = new ArrayList<>();
    private Context mContext;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare, null, false);
        mContext=this.getActivity();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welfare, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        welfareViewModel = ViewModelProviders.of(this).get(WelfareViewModel.class);
        mBinding.setBean(welfareViewModel);

        initRecycleView();
//        loadWelfareData();

        super.onActivityCreated(savedInstanceState);
    }

    private void initRecycleView() {

        mWelfareAdapter = new WelfareAdapter();
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        mBinding.fuliRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mBinding.fuliRecyclerview.setHasFixedSize(true);
        mBinding.fuliRecyclerview.setItemAnimator(null);
        welfareViewModel.loadWelfareData().observe(this, new Observer<GankIoDataBean>() {
            @Override
            public void onChanged(@Nullable GankIoDataBean gankIoDataBean) {
//                mWelfareAdapter.clear();
                Log.i("iiii",gankIoDataBean.getResults().toString());
                mWelfareAdapter.addAll(gankIoDataBean.getResults());

                mWelfareAdapter.notifyDataSetChanged();
            }
        });
        mBinding.fuliRecyclerview.setAdapter(mWelfareAdapter);
        mWelfareAdapter.setOnItemClickListener(new OnItemClickListener<GankIoDataBean.ResultBean>() {
            @Override
            public void onClick(GankIoDataBean.ResultBean resultBean, int position) {
                ViewBigImageActivity.startImageList(getContext(), position, imgList, imgTitleList);
            }
        });


        welfareViewModel.getImageAndTitle().observe(this, new Observer<ArrayList<ArrayList<String>>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ArrayList<String>> arrayLists) {
                if (arrayLists != null && arrayLists.size() == 2) {
                    imgList.addAll(arrayLists.get(0));
                    imgTitleList.addAll(arrayLists.get(1));
                }
            }
        });


    }

}//zongjieshu