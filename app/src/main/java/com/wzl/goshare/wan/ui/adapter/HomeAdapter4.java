package com.wzl.goshare.wan.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseBindAdapter;
import com.wzl.goshare.base.baseadapter.BaseRVHolder;
import com.wzl.goshare.databinding.ArticleItemCardviewLayout3Binding;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;
import com.wzl.goshare.wan.bean.collect.CollectBean;

import java.util.List;

/**
 * 作者：Create on 2019/9/6 18:33  by  wzl
 * 描述：
 * 最近修改：2019/9/6 18:33 modify by wzl
 */
public class HomeAdapter4 extends BaseBindAdapter<CollectBean> {

//    public HomeAdapter4(int mLayoutResId, int variable, @Nullable List data) {
//        super(mLayoutResId, variable, data);
//    }
//
//    public HomeAdapter4(int mLayoutResId, int variable) {
//        super(mLayoutResId, variable);
//    }
//
//
//    public HomeAdapter4(int mLayoutResId) {
//        super(mLayoutResId);
//    }

    public HomeAdapter4(int mLayoutResId,  @Nullable List data) {
        super(mLayoutResId, data);

    }

    @Override
    protected void convert(BaseRVHolder helper, CollectBean item) {
        ArticleItemCardviewLayout3Binding binding =
                (ArticleItemCardviewLayout3Binding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support2);
//      holder.getBinding().setVariable(BR.data, mDatas.get(position));
//      binding.setVariable(variable, item);
        binding.setBean(item);
        Log.i("KKKKOO",binding.getBean().getAuthor());
//        helper.setSelected(R.id.image_collect,item.isCollect());
        helper.addOnClickListener(R.id.image_collect2);
        dataBinding(helper, binding, item, helper.getLayoutPosition() - getHeaderLayoutCount());
        binding.executePendingBindings();

    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ArticleItemCardviewLayout3Binding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support2, binding);
        return view;
    }

}

