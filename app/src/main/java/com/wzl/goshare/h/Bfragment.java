package com.wzl.goshare.h;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wzl.goshare.R;
import com.wzl.goshare.gank.ui.WelfareFragment;
import com.wzl.goshare.wan.navigation.NavigationFragment;
import com.wzl.goshare.wan.ui.HomeFragment;
import com.wzl.goshare.wan.ui.HomeFragment2;

import java.util.ArrayList;

/**
 * 作者：Create on 2019/8/2 16:46  by  wzl
 * 描述：
 * 最近修改：2019/8/2 16:46 modify by wzl
 */
public class Bfragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.myfragment2, container,false);
        return  inflater.inflate(R.layout.myfragment2,container,false);


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyPageAdapter myPageAdapter = new MyPageAdapter(this.getChildFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new Afragment());
        datas.add(new HomeFragment2());
        datas.add(new NavigationFragment());
        datas.add(new WelfareFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("玩安卓");
        titles.add("知识体系");
        titles.add("导航");
        titles.add("项目");
        myPageAdapter.setTitles(titles);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout2);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager2);
// 将适配器设置进ViewPager
        viewPager.setAdapter(myPageAdapter);
// 将ViewPager与TabLayout相关联
        tabLayout.setupWithViewPager(viewPager);

    }



}
