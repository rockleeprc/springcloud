package com.foobar.pojo;

public class Car {
    private String make;
    private Integer number;

    public Car() {
    }

    public Car(String make, Integer number) {
        this.make = make;
        this.number = number;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}