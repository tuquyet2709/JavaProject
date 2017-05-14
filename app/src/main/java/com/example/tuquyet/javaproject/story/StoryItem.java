package com.example.tuquyet.javaproject.story;

/**
 * Created by tuquyet on 29/04/2017.
 */

public class StoryItem {
    private int stID;
    private String mName;
    private String mDescreption;

    public StoryItem(int stID, String mName, String mDescreption) {
        this.stID = stID;
        this.mName = mName;
        this.mDescreption = mDescreption;
    }

    public int getStID() {
        return stID;
    }

    public void setStID(int stID) {
        this.stID = stID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescreption() {
        return mDescreption;
    }

    public void setmDescreption(String mDescreption) {
        this.mDescreption = mDescreption;
    }
}

