package com.toka.legendarynews.ui.auth;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LifecycleOwner;

import com.toka.legendarynews.BR;
import com.toka.legendarynews.data.remote.Repo;
import com.toka.legendarynews.ui.base.BasicUIStatus;
import com.toka.legendarynews.ui.base.BasicViewModel;

public class LoginViewModel extends BasicViewModel implements Observable {

    private String username;
    private String password;
    private String error;

    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(
            Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(
            Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }

    public void onLogin(LifecycleOwner lifecycleOwner) {
        getStatus().setValue(BasicUIStatus.LOADING);
        Repo.login(username, password).observe(lifecycleOwner, task -> {
            if (task.isSuccessful()) {
                Repo.setCurrentUserInfo().observe(lifecycleOwner, aBoolean -> {
                    if (Boolean.TRUE.equals(aBoolean)) getStatus().postValue(BasicUIStatus.SUCCESS);
                });
            }
            else {
                Exception exception = task.getException();
                error = exception != null ? exception.getLocalizedMessage() : null;
                getStatus().postValue(BasicUIStatus.ERROR);
            }
        });
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
