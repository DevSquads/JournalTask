package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;

public class NewArticleViewModel extends BasicViewModel {

    public void addArticle(String title, String desc, LifecycleOwner lifecycleOwner) {
        getStatus().setValue(BasicUIStatus.LOADING);
        Repo.addArticle(title, desc).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                getStatus().postValue(BasicUIStatus.SUCCESS);
            else getStatus().postValue(BasicUIStatus.ERROR);
        });
    }
}
