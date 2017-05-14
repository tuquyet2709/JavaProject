package com.example.tuquyet.javaproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.tuquyet.javaproject.story.StoryItem;

import java.util.ArrayList;

/**
 * Created by tuquyet on 01/05/2017.
 */

public class SQLiteStoryList extends DatabaseController {
    public SQLiteStoryList(Context con) {
        super(con);
    }

    public ArrayList<StoryItem> getStoryLists() {
        ArrayList<StoryItem> arrayList = new ArrayList<>();
        try {
            openDataBase();
            Cursor cursor = database.rawQuery("SELECT \"stID\", \"stName\", \"stDescribe\" FROM \"kimdung\"", null);
            StoryItem storyList;
            while (cursor.moveToNext()) {
                storyList = new StoryItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                arrayList.add(storyList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrayList;
    }
}