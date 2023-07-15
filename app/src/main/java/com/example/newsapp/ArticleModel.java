package com.example.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArticleModel {
    String source;
    String author;
    String title;
    String description;
    String url;
    Bitmap urlToImage;
    String publishedAt;


    public ArticleModel(String source, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        new Thread(new Runnable() {
            public void run() {
                getBitmapFromURL(urlToImage);
            }
        }).start();
        this.url = url;
        System.out.println(publishedAt);
        this.publishedAt = publishedAt;

    }

    public void getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            this.urlToImage = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return;
        }
    }

}
