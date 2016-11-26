package com.example.tuan_fpt.session93_serializedname.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan-FPT on 11/25/2016.
 */

public class Owner {

    //@SerializedName("login")
    private String login;
    //@SerializedName("avatar_url")
    private String avatar_url;

    public Owner(String login, String avatarURL) {
        this.login = login;
        this.avatar_url = avatarURL;
    }

    public String getLogin() {
        return login;
    }


    public String getAvatarURL() {
        return avatar_url;
    }

}
