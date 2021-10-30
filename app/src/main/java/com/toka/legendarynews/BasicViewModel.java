package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BasicViewModel extends ViewModel {
    private final MutableLiveData<BasicUIStatus> status = new MutableLiveData<>(BasicUIStatus.IDLE);

    public void setStatus(BasicUIStatus status) {
        this.status.setValue(status);
    }

    public MutableLiveData<BasicUIStatus> getStatus() {
        return status;
    }
}
