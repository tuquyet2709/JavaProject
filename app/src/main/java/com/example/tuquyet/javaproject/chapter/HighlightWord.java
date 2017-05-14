package com.example.tuquyet.javaproject.chapter;

import android.text.Html;

/**
 * Created by tuquyet on 14/05/2017.
 */

public class HighlightWord {
    String contentToHighLight;
    String result;

    public String HighlightWord(String contentToHighLight) {
        this.contentToHighLight = contentToHighLight;
        result = String.valueOf(Html.fromHtml("<font color=\"red\">" + contentToHighLight + "</font>"));
        return result;
    }

}
