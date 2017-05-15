package com.example.tuquyet.javaproject.story;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuquyet.javaproject.R;
import com.example.tuquyet.javaproject.chapter.StoryChapterActivity;


public class StoryDescription extends AppCompatActivity {
    ImageView imgDescription;
    TextView txtDescription;
    Button buttonRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_description);
        Intent mIntent = getIntent();
        final int id = mIntent.getIntExtra("ID", 0);
        final String storyName = mIntent.getStringExtra("StoryName");
        findViewById();
        //Set anh ben trong
        String mDrawableName = "bia_" + id;
        Resources res = getResources();
        int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        imgDescription.setImageDrawable(drawable);

        String s = mIntent.getStringExtra("String");
        txtDescription.setMovementMethod(new ScrollingMovementMethod()); //Set co the scroll
        txtDescription.setText(Html.fromHtml(s));

        //Set su kien cho button
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryDescription.this, StoryChapterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID2", id);
                bundle.putString("StoryName", storyName);
                intent.putExtra("bundle1", bundle);
                startActivity(intent);
            }
        });
    }

    private void findViewById() {
        imgDescription = (ImageView) findViewById(R.id.img_description);
        txtDescription = (TextView) findViewById(R.id.textview_description);
        buttonRead = (Button) findViewById(R.id.button_doctruyen);
    }
}
