package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class ArticlesViewModel extends BaseViewModel {

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();

    public void startFetchingArticles(LifecycleOwner lifecycleOwner) {
        getStatus().postValue(Status.LOADING);
        Repo.getArticles().observe(lifecycleOwner, articles::postValue);
    }

    public void setArticles(List<Article> articles) {
        this.articles.setValue(articles);
    }

    public MutableLiveData<List<Article>> getArticles() {
        return articles;
    }
}
