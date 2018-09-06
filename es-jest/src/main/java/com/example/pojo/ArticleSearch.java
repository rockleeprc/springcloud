package com.example.pojo;

import io.searchbox.annotations.JestId;

import java.io.Serializable;
import java.util.Date;

public class ArticleSearch implements Serializable {


    @JestId
    private Integer articleNationId;
    private Integer articleNationDel;
    private Date articleNationUpdateTime;
    private String articleNationName;
    private String articleNationContent;

    private Integer articleId;

    private Integer nationId;
    private String nationCode;

    private Integer siteId;
    private String domain;

    private Integer categoryId;
    private String categoryName;
    private Integer categoryDel;

    private Integer sectionsId;
    private String sectionsName;
    private Integer sectionsDel;

    private Date articleNationCreateTime;
    private String timeDifference;

    public ArticleSearch() {
    }

    public ArticleSearch(Integer articleNationId, Integer articleNationDel, Date articleNationUpdateTime, String articleNationName, String articleNationContent, Integer articleId, Integer nationId, String nationCode, Integer siteId, String domain, Integer categoryId, String categoryName, Integer categoryDel, Integer sectionsId, String sectionsName, Integer sectionsDel) {
        this.articleNationId = articleNationId;
        this.articleNationDel = articleNationDel;
        this.articleNationUpdateTime = articleNationUpdateTime;
        this.articleNationName = articleNationName;
        this.articleNationContent = articleNationContent;
        this.articleId = articleId;
        this.nationId = nationId;
        this.nationCode = nationCode;
        this.siteId = siteId;
        this.domain = domain;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDel = categoryDel;
        this.sectionsId = sectionsId;
        this.sectionsName = sectionsName;
        this.sectionsDel = sectionsDel;
    }

    public Date getArticleNationCreateTime() {
        return articleNationCreateTime;
    }

    public void setArticleNationCreateTime(Date articleNationCreateTime) {
        this.articleNationCreateTime = articleNationCreateTime;
    }

    public String getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(String timeDifference) {
        this.timeDifference = timeDifference;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleNationId=" + articleNationId +
                ", articleNationDel=" + articleNationDel +
                ", articleNationUpdateTime=" + articleNationUpdateTime +
                ", articleNationName='" + articleNationName + '\'' +
                ", articleNationContent='" + articleNationContent + '\'' +
                ", articleId=" + articleId +
                ", nationId=" + nationId +
                ", nationCode='" + nationCode + '\'' +
                ", siteId=" + siteId +
                ", domain='" + domain + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDel=" + categoryDel +
                ", sectionsId=" + sectionsId +
                ", sectionsName='" + sectionsName + '\'' +
                ", sectionsDel=" + sectionsDel +
                '}';
    }




    public Integer getArticleNationId() {
        return articleNationId;
    }

    public void setArticleNationId(Integer articleNationId) {
        this.articleNationId = articleNationId;
    }

    public Integer getArticleNationDel() {
        return articleNationDel;
    }

    public void setArticleNationDel(Integer articleNationDel) {
        this.articleNationDel = articleNationDel;
    }

    public Date getArticleNationUpdateTime() {
        return articleNationUpdateTime;
    }

    public void setArticleNationUpdateTime(Date articleNationUpdateTime) {
        this.articleNationUpdateTime = articleNationUpdateTime;
    }

    public String getArticleNationName() {
        return articleNationName;
    }

    public void setArticleNationName(String articleNationName) {
        this.articleNationName = articleNationName;
    }

    public String getArticleNationContent() {
        return articleNationContent;
    }

    public void setArticleNationContent(String articleNationContent) {
        this.articleNationContent = articleNationContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryDel() {
        return categoryDel;
    }

    public void setCategoryDel(Integer categoryDel) {
        this.categoryDel = categoryDel;
    }

    public Integer getSectionsId() {
        return sectionsId;
    }

    public void setSectionsId(Integer sectionsId) {
        this.sectionsId = sectionsId;
    }

    public String getSectionsName() {
        return sectionsName;
    }

    public void setSectionsName(String sectionsName) {
        this.sectionsName = sectionsName;
    }

    public Integer getSectionsDel() {
        return sectionsDel;
    }

    public void setSectionsDel(Integer sectionsDel) {
        this.sectionsDel = sectionsDel;
    }
}
