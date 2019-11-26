package com.wzl.goshare.wan.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzl.goshare.R;
import com.wzl.goshare.wan.bean.HomeArticleDataBean;

import java.util.List;

/**
 * 作者：Create on 2019/8/31 16:19  by  wzl
 * 描述：
 * 最近修改：2019/8/31 16:19 modify by wzl
 */
public class HomeAdapter extends BaseQuickAdapter<HomeArticleDataBean, BaseViewHolder> {


    public HomeAdapter(int layoutResId, @Nullable List<HomeArticleDataBean> data) {
        super(layoutResId, data);
    }

    public HomeAdapter(@Nullable List<HomeArticleDataBean> data) {
        super(data);
    }

    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }




    @Override
    protected void convert(BaseViewHolder helper, HomeArticleDataBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_artical_title,item.getTitle())
                .setText(R.id.tv_super_chapterName,item.getSuperChapterName()+" | "+item.getChapterName())
                //.setText(R.id.tv_chapterName,item.getChapterName())
                .setText(R.id.tv_artical_date,item.getNiceDate())
                .addOnClickListener(R.id.image_collect)
                .addOnClickListener(R.id.tv_super_chapterName);
        //.addOnClickListener(R.id.tv_chapterName);
        // tag
        if(item.getTags().size()>0){
            helper.getView(R.id.tv_artical_tag).setVisibility(View.VISIBLE);
            HomeArticleDataBean.TagsBean tagsBean = item.getTags().get(0);
            helper.setText(R.id.tv_artical_tag,tagsBean.getName());
            helper.setTextColor(R.id.tv_artical_tag, ContextCompat.getColor(mContext, R.color.color_green));
            helper.setBackgroundRes(R.id.tv_artical_tag,R.drawable.tag_green_background);
        }else {
            helper.getView(R.id.tv_artical_tag).setVisibility(View.GONE);
        }
//        // tag 新
//        if(item.isFresh()){
//            helper.getView(R.id.tv_artical_new_tag).setVisibility(View.VISIBLE);
//            helper.setText(R.id.tv_artical_new_tag,mContext.getString(R.string.new_tag));
//            helper.setTextColor(R.id.tv_artical_new_tag, ContextCompat.getColor(mContext, android.R.color.holo_red_light));
//            helper.setBackgroundRes(R.id.tv_artical_new_tag,R.drawable.tag_red_background);
//        }else {
//            helper.getView(R.id.tv_artical_new_tag).setVisibility(View.GONE);
//        }
//        //tag 置顶
//        if(1 == item.getType()){
//            helper.getView(R.id.tv_artical_top_tag).setVisibility(View.VISIBLE);
//            helper.setText(R.id.tv_artical_top_tag,mContext.getString(R.string.top_tag));
//            helper.setTextColor(R.id.tv_artical_top_tag, ContextCompat.getColor(mContext, R.color.color_red));
//            helper.setBackgroundRes(R.id.tv_artical_top_tag,R.drawable.tag_top_red_background);
//        }else {
//            helper.getView(R.id.tv_artical_top_tag).setVisibility(View.GONE);
//        }
//        if(item.isCollect()){
//            helper.setImageDrawable(R.id.image_collect,ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_collect_24dp));
//        }else {
//            helper.setImageDrawable(R.id.image_collect,ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp));
//        }
//

    }
}
