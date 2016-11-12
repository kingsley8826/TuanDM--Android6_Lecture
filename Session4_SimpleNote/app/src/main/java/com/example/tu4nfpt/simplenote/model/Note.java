package com.example.tu4nfpt.simplenote.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tu4nFPT on 12/11/2016.
 */

public class Note implements Serializable{

    public static final int OP_ADD = 1;
    public static final int OP_UPDATE = 0;

    private String title;
    private String content;

    public Note() {
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }

    private static Note[] nodes = {
            new Note("Tools for learning Android",
                    "- PC or MAC with at least 4GB RAM\n" +
                    "- Android Studio\n" +
                    "- Genymotion or other simulation tools\n")
    };
    public static ArrayList<Note> list = new ArrayList<>(Arrays.asList(nodes));

}
