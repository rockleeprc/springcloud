package com.example.model;

import java.io.Serializable;
import java.util.Date;

/*
* 
* gen by beetlsql 2017-03-18
*/
public class User implements Serializable {
    private Integer id;
    private Integer departmentId;
    //部门
    private String name;
    //创建时间
    private Date createTime;

    public User() {
    }


    public User(Integer id, String name, Integer departmentId) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
    }

    public User(Integer departmentId, String name, Date createTime) {
        this.departmentId = departmentId;
        this.name = name;
        this.createTime = createTime;
    }

    public User(Integer id, String name, Integer departmentId, Date createTime) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
