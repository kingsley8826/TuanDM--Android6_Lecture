package com.example.tuan_fpt.lab5_recyclerview.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class Status {
    public static String TOKEN_DATA = "token_data";
    public static String TOKEN_KEY = "token_key";
    @SerializedName("token")
    private String token;
    @SerializedName("result")
    private int result;
    @SerializedName("message")
    private String message;

    public Status(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public Status(String token, int result, String message) {
        this.token = token;
        this.result = result;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
    public static String TOKEN;

    @Override
    public String toString() {
        return "Status{" +
                "token='" + token + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
