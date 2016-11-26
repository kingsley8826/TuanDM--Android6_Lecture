package com.example.tuan_fpt.session91_login_nwc.jsonmodels;

/**
 * Created by Tuan-FPT on 11/25/2016.
 */

public class LoginStatus {
    private int code;
    private String message;

    public LoginStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
