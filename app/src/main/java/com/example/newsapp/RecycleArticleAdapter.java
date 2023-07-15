package com.example.newsapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleArticleAdapter extends RecyclerView.Adapter<RecycleArticleAdapter.ViewHolder> {
    Context context;
    ArrayList<ArticleModel> arrArticle;

    RecycleArticleAdapter(Context context, ArrayList<ArticleModel> arrArticle) {
        this.context = context;
        this.arrArticle = arrArticle;

    }

    @NonNull
    @Override
    public RecycleArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_article, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.source.setText(arrArticle.get(position).source);
        holder.title.setText(arrArticle.get(position).title);
        holder.description.setText(arrArticle.get(position).description);
        holder.urlToImage.setImageBitmap(arrArticle.get(position).urlToImage);
        holder.publishedAt.setText(arrArticle.get(position).publishedAt);
        holder.author.setText(arrArticle.get(position).author);
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iWeb_View = new Intent(context, Web_View.class);
                String url = arrArticle.get(holder.getAdapterPosition()).url;
                iWeb_View = iWeb_View.putExtra("url", url);
                context.startActivity(iWeb_View);
            }
        });
        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iWeb_View = new Intent(context, Web_View.class);
                String url = arrArticle.get(holder.getAdapterPosition()).url;
                iWeb_View = iWeb_View.putExtra("url", url);
                context.startActivity(iWeb_View);


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrArticle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView source;
        TextView author;
        TextView title;
        TextView description;
        Button url;
        ImageView urlToImage;
        TextView publishedAt;
        LinearLayout llRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            source = itemView.findViewById(R.id.article_source);
            author = itemView.findViewById(R.id.article_author);    //author
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_desc);
            url = itemView.findViewById(R.id.read_more_btn);
            urlToImage = itemView.findViewById(R.id.article_img);
            publishedAt = itemView.findViewById(R.id.article_publishedAt);
            llRow = itemView.findViewById(R.id.llRow);

        }
    }

}
