package com.example.tuquyet.javaproject.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuquyet.javaproject.R;
import com.example.tuquyet.javaproject.SpellErrorActivity;
import com.example.tuquyet.javaproject.spellcheckrule.Xau;

import java.util.ArrayList;
import java.util.List;

import static android.text.Html.FROM_HTML_MODE_COMPACT;
import static com.example.tuquyet.javaproject.spellcheckrule.Rule.checkValid;

public class ChapterContent extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CHECK_MISTAKE";
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
        //Tao chapterItem de do du lieu vao
        ChapterItem chapterItemReceive = new ChapterItem(0, 0, "", "");
        Intent intent = getIntent();
        chapterItemReceive = (ChapterItem) intent.getSerializableExtra("Chapter");
        // Noi dung truyen
        content = chapterItemReceive.getDecontent();
        String chapterName = chapterItemReceive.getDeName();
        arrayListToSearch = chapterItemReceive.getResultTmp();

        if ((arrayListToSearch) != null) findAndHighLight();
        txtChapterContent.setText(Html.fromHtml(content));
        txtChapterName.setText(chapterName);
        imgCheck.setOnClickListener(this);
    }

    private String toChange(String word) {
        return "<font color=\"red\">" + word + "</font>";
    }

    public String takeLast(String s) {
        int i = s.lastIndexOf(' ');
        s = s.substring(i + 1);
        return s;
    }

    public String takeFirst(String s) {
        int i = s.lastIndexOf(' ');
        s = s.substring(0, i);
        return s;
    }


    private void findAndHighLight() {
        String s = "";
        for (int i = 0; i < arrayListToSearch.size(); i++) {
            String wordCurrent = arrayListToSearch.get(i);
            s += wordCurrent + "\n";

            content = content.replaceAll(wordCurrent, toChange(wordCurrent));
            content = content.replaceAll(takeLast(wordCurrent), toChange(takeLast(wordCurrent)));
        }
        if (s != "")
            Toast.makeText(this, "Tìm kiếm cho : \n" + s, Toast.LENGTH_LONG).show();

    }

    public void checkValidParagraph(String mTmpContent) {
//       mTmpContent = new String(mTmpContent);
//       mTmpContent = "huỳnh ănh ăn của bống bang absnfh 55 ?ud ?ud!";
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
                        String word = key.getXau();
                        arrayListMistake.add(word);


                    }

                }
            }
        }
    }


    public static String removeHtmlTag(String mContentWithHtmlTag) {
        String content = new String(mContentWithHtmlTag);
        content = content.replaceAll("<br/>", "\n");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            content = android.text.Html.fromHtml(mContentWithHtmlTag, FROM_HTML_MODE_COMPACT).toString();
        } else {
            content = android.text.Html.fromHtml(mContentWithHtmlTag).toString();
        }
        return content;

    }

    private void findViewById() {
        txtChapterContent = (TextView) findViewById(R.id.textview_content);
        txtChapterName = (TextView) findViewById(R.id.textview_chaptername);
        imgCheck = (ImageView) findViewById(R.id.imageview_check);

    }

    @Override
    public void onClick(View v) {
        checkValidParagraph(content);
        txtChapterContent.setText(Html.fromHtml(content));
        Intent intent2 = new Intent(ChapterContent.this, SpellErrorActivity.class);
        intent2.putStringArrayListExtra("ArrayMistake", arrayListMistake);
        intent2.putExtra("ArrayMistakeSize", arrayListMistake.size());
        startActivity(intent2);
    }


}
