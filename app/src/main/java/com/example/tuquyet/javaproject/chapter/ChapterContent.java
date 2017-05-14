package com.example.tuquyet.javaproject.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuquyet.javaproject.R;
import com.example.tuquyet.javaproject.SpellErrorActivity;
import com.example.tuquyet.javaproject.spellcheckrule.Xau;

import java.util.ArrayList;
import java.util.List;

import static android.text.Html.FROM_HTML_MODE_COMPACT;
import static com.example.tuquyet.javaproject.spellcheckrule.Rule.checkValid;

public class ChapterContent extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MY_TAG_CHECK_MISTAKE";
    TextView txtChapterContent;
    TextView txtChapterName;
    ImageView imgCheck;
    ArrayList<String> arrayListMistake = new ArrayList<>();
    List<String> arrayListToSearch = new ArrayList<>();
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_content);
        findViewById();
        ChapterItem chapterItemReceive = new ChapterItem(0, 0, "", "");
        Intent intent = getIntent();
        chapterItemReceive = (ChapterItem) intent.getSerializableExtra("Chapter");
        content = chapterItemReceive.getDecontent();
        String chapterName = chapterItemReceive.getDeName();
        arrayListToSearch = chapterItemReceive.getResultTmp();
        if((arrayListToSearch) != null) findAndHighLight();
        //HighlightWord highlight = new HighlightWord();
        txtChapterContent.setText(Html.fromHtml(content));
        txtChapterName.setText(chapterName);
        imgCheck.setOnClickListener(this);
    }

    private void findAndHighLight() {
        for (int i = 0; i < arrayListToSearch.size(); i++) {
//            List<String> list = new ArrayList<>();
//            list =  arrayListToSearch.get(i);

//            for (int j = 0; j < list.size(); j++) {
            String word = arrayListToSearch.get(i);
            Log.e(TAG, "findAndHighLight: tu can phai tim" +word );
            String toChange =  "<font color=\"red\">" + word + "</font>";
             content = content.replaceAll(word,toChange);

                Log.e(TAG, "==========>" + content);

            //    }
            txtChapterContent.setText(content);

        }

    }

    public void checkValidParagraph(String mTmpContent) {
        //String mTmpContent = new String(mTmpContent);
        // mTmpContent = "THIÊN NHAI TƯ QUÂN BẤT KHẢ VONG-<br/>\t\t\t\t\t\t   Chữ tình buộc lấy chữ sầu,<br/>Chân trời góc biển tìm đâu";
        mTmpContent = removeHtmlTag(mTmpContent);
        mTmpContent = mTmpContent.replaceAll("\\W", " ");
        String[] mang;
        mang = mTmpContent.split(" ");
        for (int i = 0; i < mang.length; i++) {
            if (mang[i].compareTo("") != 0) {
                String tmp = mang[i].trim();
                if (tmp.length() != 0) {
                    Xau key = new Xau(tmp);
                    boolean result = checkValid(key);
                    if (result == false) {
                        //Log.d(TAG, "mTmpContent: \"" + mContent + "\"");
//                        Log.d(TAG, "mTmpContent: \"" + mTmpContent + "\"");
                        //                     Log.d(TAG, "key.getXau(): \"" + key.getXau() + "\"");
                        //Log.d(TAG, "result: " + result + "\n");
                        String word = key.getXau();
                        arrayListMistake.add(word);

                        Log.e("MY_TAG", "tmp :" + tmp + "");

                        mTmpContent = mTmpContent.replaceAll(tmp, "hiiiiiiiiiiiiiiiiiiii");
//                        word = "<font style = background-color : #00ff00>"+word+"</font>";
                        Log.e("MY_TAG", "mtp :" + mTmpContent + "");

                    }

                }
            }
        }
//        for (int j = 0; j < arrayListMistake.size(); j++) {
//            Log.i(TAG, "mistake: "+arrayListMistake.get(j));
//        }
    }

    public static String removeHtmlTag(String mContentWithHtmlTag) {
        String content = new String(mContentWithHtmlTag);
        content = content.replaceAll("<br/>", "\n");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            content = android.text.Html.fromHtml(mContentWithHtmlTag, FROM_HTML_MODE_COMPACT).toString();
        } else {
            content = android.text.Html.fromHtml(mContentWithHtmlTag).toString();
        }
        // Log.e(TAG, "checkValidParagraph: mContent_before: \"" + content + "\"");
        return content;

    }

    private void findViewById() {
        txtChapterContent = (TextView) findViewById(R.id.textview_content);
        txtChapterName = (TextView) findViewById(R.id.textview_chaptername);
        imgCheck = (ImageView) findViewById(R.id.imageview_check);

    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        Bundle bundle1 = intent.getBundleExtra("bundle_chapter");
        String content = bundle1.getString("CONTENT");
        checkValidParagraph(content);
        txtChapterContent.setText(Html.fromHtml(content));
        Intent intent2 = new Intent(ChapterContent.this, SpellErrorActivity.class);
        intent2.putStringArrayListExtra("ArrayMistake", arrayListMistake);
        startActivity(intent2);
    }


}
