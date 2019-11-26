package com.wzl.goshare.gank.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Create on 2019/9/12 01:43  by  wzl
 * 描述：
 * 最近修改：2019/9/12 01:43 modify by wzl
 */
public class GankIoDayBean implements Serializable {

    private List<String> category;
    private boolean error;
    private ResultsBean results;


    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }




    public static class ResultsBean {
        /**
         * _id : 56cc6d23421aa95caa707a69
         * createdAt : 2015-08-06T07:15:52.65Z
         * desc : 类似Link Bubble的悬浮式操作设计
         * images : ["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"]
         * source : web
         * publishedAt : 2015-08-07T03:57:48.45Z
         * type : Android
         * url : https://github.com/recruit-lifestyle/FloatingView
         * used : true
         * who : mthli
         */


        private List<AndroidBean> Android;

        private List<AndroidBean> iOS;

        private List<AndroidBean> front;

        private List<AndroidBean> app;

        public void setAndroid(List<AndroidBean> android) {
            Android = android;
        }

        public void setiOS(List<AndroidBean> iOS) {
            this.iOS = iOS;
        }

        public void setFront(List<AndroidBean> front) {
            this.front = front;
        }

        public void setApp(List<AndroidBean> app) {
            this.app = app;
        }

        public void setRestMovie(List<AndroidBean> restMovie) {
            this.restMovie = restMovie;
        }

        public void setResource(List<AndroidBean> resource) {
            this.resource = resource;
        }

        public void setRecommend(List<AndroidBean> recommend) {
            this.recommend = recommend;
        }

        public void setWelfare(List<AndroidBean> welfare) {
            this.welfare = welfare;
        }

        private List<AndroidBean> restMovie;

        private List<AndroidBean> resource;

        private List<AndroidBean> recommend;

        private List<AndroidBean> welfare;


    }



}
