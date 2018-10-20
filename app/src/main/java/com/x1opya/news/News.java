package com.x1opya.news;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class News {

    public Source source;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;

    public String getbodyNews(){
        String result = title+"\n \n";
        result += (description==null)?"":description+"\n";
        result += (content==null)?"":content+"\n";
        return result;
    }

    class Source{
        public String id;
        public String name;
    }

    public String getTime(){
        String[] parts = publishedAt.split("T");

        String[]timeParts = parts[1].replace("Z","").split(":");
        Calendar time = Calendar.getInstance();

        return timeParts[0]+":"+timeParts[1]+":"+timeParts[2];
    }

    public String getContent(){
        if(content==null) return null;
        String[] s =content.split("...");
        s[s.length]=". Продолжение в источник";
        String value="";
        for (int i = 0;i<=s.length;i++){
            value+=s[i]+"...";
        }
        return value;
    }
}

