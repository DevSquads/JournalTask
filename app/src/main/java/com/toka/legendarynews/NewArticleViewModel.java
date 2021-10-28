package com.toka.legendarynews;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewArticleViewModel extends ViewModel {

    private final MutableLiveData<Status> status = new MutableLiveData<>(Status.IDLE);

    public void addArticle(String title, String desc, LifecycleOwner lifecycleOwner) {
        status.setValue(Status.LOADING);
        Repo.addArticle(title, desc).observe(lifecycleOwner, voidTask -> {
            if (voidTask.isSuccessful())
                status.postValue(Status.SUCCESS);
            else status.postValue(Status.ERROR);
        });
    }

    public void setStatus(Status status) {
        this.status.setValue(status);
    }

    public MutableLiveData<Status> getStatus() {
        return status;
    }
}
