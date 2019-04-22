package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/22/2017.
 */

public class orderData  {


    @SerializedName("id")
    String id;
    @SerializedName("date_time")
    String date_time;
    @SerializedName("price")
    String total;
    @SerializedName("Username")
    String username;

    public orderData(String id, String date_time, String total, String username) {
        this.id = id;
        this.date_time = date_time;
        this.total = total;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
