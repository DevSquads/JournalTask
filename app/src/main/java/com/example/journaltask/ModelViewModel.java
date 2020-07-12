package com.example.journaltask;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;

import java.util.ArrayList;
import java.util.List;

public class ModelViewModel extends ViewModel {
    MutableLiveData<List<articlesWithid>> liveData=new MutableLiveData<>(); // choosed MutableLiveData for edit it later
    MutableLiveData<List<articlesWithid>> liveData2=new MutableLiveData<>();
    public void getArticles(){  //retrive articles useinf call back interface
        new FireBase().getAllBinding(new callBack() {
            @Override
            public void onCallback(users user) {

            }

            @Override
            public void isSent(boolean sent) {

            }

            @Override
            public void retriveArticles(List<articlesWithid> article) {
                liveData.setValue(article); //set liveData value to list of articles to observe it later
                Log.e("Here3", article.size()+"");
            }

            @Override
            public void retriveAllArticles(List<articlesWithid> article) {

            }
        });



    }
    public void getAllArticles(){
        new FireBase().getAllArticles(new callBack() {
            @Override
            public void onCallback(users user) {

            }

            @Override
            public void isSent(boolean sent) {

            }

            @Override
            public void retriveArticles(List<articlesWithid> article) {

            }

            @Override
            public void retriveAllArticles(List<articlesWithid> article) {
            liveData2.setValue(article);
            }
        });
    }

    }


