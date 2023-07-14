package com.example.newsapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.today_headline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent today_headline_intent = new Intent(getApplicationContext(), TodayHeadlines.class);
                startActivity(today_headline_intent);
            }
        });
        findViewById(R.id.bussiness).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bussiness_intent = new Intent(getApplicationContext(), Bussiness.class);
                startActivity(bussiness_intent);
            }
        });
        findViewById(R.id.entertainment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entertainment_intent = new Intent(getApplicationContext(), Entertainment.class);
                startActivity(entertainment_intent);
            }
        });
        findViewById(R.id.health).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent health_intent = new Intent(getApplicationContext(), Health.class);
                startActivity(health_intent);
            }
        });
        findViewById(R.id.science).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent science_intent = new Intent(getApplicationContext(), Science.class);
                startActivity(science_intent);
            }
        });
        findViewById(R.id.technology).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent technology_intent = new Intent(getApplicationContext(), Technology.class);
                startActivity(technology_intent);
            }
        });
        findViewById(R.id.sports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sports_intent = new Intent(getApplicationContext(), Sports.class);
                startActivity(sports_intent);
            }
        });


//        getData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.subscribe:
                Intent subscribe_intent = new Intent(getApplicationContext(), Subscribe.class);
                startActivity(subscribe_intent);
                return true;
            case R.id.about_us:
                Intent about_us_intent = new Intent(getApplicationContext(), About_us.class);
                startActivity(about_us_intent);
                return true;
            case R.id.contact_us:
                Intent contact_us_intent = new Intent(getApplicationContext(), Contact_us.class);
                startActivity(contact_us_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}