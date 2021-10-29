package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    private final MutableLiveData<UIStatus> status = new MutableLiveData<>(UIStatus.IDLE);

    public void setStatus(UIStatus UIStatus) {
        this.status.setValue(UIStatus);
    }

    public MutableLiveData<UIStatus> getStatus() {
        return status;
    }
}
