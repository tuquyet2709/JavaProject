package com.example.tuquyet.javaproject.chapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuquyet on 01/05/2017.
 */

public class ChapterItem implements Serializable{
    private int stID;
    private int deID;
    private String deName;
    private String decontent;
    private List<String> resultTmp;


    public void setResultTmp(ArrayList resultTmp) {
        this.resultTmp = resultTmp;
    }

    public ArrayList getResultTmp() {
        return (ArrayList) resultTmp;
    }

    public ChapterItem(int stID, int deID, String deName, String decontent) {
        this.stID = stID;
        this.deID = deID;
        this.deName = deName;
        this.decontent = decontent;
        this.resultTmp = new ArrayList();
    }

    public int getStID() {
        return stID;
    }

    public void setStID(int stID) {
        this.stID = stID;
    }

    public int getDeID() {
        return deID;
    }

    public void setDeID(int deID) {
        this.deID = deID;
    }

    public String getDeName() {
        return deName;
    }

    public void setDeName(String deName) {
        this.deName = deName;
    }

    public String getDecontent() {
        return decontent;
    }

    public void setDecontent(String decontent) {
        this.decontent = decontent;
    }
}
