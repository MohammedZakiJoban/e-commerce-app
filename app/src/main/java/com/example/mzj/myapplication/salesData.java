package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/20/2017.
 */

public class salesData {

    @SerializedName("DATE")
     String date;
    @SerializedName("total_sales")
    String sales;

    public salesData(String date, String sales) {
        this.date = date;
        this.sales = sales;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
}
