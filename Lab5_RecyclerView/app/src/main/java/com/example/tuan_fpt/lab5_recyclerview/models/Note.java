package com.example.tuan_fpt.lab5_recyclerview.models;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class Note {
    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private String title;
    @SerializedName("color")
    private String color;

    public Note(String content, String title, String color) {
        this.content = content;
        this.title = title;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public static List<Note> noteList = new ArrayList<>(
    );

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
