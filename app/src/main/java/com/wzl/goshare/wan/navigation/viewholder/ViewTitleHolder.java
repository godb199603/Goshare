package com.wzl.goshare.wan.navigation.viewholder;

import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.base.baseadapter.BaseRecyclerViewHolder;
import com.wzl.goshare.databinding.ItemNavigationContentBinding;
import com.wzl.goshare.databinding.ItemNavigationTitleBinding;
import com.wzl.goshare.utils.CommonUtils;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;

/**
 * 作者：Create on 2019/9/7 23:08  by  wzl
 * 描述：
 * 最近修改：2019/9/7 23:08 modify by wzl
 */
public class ViewTitleHolder extends BaseRecyclerViewHolder<HomeArticleDataBean, ItemNavigationTitleBinding> {


    public ViewTitleHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    @Override
    public void onBindViewHolder(final HomeArticleDataBean dataBean, final int position) {
        if (dataBean != null) {
            binding.setBean(dataBean);
            if (position == 0) {
                binding.viewLine.setVisibility(View.GONE);
            } else {
                binding.viewLine.setVisibility(View.VISIBLE);
            }
        }
    }

    }//zongjieshu

