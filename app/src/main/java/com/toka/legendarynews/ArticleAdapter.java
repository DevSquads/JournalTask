package com.toka.legendarynews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.toka.legendarynews.databinding.ArticleErrorRowItemBinding;
import com.toka.legendarynews.databinding.ArticleRowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends ListAdapter<Article, RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_ERROR = 1;

    private final boolean isCurrentUserAdmin;
    private final OnArticleActionsListener onArticleActionsListener;

    public ArticleAdapter(boolean isCurrentUserAdmin, OnArticleActionsListener onArticleActionsListener) {
        super(new ArticlesDiffUtilCallback());

        this.isCurrentUserAdmin = isCurrentUserAdmin;
        this.onArticleActionsListener = onArticleActionsListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_ITEM)
            return new ArticleViewHolder(ArticleRowItemBinding.inflate(inflater));
        else return new ErrorViewHolder(ArticleErrorRowItemBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleViewHolder) {
            Article article = getItem(position);
            ArticleViewHolder articleViewHolder = (ArticleViewHolder)holder;

            articleViewHolder.binding.tvTitle.setText(article.getTitle());
            articleViewHolder.binding.tvDesc.setText(article.getDescription());

            articleViewHolder.binding.getRoot().setOnClickListener(v -> onArticleActionsListener.onArticleClick(article));

            articleViewHolder.binding.g.setVisibility(isCurrentUserAdmin ? View.VISIBLE : View.GONE);

            articleViewHolder.binding.ibDelete.setOnClickListener(v -> onArticleActionsListener.onArticleDeleteClick(article));

            articleViewHolder.binding.ibPublish.setOnClickListener(v -> onArticleActionsListener.onArticlePublishClick(article));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) == null)
            return VIEW_TYPE_ERROR;
        else return VIEW_TYPE_ITEM;
    }

    @Override
    public void submitList(@Nullable List<Article> list) {
        if (list != null) {
            List<Article> newList = new ArrayList<>(list);
            super.submitList(newList);
        } else super.submitList(null);
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final ArticleRowItemBinding binding;

        public ArticleViewHolder(@NonNull ArticleRowItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {

        public ErrorViewHolder(@NonNull ArticleErrorRowItemBinding binding) {
            super(binding.getRoot());
        }
    }

    public interface OnArticleActionsListener {
        void onArticleClick(Article article);
        void onArticlePublishClick(Article article);
        void onArticleDeleteClick(Article article);
    }
}
