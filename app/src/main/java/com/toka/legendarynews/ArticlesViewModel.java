package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class ArticlesViewModel extends BaseViewModel {

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();

    public void startFetchingArticles(LifecycleOwner lifecycleOwner) {
        getStatus().postValue(UIStatus.LOADING);
        Repo.listenForCurrentUserArticleChanges().observe(lifecycleOwner, articles::postValue);
    }

    public void publishArticle(Article article) {
        Repo.publishArticle(article);
    }

    public void deleteArticle(Article article) {
        Repo.deleteArticle(article);
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
}
