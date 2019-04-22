package com.example.mzj.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MZJ on 11/5/2017.
 */

public class data {


    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;


    public data (int id,String username,String password){


       this.setId(id);
        this.setUsername(username);
        this.setPassword(password);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
