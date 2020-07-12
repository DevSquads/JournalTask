package com.example.journaltask;

import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;

import java.util.ArrayList;
import java.util.List;
//callBack interface just to synchronise retriving data speed with app callBacks could return lists or just boolean for isSuccessful or not
public interface callBack {
    public void onCallback(users user);
    public void isSent(boolean sent);
    public void retriveArticles(List<articlesWithid> article);
    public void retriveAllArticles(List<articlesWithid> article);
}
