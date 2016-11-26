package com.example.tuan_fpt.session93_serializedname.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan-FPT on 11/25/2016.
 */

public class Repository {
    private String name;

    @SerializedName("owner")
    private Owner owner;

    public Repository(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }


    public Owner getOwner() {
        return owner;
    }

}
