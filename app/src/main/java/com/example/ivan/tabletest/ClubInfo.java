package com.example.ivan.tabletest;

/**
 * Created by Ivan on 18.8.2017..
 */

public class ClubInfo {

    private String name;
    private String address;
    private int tableNumber;
    private String image;


    public ClubInfo() {
    }

    public ClubInfo(String name, String address, int tableNumber, String image) {
        this.name = name;
        this.address = address;
        this.tableNumber = tableNumber;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
