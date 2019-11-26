package com.wzl.goshare.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wzl.goshare.R;
import com.wzl.goshare.base.baseadapter.BaseRVHolder;

import java.util.List;

/**
 * 作者：Create on 2019/9/7 18:20  by  wzl
 * 描述：
 * 最近修改：2019/9/7 18:20 modify by wzl
 */
public class BaseBindAdapter2<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseRVHolder> {
    protected int variable;//BR
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseBindAdapter2(List data) {
        super(data);


    }


    @Override
    protected void convert(BaseRVHolder helper, T item) {
        ViewDataBinding binding =
                (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        binding.setVariable(variable, item);
//        dataBinding(helper, binding, item, helper.getLayoutPosition() - getHeaderLayoutCount());
        binding.executePendingBindings();
    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }


}
