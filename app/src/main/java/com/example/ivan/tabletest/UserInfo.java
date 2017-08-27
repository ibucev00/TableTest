package com.example.ivan.tabletest;

/**
 * Created by Ivan on 10.8.2017..
 */

public class UserInfo {
    public String name;
    public String lastName;
    public String phone;
    public String address;

    public UserInfo() {

    }

    public UserInfo(String name, String lastName, String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}


