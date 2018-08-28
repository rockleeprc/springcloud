package com.example.pojo;

import io.searchbox.annotations.JestId;

import java.util.Date;

public class User {

    @JestId
    private Integer id;
    private String name;
    private Date birth;
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User() {
        super();
    }


    public User(Integer id, String name, Date birth,String status) {
        super();
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.status=status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", status='" + status + '\'' +
                '}';
    }
}
