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

public class Sports extends AppCompatActivity {
    ArrayList<ArticleModel> arrArticle = new ArrayList<>();
    RecyclerView recyclerView;
    RecycleArticleAdapter adapter;
    private final String url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=43a3a6d4d9204f8886265212e5cc2f97";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        recyclerView = findViewById(R.id.recycle_sports);
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("GOT");
                try {
                    String status = response.getString("status");
                    String totalResults = response.getString("totalResults");
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        String source = articles.getJSONObject(i).getJSONObject("source").getString("name");
                        String author = articles.getJSONObject(i).getString("author");
                        String title = articles.getJSONObject(i).getString("title");
                        String description = articles.getJSONObject(i).getString("description");
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