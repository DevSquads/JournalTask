package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class API {

    private API() {}
    public static MutableLiveData<Task<AuthResult>> login(String username, String password) {
        MutableLiveData<Task<AuthResult>> authResultTask = new MutableLiveData<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(authResultTask::postValue);
        return authResultTask;
    }
}
