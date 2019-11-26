package com.wzl.goshare.h;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 作者：Create on 2019/8/1 16:58  by  wzl
 * 描述：
 * 最近修改：2019/8/1 16:58 modify by wzl
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> datas;
    ArrayList<String> titles;

    public MyPageAdapter(FragmentManager fm) {
        super(fm);

    }
//    public MyPageAdapter(ChildFragmentManager fm) {
//        super(fm);
//    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public CharSequence getPageTitle(int position) {
        return titles == null ? null : titles.get(position);
    }

    public void setData(ArrayList<Fragment> datas) {
        this.datas = datas;
    }




    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }


}
