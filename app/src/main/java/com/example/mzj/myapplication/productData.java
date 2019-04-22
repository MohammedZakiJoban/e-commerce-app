package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/6/2017.
 */

public class productData {

    public productData() {
    }

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private int price;
    @SerializedName("stock")
    private int stock;
    @SerializedName("picture")
    private String picture;

    public productData(int id, String name, int price, int stock, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
