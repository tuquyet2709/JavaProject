package com.example.tuquyet.javaproject.story;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tuquyet.javaproject.R;
import com.example.tuquyet.javaproject.database.DatabaseController;
import com.example.tuquyet.javaproject.database.SQLiteStoryList;

import java.io.IOException;
import java.util.List;

import static com.example.tuquyet.javaproject.spellcheckrule.Charcode.sortCharcode;

public class MainActivity extends AppCompatActivity {
    private RecyclerView storyRecyclerView;
    private StoryStoreAdapter storyAdapter;
    private List<StoryItem> storyItemsList;
    // ArrayList<ChapterItem> chapterLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("OOPS Project");
        //Khoi tao va lay danh sach truyen tu database
        createDB();
        getStory();
//        getStoryChapter();

        // sort charcode
        sortCharcode();

        storyRecyclerView = (RecyclerView) findViewById(R.id.story_recyclerview);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        storyAdapter = new StoryStoreAdapter(storyItemsList, getApplicationContext());
        storyRecyclerView.setAdapter(storyAdapter);

    }


    private void createDB() {
        // khởi tạo database
        DatabaseController sql = new DatabaseController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getStory() {
        SQLiteStoryList storyListSql = new SQLiteStoryList(getApplicationContext());
        storyItemsList = storyListSql.getStoryLists();

    }

//    private void getStoryChapter() {
//        SQLiteStoryChapter storyChapterSQL = new SQLiteStoryChapter(getApplicationContext());
//        chapterLists = storyChapterSQL.getStoryChapters();
//    }
}
