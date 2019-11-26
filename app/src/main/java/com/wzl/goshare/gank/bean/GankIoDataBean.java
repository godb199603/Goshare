package com.wzl.goshare.gank.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/24.
 */

public class GankIoDataBean extends BaseObservable implements Serializable {


    private boolean error;
    /**
     * _id : 5832662b421aa929b0f34e99
     * createdAt : 2016-11-21T11:12:43.567Z
     * desc :  深入Android渲染机制
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : web
     * type : Android
     * url : http://blog.csdn.net/ccj659/article/details/53219288
     * used : true
     * who : Chauncey
     */


    private List<ResultBean> results;

    public static class ResultBean extends BaseObservable implements Serializable {

        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        @Bindable
        public String getCreatedAt() {
            return createdAt;
        }

        @Bindable
        public String getDesc() {
            return desc;
        }

        @Bindable
        public String getPublishedAt() {
            return publishedAt;
        }

        @Bindable
        public String getSource() {
            return source;
        }

        @Bindable
        public String getType() {
            return type;
        }

        @Bindable
        public String getUrl() {
            return url;
        }

        @Bindable
        public String getWho() {
            return who;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public boolean isError() {
        return error;
    }

    @Bindable
    public List<ResultBean> getResults() {
        return results;
    }

    public void setResults(List<ResultBean> results) {
        this.results = results;
    }
}
