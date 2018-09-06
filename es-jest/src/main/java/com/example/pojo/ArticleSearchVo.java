package com.example.pojo;

import java.io.Serializable;
import java.util.List;

public class ArticleSearchVo implements Serializable {

    List<ArticleSearch> articles;

    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ArticleSearch> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleSearch> articles) {
        this.articles = articles;
    }
}
