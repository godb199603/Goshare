package com.wzl.goshare.wan.navigation;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseBindAdapter;
import com.wzl.goshare.base.baseadapter.BaseRVHolder;
import com.wzl.goshare.databinding.ArticleItemCardviewLayout3Binding;
import com.wzl.goshare.databinding.ItemNavigationBinding;
import com.wzl.goshare.wan.bean.NaviBean;
import com.wzl.goshare.wan.bean.collect.CollectBean;

import java.util.List;

/**
 * 作者：Create on 2019/9/7 17:05  by  wzl
 * 描述：
 * 最近修改：2019/9/7 17:05 modify by wzl
 */
public class NavigationAdapter extends BaseBindAdapter<NaviBean>{

    private ItemNavigationBinding binding;

    public NavigationAdapter(int mLayoutResId, @Nullable List<NaviBean> data) {
        super(mLayoutResId, data);
    }

    @Override
    protected void convert(BaseRVHolder helper, NaviBean item) {
         binding =
                (ItemNavigationBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support3);
//      holder.getBinding().setVariable(BR.data, mDatas.get(position));
//      binding.setVariable(variable, item);
        binding.setBean(item);
        dataBinding(helper, binding, item, helper.getLayoutPosition() - getHeaderLayoutCount());
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ItemNavigationBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support3, binding);
        return view;
    }

    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }



}
