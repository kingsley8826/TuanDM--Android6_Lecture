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
    @SerializedName("completed")
    private boolean completed;
    @SerializedName("_id")
    private Oid id;

    public Note(String content, String title, String color, boolean completed, Oid id) {
        this.content = content;
        this.title = title;
        this.color = color;
        this.completed = completed;
        this.id = id;
    }

    public Note(String title, String content, String color, boolean completed) {
        this.title = title;
        this.content = content;
        this.color = color;
        this.completed = completed;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Oid getId() {
        return id;
    }

    public static List<Note> completeNoteList = new ArrayList<>(
    );

    public static List<Note> incompleteNoteList = new ArrayList<>(
    );

    public class Oid {
        @SerializedName("$oid")
        private String id;
        public Oid(String id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return id;
        }
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", completed=" + completed +
                ", id=" + id +
                '}';
    }
}
