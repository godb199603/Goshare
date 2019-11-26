package com.wzl.goshare.wan.navigation.viewholder;

import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.base.baseadapter.BaseRecyclerViewHolder;

import com.wzl.goshare.databinding.ItemNavigationContentBinding;
import com.wzl.goshare.utils.CommonUtils;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.webview.WebViewActivity;

/**
 * 作者：Create on 2019/9/7 23:08  by  wzl
 * 描述：
 * 最近修改：2019/9/7 23:08 modify by wzl
 */
public class ViewContentHolder extends BaseRecyclerViewHolder<HomeArticleDataBean,ItemNavigationContentBinding> {


    public ViewContentHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    @Override
    public void onBindViewHolder(final HomeArticleDataBean dataBean, final int position) {
        if (dataBean != null) {
            binding.setBean(dataBean);
            binding.tvNaviTag.setTextColor(CommonUtils.randomColor());
            binding.tvNaviTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebViewActivity.loadUrl(view.getContext(), dataBean.getLink(), dataBean.getTitle());
                }
            });
        }
    }



}//ZONGJIESHU
