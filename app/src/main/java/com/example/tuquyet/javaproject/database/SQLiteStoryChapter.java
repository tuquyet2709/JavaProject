package com.example.tuquyet.javaproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.example.tuquyet.javaproject.chapter.ChapterItem;

import java.util.ArrayList;

/**
 * Created by tuquyet on 01/05/2017.
 */

public class SQLiteStoryChapter extends DatabaseController {
    public SQLiteStoryChapter(Context con) {
        super(con);
    }

    public ArrayList<ChapterItem> getStoryChapters(int id) {
        ArrayList<ChapterItem> arrayList = new ArrayList<>();
        try {
            openDataBase();
            Cursor cursor = database.rawQuery("SELECT \"stID\", \"deID\",\"deName\", \"decontent\" FROM \"st_kimdung\" WHERE" +
                    "\"stID\" = " + id, null);
            ChapterItem chapterItem;
            while (cursor.moveToNext()) {
                chapterItem = new ChapterItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                arrayList.add(chapterItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrayList;
    }
}
