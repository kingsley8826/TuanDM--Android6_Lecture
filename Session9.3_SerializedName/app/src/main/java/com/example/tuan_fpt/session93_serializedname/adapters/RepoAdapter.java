package com.example.tuan_fpt.session93_serializedname.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tuan_fpt.session93_serializedname.R;
import com.example.tuan_fpt.session93_serializedname.jsonmodels.Repository;

import java.util.List;

/**
 * Created by Tuan-FPT on 11/26/2016.
 */

public class RepoAdapter extends ArrayAdapter<Repository> {

    public RepoAdapter(Context context, int resource, List<Repository> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(R.layout.list_item_repository, parent, false);

        Repository repoItem = getItem(position);

        ImageView ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvLogin = (TextView) convertView.findViewById(R.id.tv_login);

        Glide.with(this.getContext()).load(repoItem.getOwner().getAvatarURL()).into(ivAvatar);
        tvName.setText(repoItem.getName());
        tvLogin.setText(repoItem.getOwner().getLogin());

        return convertView;
    }
}
