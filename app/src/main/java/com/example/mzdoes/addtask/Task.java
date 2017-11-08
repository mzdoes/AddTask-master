package com.example.mzdoes.addtask;

import android.graphics.Color;

public class Task {

    //instance variables
    private String name;
    private String desc;
    private int color;
    //make a dueDate and dueTime varible asap

    //constructor
    public Task(String name, String desc, Boolean aToDoItem) {
        this.name = name;
        this.desc = desc;
        if (!aToDoItem) {
            //set dueDate and Time
        } else {
            color = Color.rgb(255, 51, 51);
        }
        //default color here
    }


    //methods
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //setColor method using Color.rgb method
    public void setToDoColor() {
        if (color == Color.rgb(255, 51, 51)) {
            color = Color.rgb(112, 219, 112);
        } else if (color == Color.rgb(112, 219, 112)) {
            color = Color.rgb(255, 51, 51);
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
