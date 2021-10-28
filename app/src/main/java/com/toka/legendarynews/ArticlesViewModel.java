package com.toka.legendarynews;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private List<Article> articles = new ArrayList<>();
    private String currentUserId;

}
