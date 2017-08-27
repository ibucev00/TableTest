package com.example.ivan.tabletest;

/**
 * Created by Ivan on 20.8.2017..
 */

public class ReservationInfo {
    public String name;
    public int number;

    public ReservationInfo() {
    }

    public ReservationInfo(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
