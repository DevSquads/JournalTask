package com.toka.legendarynews.ui.articles;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.toka.legendarynews.data.model.Article;

public class ArticlesDiffUtilCallback extends DiffUtil.ItemCallback<Article> {

    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.equals(newItem);
    }
}
