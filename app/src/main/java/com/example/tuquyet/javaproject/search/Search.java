package com.example.tuquyet.javaproject.search;

import java.util.ArrayList;

public class Search {

    public static void main(String[] args) {
        String content = "một con vịt xòe ra hai cái cánh. con vịt ăn con gà. gà thì lại ăn trứng vịt";
        String keywords = "vịt cánh gà";
        ArrayList<String> result = new ArrayList<>();
        result = search(content, keywords);
        if (result!=null)
            for (int i =0; i<result.size();i++){System.out.println(result.get(i));}

    }

    public static  ArrayList<String> search(String content, String keywords) {
        ArrayList<String> listKeyword = splitKeywords(keywords);
        ArrayList<String> listContentWords = splitContent(content);
        ArrayList<String> result = new ArrayList<>();
        int begin=0;
        for (int k = 0; k < listKeyword.size(); k++) {
            int check=0;
            for (int c = begin; c < listContentWords.size(); c++) {
                String tmpContentWords = listContentWords.get(c).trim().toLowerCase();

                //System.out.print(tmpContentWords);
                if (listKeyword.get(k).equals(tmpContentWords)) {
                    check=1;
                    //System.out.println("listKeyword.get(k): "+listKeyword.get(k)+" listContentWords.get(c):"+listContentWords.get(c)+" check=1"+" c="+c);
                    int match = c;
                    if (match - 1 >= 0) {
                        String tmp = listContentWords.get(match-1);
                        tmp = tmp.concat(" "+listContentWords.get(match));
                        result.add(tmp);
                    } else if (match + 1 < listContentWords.size()) {
                        String tmp = listContentWords.get(match);
                        tmp = tmp.concat(" "+listContentWords.get(match+1));
                        result.add(tmp);
                    }
                    begin=c;
                    break;
                }
            }
            if(check==0) {return null;}
        }
        return result;
    }

    private static ArrayList<String> splitKeywords(String keywords) {
        String[] str;
        ArrayList<String> listKeyword = new ArrayList<>();
        str = keywords.split(" ");
        for (String s : str) {
            if (!s.equals("")) {
                listKeyword.add(s);
            }
        }
        return listKeyword;
    }

    private static ArrayList<String> splitContent(String content) {
        String[] str;
        ArrayList<String> words = new ArrayList<>();
        str = content.split(" ");

        for (String s : str) {
            if (!s.equals("")) {
                int i=s.length()-1;
                if(i>=0){
                    while (".,?[]):\"!0123456789".contains(String.valueOf(s.charAt(i))) ){
                        s = s.substring(0, i);
                        i--;
                        if(i==-1) break;
                    }
                }
                words.add(s);
            }
        }
        return words;
    }
}