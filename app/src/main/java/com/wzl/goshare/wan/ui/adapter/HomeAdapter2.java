package com.wzl.goshare.wan.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzl.goshare.R;
import com.wzl.goshare.base.BaseBindAdapter;
import com.wzl.goshare.base.baseadapter.BaseRVHolder;
import com.wzl.goshare.databinding.ArticleItemCardviewLayout2Binding;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;



import java.util.List;

/**
 * 作者：Create on 2019/9/2 16:42  by  wzl
 * 描述：
 * 最近修改：2019/9/2 16:42 modify by wzl
 */
public class HomeAdapter2 extends BaseBindAdapter<HomeArticleDataBean>{

//    private OnItemClickListener mOnItemClickListener=this.getOnItemClickListener();

//    public interface OnItemClickListener{
//        public void onItemClick(View v, int position);
//    }

//    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
//        this.mOnItemClickListener = mOnItemClickListener;
//    }


//    public OnItemClickListener getOnItemClickListener(){
//        return mOnItemClickListener;
//    }


    public HomeAdapter2(int mLayoutResId, int variable, @Nullable List data) {
        super(mLayoutResId, variable, data);
    }

    public HomeAdapter2(int mLayoutResId, int variable) {
        super(mLayoutResId, variable);
    }


    public HomeAdapter2(int mLayoutResId) {
        super(mLayoutResId);
    }

    public HomeAdapter2(int mLayoutResId,  @Nullable List data) {
        super(mLayoutResId, data);
    }

    @Override
    protected void convert(final BaseRVHolder helper, HomeArticleDataBean item) {
        ArticleItemCardviewLayout2Binding binding =
                (ArticleItemCardviewLayout2Binding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);

//      holder.getBinding().setVariable(BR.data, mDatas.get(position));
//      binding.setVariable(variable, item);
        binding.setBean(item);
        helper.setSelected(R.id.image_collect,item.isCollect());
        helper.addOnClickListener(R.id.image_collect);

//        helper.setOnClickListener(R.id.image_collect, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mOnItemClickListener.onItemClick(this.,view,helper.getLayoutPosition());
//            }
//        });
        dataBinding(helper, binding, item, helper.getLayoutPosition() - getHeaderLayoutCount());
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ArticleItemCardviewLayout2Binding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

}
