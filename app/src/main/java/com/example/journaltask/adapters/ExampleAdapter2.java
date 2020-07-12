package com.example.journaltask.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journaltask.R;
import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter2 extends RecyclerView.Adapter<ExampleAdapter2.ExamplePlaceHolder>  {
    private List<articlesWithid> articles;
    private onClickListener litener;


    class ExamplePlaceHolder extends RecyclerView.ViewHolder{
        TextView authorName2,articleTitle;
        ExamplePlaceHolder(@NonNull View itemView) {
            super(itemView);
            authorName2=itemView.findViewById(R.id.txt_authorName3);
            articleTitle=itemView.findViewById(R.id.txt_articleTitle3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(litener != null){
                        int position =getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION)
                        {
                            litener .onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter2(List<articlesWithid> articles){
        this.articles=articles;


    }
    @NonNull
    @Override
    public ExamplePlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_articles_binding,parent,false);
        ExamplePlaceHolder evh=new ExamplePlaceHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamplePlaceHolder examplePlaceHolder, int Position) {
        articlesWithid currentArticle=articles.get(Position);
        examplePlaceHolder.articleTitle.setText(currentArticle.getTitle());
        examplePlaceHolder.authorName2.setText(currentArticle.getAuthor());
    }
    public interface onClickListener{
        void onItemClick(int position);
    }
    public void setOnitemClickedListener(onClickListener litener)
    {
        this.litener=litener;
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }
}
