package com.example.tuan_fpt.session92_loadimageurl.jsonmodels;

import android.media.Image;

/**
 * Created by Tuan-FPT on 11/26/2016.
 */

public class FoodItem {
    private String detail;
    private int price;
    private String name;
    private String image;

    public FoodItem(String detail, String image, String name, int price) {
        this.detail = detail;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

}
