package com.wzl.goshare.wan.bean;

import java.util.List;

/**
 * 作者：Create on 2019/9/7 02:43  by  wzl
 * 描述：
 * 最近修改：2019/9/7 02:43 modify by wzl
 */
public class NaviBean {
    private int cid;

    private boolean selected;
    private String name;

    private List<HomeArticleDataBean> articles;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HomeArticleDataBean> getArticles() {
        return articles;
    }

    public void setArticles(List<HomeArticleDataBean> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NaviBean naviBean = (NaviBean) o;

        if (cid != naviBean.cid) return false;
        return name != null ? name.equals(naviBean.name) : naviBean.name == null;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NaviBean{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }
}
