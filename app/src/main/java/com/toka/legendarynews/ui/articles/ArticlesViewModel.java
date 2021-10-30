package com.toka.legendarynews.ui.articles;

import static com.toka.legendarynews.ui.articles.ArticlesUIStatus.PUBLISH_ARTICLES_ERROR;
import static com.toka.legendarynews.ui.articles.ArticlesUIStatus.PUBLISH_ARTICLES_SUCCESS;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.toka.legendarynews.data.model.Article;
import com.toka.legendarynews.data.remote.Repo;

import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private final MutableLiveData<ArticlesUIStatus> status = new MutableLiveData<>(ArticlesUIStatus.IDLE);

    public void startFetchingArticles(LifecycleOwner lifecycleOwner) {
        Repo.listenForCurrentUserArticleChanges().observe(lifecycleOwner, articles::postValue);
    }

    public void publishArticle(LifecycleOwner lifecycleOwner, Article article) {
        status.postValue(ArticlesUIStatus.LOADING);
        Repo.publishArticle(article).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                status.postValue(PUBLISH_ARTICLES_SUCCESS);
            else status.postValue(PUBLISH_ARTICLES_ERROR);
        });
    }

    public void deleteArticle(LifecycleOwner lifecycleOwner, Article article) {
        status.postValue(ArticlesUIStatus.LOADING);
        Repo.deleteArticle(article).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                status.postValue(ArticlesUIStatus.DELETE_ARTICLE_SUCCESS);
            else status.postValue(ArticlesUIStatus.DELETE_ARTICLE_ERROR);
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
