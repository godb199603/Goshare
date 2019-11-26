package com.wzl.goshare.binding;

import android.databinding.BindingAdapter;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * 作者：Create on 2019/8/11 03:18  by  wzl
 * 描述：
 * 最近修改：2019/8/11 03:18 modify by wzl
 */
 public class ImageViewBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView iv,String url){
        Glide.with(iv.getContext())
                .load(url)
                .into(iv);
    }


//    /**
//     * 妹子，电影列表图
//     * @param defaultPicType 妹子：1 电影：0
//     */
//    @BindingAdapter({"android:displayFadeImage","android:defaultPicType"})
//    public static void displayFadeImage(ImageView imageView, String url,int defaultPicType) {
//        displayEspImage(url, imageView, defaultPicType);
//    }


}
