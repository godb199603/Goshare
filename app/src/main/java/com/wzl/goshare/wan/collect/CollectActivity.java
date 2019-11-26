package com.wzl.goshare.wan.collect;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseActivity;
import com.wzl.goshare.databinding.ActivityCollectBinding;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.collect.CollectBean;
import com.wzl.goshare.wan.bean.collect.CollectListData;
import com.wzl.goshare.wan.ui.adapter.HomeAdapter2;
import com.wzl.goshare.wan.ui.adapter.HomeAdapter4;
import com.wzl.goshare.wan.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Create on 2019/9/6 16:22  by  wzl
 * 描述：
 * 最近修改：2019/9/6 16:22 modify by wzl
 */
public class CollectActivity extends BaseActivity{


    private HomeAdapter4 mAdapter;
    private List<CollectBean> listData9=new ArrayList<CollectBean>();
//private List<CollectBean> listData9;
    private ActivityCollectBinding bindingView;
    private CollectViewModel collectViewModel;
    private int mCurPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingView = DataBindingUtil.setContentView(this, R.layout.activity_collect);
        initViewModel();
        initRecyclerView();
        initdata();

        mAdapter = new HomeAdapter4(R.layout.article_item_cardview_layout3,listData9);
        bindingView.rv.setAdapter(mAdapter);


        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CollectBean collectBean = mAdapter.getData().get(position);
                if(view.getId() == R.id.image_collect2) {
                    unMyCollect(collectBean,position);
                    Log.i("tggg",position+"");
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CollectBean collectBean = mAdapter.getData().get(position);
//
//                Log.i("ssss","TTTTTTTT");
//                  if(view.getId() == R.id.image_collect) {
//                      Log.i("gggg",position+"");
//                      clickCollectItem(mArticleBean, position);
////                    addOrCancelCollect(position,homeArticleData);
//     Log.i("tggg",position+"");
//                  }
//                  else {
                String s=collectBean.getLink();
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
//            }
            }
        });




    }//create结束




    public void initViewModel(){
        collectViewModel= ViewModelProviders.of(this).get(CollectViewModel.class);
        bindingView.setViewModel(collectViewModel);
    }
    public void initRecyclerView(){

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);
        bindingView.rv.setLayoutManager(linearLayoutManager);
        // 需加，不然滑动不流畅
        bindingView.rv.setNestedScrollingEnabled(false);
        bindingView.rv.setHasFixedSize(false);
        bindingView.rv.setItemAnimator(new DefaultItemAnimator());
    }
    public void initdata(){
        collectViewModel.getCollect(mCurPage).observe(this, new Observer<CollectListData>() {
            @Override
            public void onChanged(@Nullable CollectListData collectListData) {
                listData9.clear();
                listData9.addAll(collectListData.getDatas());
                Log.i("ss",listData9.toString());
                Log.i("sssssr","uuuuuuu");
                mAdapter.notifyDataSetChanged();
//                mAdapter = new HomeAdapter4(R.layout.article_item_cardview_layout3,listData9);
//                mAdapter = new HomeAdapter4(R.layout.article_item_cardview_layout3,collectListData.getDatas());
//                bindingView.rv.setAdapter(mAdapter);
            }
        });
    }






    /**
     *  取消收藏
     * @param data
     */
    private void unMyCollect(final CollectBean data, final int position){
        collectViewModel.unMyCollect(data.getId(),data.getOriginId()).observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(@Nullable ResponseBody responseBody) {
                listData9.remove(position);
                Toast.makeText(CollectActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
//              mAdapter.notifyItemChanged(position);
                mAdapter.notifyDataSetChanged();
                bindingView.executePendingBindings();
            }
        });
    }







}//总结数
