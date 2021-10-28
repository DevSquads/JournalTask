package com.toka.legendarynews;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.toka.legendarynews.databinding.ArticleRowItemBinding;

public class ArticleAdapter extends ListAdapter<Article, ArticleAdapter.ArticleViewHolder> {

    private OnArticleClickListener onArticleClickListener;

    public ArticleAdapter(OnArticleClickListener onArticleClickListener) {
        super(new DiffUtilCallback());

        this.onArticleClickListener = onArticleClickListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(ArticleRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = getItem(position);

        holder.binding.tvTitle.setText(article.getTitle());
        holder.binding.tvDesc.setText(article.getDescription());

        holder.binding.getRoot().setOnClickListener(v -> onArticleClickListener.onArticleClick(article));

        holder.binding.ibDelete.setOnClickListener(v -> {

        });

        holder.binding.ibPublish.setOnClickListener(v -> {

        });
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final ArticleRowItemBinding binding;

        public ArticleViewHolder(@NonNull ArticleRowItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public interface OnArticleClickListener {
        void onArticleClick(Article article);
    }
}
