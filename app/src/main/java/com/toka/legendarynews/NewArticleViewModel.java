package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;

public class NewArticleViewModel extends BaseViewModel {

    public void addArticle(String title, String desc, LifecycleOwner lifecycleOwner) {
        getStatus().setValue(Status.LOADING);
        Repo.addArticle(title, desc).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                getStatus().postValue(Status.SUCCESS);
            else getStatus().postValue(Status.ERROR);
        });
    }
}
