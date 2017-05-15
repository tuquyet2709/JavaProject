package com.example.tuquyet.javaproject.chapter;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuquyet.javaproject.R;
import com.example.tuquyet.javaproject.database.SQLiteStoryChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.tuquyet.javaproject.search.Search.search;

public class StoryChapterActivity extends AppCompatActivity {
    ArrayList<ChapterItem> mChapterLists = new ArrayList<>();
    private RecyclerView mChapterRecyclerView;
    private ChapterAdapter mChapterAdapter;
    private TextView mStoryNameInChapter;
    private ImageView img_micro;
    private ImageView img_search;
    private EditText text;
    private String searchTextInput;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    public ProgressDialog dialog;
    public final String TAG = "MY_TAG";
     List<List<String>> result = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_chapter_main);
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("bundle1");
        final int idStory = bundle1.getInt("ID2", 0);
        final String storyName = bundle1.getString("StoryName");
        //Lay danh sach chapter tu idStory gui tu Descreption
        getStoryChapter(idStory);
        findViewById();
        mStoryNameInChapter.setText(storyName);
        mChapterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChapterAdapter = new ChapterAdapter(mChapterLists, getApplicationContext());
        mChapterRecyclerView.setAdapter(mChapterAdapter);
        //Bat giong noi
        img_micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgToOpenMic();
            }
        });
        //Search
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.clear();
                imgToSearch();

            }
        });


    }

    private void findViewById() {
        img_micro = (ImageView) findViewById(R.id.imageview_micro);
        img_search = (ImageView) findViewById(R.id.imageview_search);
        text = (EditText) findViewById(R.id.edittext_text);
        mStoryNameInChapter = (TextView) findViewById(R.id.textview_storyname_chapterlist);
        mChapterRecyclerView = (RecyclerView) findViewById(R.id.chapter_recyclerview);
    }


    private void imgToOpenMic() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Nhập giọng nói sau tiếng *beep*....");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim) {
            Toast.makeText(this, "Lỗi! Bạn chưa cài đặt Speech to text của Google", Toast.LENGTH_SHORT).show();
        }
    }

    private void imgToSearch() {
        // TODO: 14/05/2017
        searchTextInput = text.getText().toString();
        searchTextInput = searchTextInput.trim();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new SearchInChapter().execute();

            }
        });
    }


    private void getStoryChapter(int id) {
        SQLiteStoryChapter storyChapterSQL = new SQLiteStoryChapter(getApplicationContext());
        mChapterLists = storyChapterSQL.getStoryChapters(id);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text.setText(voiceInText.get(0));

                }
                break;
            }


        }
    }

    public class SearchInChapter extends AsyncTask<String, ChapterItem, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(StoryChapterActivity.this, "Tìm kiếm từ khóa", "Đang tìm kiếm", true);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            //  tim kiem tu nhap vao trong moi chapter
            for (int i = 0; i < mChapterLists.size(); i++) {
                String deContent = ChapterContent.removeHtmlTag(mChapterLists.get(i).getDecontent());
                deContent = deContent.replaceAll("\\W", " ");
                searchTextInput = searchTextInput.toLowerCase() ; //chuyen thanh chu thuong h
                mChapterLists.get(i).setResultTmp(search(deContent, searchTextInput));
                result.add(mChapterLists.get(i).getResultTmp());

            }

            return null;

        }

        @Override
        protected void onProgressUpdate(ChapterItem... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mChapterAdapter.notifyDataSetChanged();
            dialog.dismiss();
            Toast.makeText(StoryChapterActivity.this, "Tìm kiếm xong !", Toast.LENGTH_SHORT).show();
        }


    }

}
