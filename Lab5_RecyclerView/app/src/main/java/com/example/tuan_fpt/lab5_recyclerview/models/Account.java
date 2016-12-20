package com.example.tuan_fpt.lab5_recyclerview.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class Account {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String passWord;

    public Account() {
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
