package com.example.tuan_fpt.session92_loadimageurl.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tuan_fpt.session92_loadimageurl.R;
import com.example.tuan_fpt.session92_loadimageurl.jsonmodels.FoodItem;


import java.util.List;


/**
 * Created by Tuan-FPT on 11/26/2016.
 */

public class FoodApdater extends ArrayAdapter<FoodItem> {
    public FoodApdater(Context context, int resource, List<FoodItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //gr refference
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(R.layout.list_item_food, parent, false);

        FoodItem foodItem = getItem(position);

        ImageView ivFood = (ImageView) convertView.findViewById(R.id.iv_food);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvDetail = (TextView) convertView.findViewById(R.id.tv_detail);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);

        Glide.with(this.getContext()).load(foodItem.getImage()).into(ivFood);
        tvName.setText(foodItem.getName());
        tvDetail.setText(foodItem.getDetail());
        tvPrice.setText(String.format("%s  VND", String.valueOf(foodItem.getPrice())));

        return convertView;
    }


}
