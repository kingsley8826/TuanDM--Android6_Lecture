package com.example.tuan_fpt.session91_login_nwc.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan-FPT on 11/25/2016.
 */

public class Acount {
    private String username;
    private String password;

    public Acount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsename() {
        return username;
    }

    public void setUsename(String usename) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
