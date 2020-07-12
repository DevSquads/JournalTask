package com.example.journaltask.adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journaltask.R;
import com.example.journaltask.modules.articleWithNum;
import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExamplePlaceHolder>  {
    private List<articleWithNum> articles;
    private onClickListener litener;
    class ExamplePlaceHolder extends RecyclerView.ViewHolder{
        TextView authorName2,articleTitle,txt_authorContribute;
        ExamplePlaceHolder(@NonNull View itemView) {
            super(itemView);
            txt_authorContribute=itemView.findViewById(R.id.txt_authorContribute);
            authorName2=itemView.findViewById(R.id.txt_authorName2);
            articleTitle=itemView.findViewById(R.id.txt_articleTitle2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(litener != null){
                        int position =getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION)
                        {
                            litener .onItemClick(articles.get(position));
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(List<articleWithNum> articles){
        this.articles=articles;


    }



    @NonNull
    @Override
    public ExamplePlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_aricles,parent,false);
        ExamplePlaceHolder evh=new ExamplePlaceHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamplePlaceHolder examplePlaceHolder, int Position) {
        articleWithNum currentArticle=articles.get(Position);
        examplePlaceHolder.articleTitle.setText(currentArticle.getTitle());
        examplePlaceHolder.authorName2.setText(currentArticle.getAuthor());
        examplePlaceHolder.txt_authorContribute.setText(currentArticle.getKey()+"Articles");


    }
    public interface onClickListener{
        void onItemClick(articleWithNum  articles);
    }
    public void setOnitemClickedListener(ExampleAdapter.onClickListener litener)
    {
        this.litener=litener;
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }
}
