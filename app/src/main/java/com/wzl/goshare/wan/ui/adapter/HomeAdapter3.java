package com.wzl.goshare.wan.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzl.goshare.base.baseadapter.BaseRVHolder;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;

import java.util.List;

/**
 * 作者：Create on 2019/9/4 17:49  by  wzl
 * 描述：
 * 最近修改：2019/9/4 17:49 modify by wzl
 */
public class HomeAdapter3 extends BaseQuickAdapter<HomeArticleDataBean, BaseRVHolder> {
    {
    }

    public HomeAdapter3(int layoutResId, @Nullable List<HomeArticleDataBean> data) {
        super(layoutResId, data);
    }

    public HomeAdapter3(@Nullable List<HomeArticleDataBean> data) {
        super(data);
    }

    public HomeAdapter3(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseRVHolder helper, HomeArticleDataBean item) {

    }
}