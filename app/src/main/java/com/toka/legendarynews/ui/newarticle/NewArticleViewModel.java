package com.toka.legendarynews.ui.newarticle;

import androidx.lifecycle.LifecycleOwner;

import com.toka.legendarynews.data.remote.Repo;
import com.toka.legendarynews.ui.base.BasicUIStatus;
import com.toka.legendarynews.ui.base.BasicViewModel;

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
