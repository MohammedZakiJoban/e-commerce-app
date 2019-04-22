package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/22/2017.
 */

public class inventoryData {




    @SerializedName("name")
    String name;
    @SerializedName("stock")
    int stock;

    public inventoryData(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
