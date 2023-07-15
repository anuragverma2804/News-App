package com.example.newsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TodayHeadlines extends AppCompatActivity {
    ArrayList<ArticleModel> arrArticle = new ArrayList<>();
    RecyclerView recyclerView;
    RecycleArticleAdapter adapter;

    private final String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=025528994f094931b7da365f0eda3ac3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_headlines);
        recyclerView = findViewById(R.id.recycle_headlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleArticleAdapter(this, arrArticle);
        getData();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(TodayHeadlines.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    String totalResults = response.getString("totalResults");
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        String source = articles.getJSONObject(i).getJSONObject("source").getString("name") != "null" ? articles.getJSONObject(i).getJSONObject("source").getString("name") : "N A";
                        String author = articles.getJSONObject(i).getString("author") != "null" ? articles.getJSONObject(i).getString("author") : "N A";
                        String title = articles.getJSONObject(i).getString("title");
                        String description = articles.getJSONObject(i).getString("description") != "null" ? articles.getJSONObject(i).getString("description") : "N A";
                        String url = articles.getJSONObject(i).getString("url");
                        String urlToImage = articles.getJSONObject(i).getString("urlToImage");
                        String publishedAt = articles.getJSONObject(i).getString("publishedAt");
                        TimeToText TimeToText = new TimeToText();
                        publishedAt = TimeToText.covertTimeToText(publishedAt);

                        arrArticle.add(new ArticleModel(source, author, title, description, url, urlToImage, publishedAt));
                    }

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
}