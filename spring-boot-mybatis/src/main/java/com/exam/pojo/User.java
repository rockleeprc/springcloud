package com.exam.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
public class User implements Serializable {

    private int id;
    private String userName;
    private String userAge;
    private String userAddress;
    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", userAge=" + userAge + ", userAddress=" + userAddress
                + "]";
    }

}
