package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Web_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent fromact = getIntent();
        String url = fromact.getStringExtra("url");
        WebView webView = findViewById(R.id.web_view);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


    }
}