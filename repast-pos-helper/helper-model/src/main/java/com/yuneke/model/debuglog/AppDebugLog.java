package com.yuneke.model.debuglog;

import java.io.Serializable;
import java.util.Date;

public class AppDebugLog implements Serializable {
    private Long id;
    private Long userId;
    private Long merchantId;
    private Integer type;
    private String detail;
    private Date createTime;

    public AppDebugLog(){}

    public AppDebugLog(Long userId, Long merchantId, Integer type, String detail, Date createTime) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.type = type;
        this.detail = detail;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
