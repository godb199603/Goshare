package com.wzl.goshare.wan.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzl.goshare.R;
import com.wzl.goshare.databinding.FragmentWanAndroidBinding;
import com.wzl.goshare.wan.api.ResponseBody;
import com.wzl.goshare.wan.bean.BannerItemBean;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.HomeArticleListDataBean;
import com.wzl.goshare.wan.bean.collect.CollectListData;
import com.wzl.goshare.wan.collect.CollectActivity;
import com.wzl.goshare.wan.collect.CollectViewModel;
import com.wzl.goshare.wan.ui.adapter.HomeAdapter2;
import com.wzl.goshare.wan.viewmodel.WanAndroidListViewModel;
import com.wzl.goshare.webview.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Create on 2019/9/4 00:58  by  wzl
 * 描述：
 * 最近修改：2019/9/4 00:58 modify by wzl
 */
public class HomeFragment2 extends Fragment {
    public static final String KEY_IS_COLLECT = "key_is_collect";
    public WanAndroidListViewModel wanAndroidViewModel;
    public FragmentWanAndroidBinding mBinding;
    public HomeAdapter2 mAdapter;
    private int mCurPage = 1;
    private List<HomeArticleDataBean> listData = new ArrayList<HomeArticleDataBean>();
    ;
    private List<BannerItemBean> listBanner;
    private Banner banner;
    public CollectViewModel collectViewModel;
    private Context mContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext=this.getActivity();
        View view = inflater.inflate(R.layout.fragment_wan_android, null, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wan_android, container, false);
        return mBinding.getRoot();
    }//create方法结束

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wanAndroidViewModel = ViewModelProviders.of(this).get(WanAndroidListViewModel.class);
        collectViewModel = ViewModelProviders.of(this).get(CollectViewModel.class);
        Log.i("FFFFF", wanAndroidViewModel.toString());
        mBinding.setItem(wanAndroidViewModel);
        initRecyclerView();

        Log.i("FFFFFH", wanAndroidViewModel.toString());
//        wanAndroidViewModel.loadData();
//        subscribeUI();

        subscribeUI2();
        mAdapter = new HomeAdapter2(R.layout.article_item_cardview_layout2, listData);
        mBinding.homeRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                HomeArticleDataBean mArticleBean = mAdapter.getData().get(position);
                if (view.getId() == R.id.image_collect) {
                    clickCollectItem(mArticleBean, position);
                    Log.i("tggg", position + "");
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeArticleDataBean mArticleBean = mAdapter.getData().get(position);
                WebViewActivity.loadUrl(view.getContext(), mArticleBean.getLink(), mArticleBean.getTitle());
//            }
            }
        });


//      HeaderWanAndroidBinding headerBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.header_wan_android, null, false);
        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.header_wan_android, null);
        banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        getBanner();
        mAdapter.addHeaderView(view);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (listBanner != null) {
                    BannerItemBean bean = listBanner.get(position);
                    String s = bean.getUrl();
                    Uri uri = Uri.parse(s);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });


    }     //   onActivityCreated结束

    //////////////////////////////////////////////////////
    private void subscribeUI() {
        wanAndroidViewModel.listData5.observe(this, new Observer<List<HomeArticleDataBean>>() {
            @Override
            public void onChanged(@Nullable final List<HomeArticleDataBean> homeArticleDataBeans) {
//                mAdapter.setNewData(homeArticleDataBeans);
                homeArticleDataBeans.clear();
                Log.i("FFFFFHJ88", homeArticleDataBeans.toString());
                HomeAdapter2 mAdapter = new HomeAdapter2(R.layout.article_item_cardview_layout2, homeArticleDataBeans);
                mBinding.homeRecyclerview.setAdapter(mAdapter);

//               mAdapter.addHeaderView()
                Log.i("KKKK", mAdapter.getData().toString());
            }
        });
    }//subscribe结束


    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void subscribeUI2() {

        wanAndroidViewModel.getArticle3(mCurPage).observe(this, mListObserver);

    }

    Observer mListObserver = new Observer<HomeArticleListDataBean>() {
        @Override
        public void onChanged(@Nullable HomeArticleListDataBean homeArticleDataBeans) {
            listData.addAll(homeArticleDataBeans.getDatas());
//            refreshListView(homeArticleDataBeans);
        }
    };

    private void refreshListView(HomeArticleListDataBean data) {
//        listData=new ArrayList<HomeArticleDataBean>();
        if (data.getCurPage() == 1) {

            listData.clear();

        }
        mCurPage = data.getCurPage() + 1;
        listData.addAll(data.getDatas());
//
//        if(mAdapter.getItemCount()<data.getTotal()){
//            mBinding.homeRecyclerview.setDirection(SuperSwipeRefreshLayout.Direction.BOTH);
//        }else{
//            mBinding.homeRecyclerview.setDirection(SuperSwipeRefreshLayout.Direction.TOP);
//        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initRecyclerView() {
        mBinding.homeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        mBinding.homeRecyclerview.setNestedScrollingEnabled(false);
        mBinding.homeRecyclerview.setHasFixedSize(false);
        mBinding.homeRecyclerview.setItemAnimator(new DefaultItemAnimator());

//        HomeAdapter2 mAdapter = new HomeAdapter2(R.layout.article_item_cardview_layout2,);
//        mBinding.homeRecyclerview.setAdapter(mAdapter);
    }//initRecyclerview结束


    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).error(R.mipmap.ic_launcher).into(imageView);
        }

    }

///////////////////////////////////////////////

    private void getBanner() {

        wanAndroidViewModel.getBanner().observe(this, new Observer<List<BannerItemBean>>() {
            @Override
            public void onChanged(@Nullable List<BannerItemBean> data) {
                if (data != null && data.size() > 0) {
                    listBanner = data;
                    List<String> urls = new ArrayList<>();
                    List<String> titles = new ArrayList<>();
                    int size = data.size();
                    for (int i = 0; i < size; i++) {
                        BannerItemBean bean = data.get(i);
                        titles.add(bean.getDesc());
                        urls.add(bean.getImagePath());
                    }
                    banner.setVisibility(View.VISIBLE);
                    banner.setBannerTitles(titles);
                    banner.setImages(urls);
                    banner.start();

                } else {
                    banner.setVisibility(View.GONE);
                }


            }
        });

    }

///////////////////    ///////////////////////

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

//////////////////////////收藏


    /**
     * 点击列表item的收藏
     *
     * @param data
     */
    private void clickCollectItem(HomeArticleDataBean data, int position) {
//        if(checkLogin()){
        if (data.isCollect()) {
            unCollect(data, position);
        } else {
            collect(data, position);
        }
    }
//    }


    /**
     * 取消收藏
     *
     * @param data
     */
    private void unCollect(final HomeArticleDataBean data, final int position) {
        collectViewModel.unCollect(data.getId()).observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(@Nullable ResponseBody responseBody) {
                data.setCollect(false);
                Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
//              mAdapter.notifyItemChanged(position);
                mAdapter.notifyDataSetChanged();
                mBinding.executePendingBindings();
            }
        });
    }

    /**
     * 收藏
     *
     * @param data
     */
    private void collect(final HomeArticleDataBean data, final int position) {
        collectViewModel.collect(data).observe(this, new Observer<CollectListData>() {
            @Override
            public void onChanged(@Nullable CollectListData collectListData) {
                data.setCollect(true);
//                mAdapter.notifyItemChanged(position);

                Boolean a = mAdapter.getData().get(position).isCollect();
                Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                mBinding.executePendingBindings();
            }
        });

    }
//////////////////////////////////////////


}///总结书