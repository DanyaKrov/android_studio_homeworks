package com.example.patrickbatemancard;


public class NewsItem {
    public final String aricle;
    public int likes;
    public final int picture;
    public boolean liked;
    public String[] commentArray;

    public NewsItem(String aricle, int likes, int picture, String[] commentArray) {
        this.aricle = aricle;
        this.likes = likes;
        this.picture = picture;
        this.commentArray = commentArray;
    }
}