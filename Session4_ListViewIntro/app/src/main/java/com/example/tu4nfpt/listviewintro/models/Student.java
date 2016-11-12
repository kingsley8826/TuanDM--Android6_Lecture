package com.example.tu4nfpt.listviewintro.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tu4nFPT on 08/11/2016.
 */

public class Student implements Serializable{
    private String name;
    private String gender;
    private int age;
    public static final int OP_ADD = 1;
    public static final int OP_UPDATE = 0;

    public Student() {
    }

    public Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
    private static final Student[] NAMES = {
            new Student("Tuấn", "Male", 20),
            new Student("Lựu", "Female", 17),
            new Student("Nghĩa", "Male", 19),
            new Student("Trung", "Male", 19)
    };
    public static ArrayList<Student> list = new ArrayList<>(Arrays.asList(NAMES));
}
