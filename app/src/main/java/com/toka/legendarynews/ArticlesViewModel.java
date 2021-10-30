package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private final MutableLiveData<ArticlesUIStatus> status = new MutableLiveData<>(ArticlesUIStatus.IDLE);

    public void startFetchingArticles(LifecycleOwner lifecycleOwner) {
        getStatus().postValue(ArticlesUIStatus.LOADING);
        Repo.listenForCurrentUserArticleChanges().observe(lifecycleOwner, articles::postValue);
    }

    public void publishArticle(Article article) {
        getStatus().postValue(ArticlesUIStatus.LOADING);
        Repo.publishArticle(article);
    }

    public void deleteArticle(LifecycleOwner lifecycleOwner, Article article) {
        getStatus().postValue(ArticlesUIStatus.LOADING);
        Repo.deleteArticle(article).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                getStatus().postValue(ArticlesUIStatus.DELETE_ARTICLE_SUCCESS);
            else getStatus().postValue(ArticlesUIStatus.DELETE_ARTICLE_ERROR);
        });
    }

    public boolean isCurrentUserAdmin() {
        return Repo.isCurrentUserAdmin();
    }

    public String getCurrentUserName() {
        return Repo.getCurrentUserName();
    }

    public void setArticles(List<Article> articles) {
        this.articles.setValue(articles);
    }

    public MutableLiveData<List<Article>> getArticles() {
        return articles;
    }

    public void setStatus(ArticlesUIStatus status) {
        this.status.setValue(status);
    }

    public MutableLiveData<ArticlesUIStatus> getStatus() {
        return status;
    }
}
