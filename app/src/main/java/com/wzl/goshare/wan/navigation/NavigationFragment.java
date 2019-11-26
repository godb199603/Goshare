package com.wzl.goshare.wan.navigation;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.R;
import com.wzl.goshare.databinding.FragmentNaviBinding;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.NaviBean;
import com.wzl.goshare.wan.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * 作者：Create on 2019/9/7 00:24  by  wzl
 * 描述：
 * 最近修改：2019/9/7 00:24 modify by wzl
 */
public class NavigationFragment extends Fragment{

   private NavigationViewModel navigationViewModel;
   private FragmentNaviBinding bindingView;
   private List<NaviBean> list=new ArrayList<NaviBean>();
   private NavigationContentAdapter mContentAdapter;
   private NavigationAdapter navigationAdapter;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager layoutManager;
   private int currentPosition = 0;
    private NavigationAdapter2 navigationAdapter2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navi, null, false);
        bindingView = DataBindingUtil.inflate(inflater, R.layout.fragment_navi, container, false);
//
        return bindingView.getRoot();
    }//create方法结束

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 6, GridLayoutManager.VERTICAL, false);
        navigationViewModel= ViewModelProviders.of(this).get(NavigationViewModel.class);
        bindingView.setItem(navigationViewModel);
//        int i=navigationViewModel.getNavigation();
//
//        Log.i("opooo",i+"");
        initRecycler3();
//        initRecycler1();
        initRecycler2();
        onObserveViewModel();


        int i=navigationViewModel.getNavigation();

        Log.i("opooo",i+"");
    }

    private void initRecycler3() {

        layoutManager = new LinearLayoutManager(this.getActivity());
        bindingView.xrvNavi.setLayoutManager(layoutManager);
        navigationAdapter2 = new NavigationAdapter2();
        bindingView.xrvNavi.setAdapter(navigationAdapter2);
//
//        navigationViewModel.getDataTitle().observe(this, new Observer<List<NaviBean>>() {
//            @Override
//            public void onChanged(@Nullable List<NaviBean> dataBeans) {
//                navigationAdapter2.clear();
//                navigationAdapter2.addAll(dataBeans);
//                navigationAdapter2.notifyDataSetChanged();
//                selectItem(0);
//            }
//        });
        navigationAdapter2.setOnSelectListener(new NavigationAdapter2.OnSelectListener() {
            @Override
            public void onSelected(int position) {
                selectItem(position);
                moveToCenter(position);
                Integer titlePosition = navigationViewModel.getTitlePositions().getValue().get(position);
                gridLayoutManager.scrollToPositionWithOffset(titlePosition, 0);
            }
        });



    }





    private void initRecycler1() {
         layoutManager = new
                LinearLayoutManager(this.getActivity());
         bindingView.xrvNavi.setLayoutManager(layoutManager);

         navigationViewModel.dataTitle.observe(this, new Observer<List<NaviBean>>() {
            @Override
            public void onChanged(@Nullable List<NaviBean> dataBeans) {
                Log.i("yyyf",dataBeans.toString());
                list.addAll(dataBeans);
                Log.i("yyy",dataBeans.toString());
//                mNaviAdapter.clear();
//                mNaviAdapter.addAll(dataBeans);

//                navigationAdapter=new NavigationAdapter(R.layout.item_navigation,list);

//                navigationAdapter=new NavigationAdapter(R.layout.item_navigation,dataBeans);

//                selectItem(0);

            }
        });
//      navigationAdapter=new NavigationAdapter(R.layout.item_navigation,list);
        navigationAdapter=new NavigationAdapter(R.layout.item_navigation,list);
//
        selectItem(1);
        navigationAdapter.setOnSelectListener(new NavigationAdapter.OnSelectListener() {
            @Override
            public void onSelected(int position) {
                selectItem(position);
                moveToCenter(position);
                Integer titlePosition = navigationViewModel.getTitlePositions().getValue().get(position);
                gridLayoutManager.scrollToPositionWithOffset(titlePosition, 0);
            }
        });

    }//init方法结束





    private void initRecycler2() {

//      GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 6, GridLayoutManager.VERTICAL, false);
        bindingView.xrvNaviDetail.setLayoutManager(gridLayoutManager);
        mContentAdapter = new NavigationContentAdapter();
        bindingView.xrvNaviDetail.setAdapter(mContentAdapter);


//        navigationViewModel.getData().observe(this, new Observer<List<HomeArticleDataBean>>() {
//            @Override
//            public void onChanged(@Nullable List<HomeArticleDataBean> list) {
//                if (list != null && list.size() > 0) {
////                  showContentView();
//                    mContentAdapter.clear();
//                    mContentAdapter.addAll(list);
//                    mContentAdapter.notifyDataSetChanged();
////                  mIsFirst = false;
//                }
////                else {
////                    showError();
////                }
//            }
//        });


        bindingView.xrvNaviDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int itemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                Integer integer = navigationViewModel.getHashMap().get(itemPosition);
                if (integer != null && currentPosition != integer) {
                    selectItem(integer);
                    moveToCenter(integer);
                }
            }
        });





    }//inierecyclrview结束
//////////////////////////////////
private void selectItem(int position) {
    if (position < 0 || navigationAdapter2.getData().size() < position) {
        return;
    }
    navigationAdapter2.getData().get(currentPosition).setSelected(false);
    navigationAdapter2.notifyItemChanged(currentPosition);
    currentPosition = position;
    navigationAdapter2.getData().get(position).setSelected(true);
    navigationAdapter2.notifyItemChanged(position);
}

///////////////////////////////
    /**
     * 将当前选中的item居中
     */
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = bindingView.xrvNavi.getChildAt(position - layoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - bindingView.xrvNavi.getHeight() / 2);
            bindingView.xrvNavi.smoothScrollBy(0, y);
        }
    }




    private void onObserveViewModel() {
        // 左侧标题
        navigationViewModel.getDataTitle().observe(this, new Observer<List<NaviBean>>() {
            @Override
            public void onChanged(@Nullable List<NaviBean> dataBeans) {
                navigationAdapter2.clear();
                navigationAdapter2.addAll(dataBeans);
                navigationAdapter2.notifyDataSetChanged();
                selectItem(0);
            }
        });
        // 右侧内容
        navigationViewModel.data.observe(this, new Observer<List<HomeArticleDataBean>>() {
            @Override
            public void onChanged(@Nullable List<HomeArticleDataBean> list) {
                if (list != null && list.size() > 0) {
//                  showContentView();
                    mContentAdapter.clear();
                    mContentAdapter.addAll(list);
                    mContentAdapter.notifyDataSetChanged();
//                  mIsFirst = false;
                }
//                else {
//                    showError();
//                }
            }
        });
    }











}///总结书
