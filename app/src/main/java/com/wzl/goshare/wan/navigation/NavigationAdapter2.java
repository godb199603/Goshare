package com.wzl.goshare.wan.navigation;


import android.view.View;
import android.view.ViewGroup;

import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseBindAdapter;
import com.wzl.goshare.base.baseadapter.BaseRecyclerViewAdapter;
import com.wzl.goshare.base.baseadapter.BaseRecyclerViewHolder;
import com.wzl.goshare.databinding.ItemNavigationBinding;
import com.wzl.goshare.wan.bean.NaviBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * 作者：Create on 2019/9/8 07:37  by  wzl
 * 描述：
 * 最近修改：2019/9/8 07:37 modify by wzl
 */
public class NavigationAdapter2 extends BaseRecyclerViewAdapter<NaviBean> {



    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(parent, R.layout.item_navigation);
    }


    private class ViewHolder extends BaseRecyclerViewHolder<NaviBean, ItemNavigationBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final NaviBean dataBean, final int position) {
            if (dataBean != null) {
                binding.tvTitle.setSelected(dataBean.isSelected());
                binding.setBean(dataBean);
               binding.tvTitle.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (listener != null) {
                         listener.onSelected(position);
                     }
                 }
             });
            }
        }
    }

    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }

}

