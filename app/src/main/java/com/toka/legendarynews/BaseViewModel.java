package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    private final MutableLiveData<Status> status = new MutableLiveData<>(Status.IDLE);

    public void setStatus(Status status) {
        this.status.setValue(status);
    }

    public MutableLiveData<Status> getStatus() {
        return status;
    }
}
