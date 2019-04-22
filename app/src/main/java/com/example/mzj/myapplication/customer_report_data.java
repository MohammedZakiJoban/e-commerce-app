package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/22/2017.
 */

public class customer_report_data {




    @SerializedName("username")
    String name;
    @SerializedName("number_of_order")
    String times;
    @SerializedName("total")
    String total;

    public customer_report_data(String name, String times, String total) {
        this.name = name;
        this.times = times;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
