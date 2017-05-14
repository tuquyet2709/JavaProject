package com.example.tuquyet.javaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuquyet.javaproject.chapter.ChapterContent;

import java.util.ArrayList;

public class SpellErrorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtError;
    Button btnViewMistake;
    ArrayList<String> mistakeList = new ArrayList();
    String errorString = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_error);
        Intent intent = getIntent();
        mistakeList = intent.getStringArrayListExtra("ArrayMistake");
        Toast.makeText(this, "Tìm thấy " +mistakeList.size() + " lỗi", Toast.LENGTH_SHORT).show();
        for (int j = 0; j < mistakeList.size(); j++) {
            errorString =  errorString+"- "+ mistakeList.get(j) + "\n";
        }
        findViewById();
        txtError.setText(errorString);
        txtError.setMovementMethod(new ScrollingMovementMethod());
        btnViewMistake.setOnClickListener(this);

    }

    private void findViewById() {
        txtError = (TextView) findViewById(R.id.textview_error);
        btnViewMistake = (Button) findViewById(R.id.button_view_mistake);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
