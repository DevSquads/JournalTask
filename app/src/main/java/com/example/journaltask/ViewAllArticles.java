package com.example.journaltask;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journaltask.adapters.ExampleAdapter;
import com.example.journaltask.modules.articleWithNum;
import com.example.journaltask.modules.articlesWithid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAllArticles extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager lm;
    ExampleAdapter exampleAdapter;
    static articleWithNum choosenArticle;
    ModelViewModel modelViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_articles);
        ButterKnife.bind(this);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //create LinearLayoutManager that needed for RecyclerView;
        recyclerView.setLayoutManager(lm); //pass it to RecyclerView
        func();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        func(); //run this function every time user return to this activity for latest data
    }

    private void func() {
        modelViewModel = ViewModelProviders.of(this).get(ModelViewModel.class); // create mnnv class object
        modelViewModel.getAllArticles(); // run getAllArticles function in mvvm class
        modelViewModel.liveData2.observe(this, new Observer<List<articlesWithid>>() { //observe livedata variable for changes
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<articlesWithid> articles) { //the observed livedata send list of articles
                List<articleWithNum> reArangedList = new ArrayList<>();
                reArangedList=reArange(articles);                  // pass unsorted list to reArange function
                exampleAdapter = new ExampleAdapter(reArangedList);
                recyclerView.setAdapter(exampleAdapter);            //pass sorted list to RecyclerView adapter
                exampleAdapter.setOnitemClickedListener(new ExampleAdapter.onClickListener() { //on item clicked from the RecyclerView lister
                    @Override
                    public void onItemClick(articleWithNum articleWithNum) {
                        choosenArticle =articleWithNum;                         //static article object to accessed from any part of application
                        startActivity(new Intent(ViewAllArticles.this, ViewArticles.class)); //go to ViewArticles activity
                    }
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N) //annotation means this function runs on android versions higher than Marshmallow
    private List<articleWithNum> reArange(List<articlesWithid> articless) {
        List<articleWithNum> list=new ArrayList<>();
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<String, Integer>();  //create linked hash map to count every author articles
        for (articlesWithid articlesWithid : articless) {
            if (hashMap.containsKey(articlesWithid.getAuthor())) {
                { int num = hashMap.get(articlesWithid.getAuthor());
                hashMap.put(articlesWithid.getAuthor(), num+1);}
            } else
                hashMap.put(articlesWithid.getAuthor(), 1);
        } //loop throw list of articles to count every author articles
        hashMap=sortHashMapByValues(hashMap); //sort hash map to put the authors with the most articles first
        for (String string:hashMap.keySet()){ // sort list with articles form the authors with the most articles first
            for (articlesWithid i:articless)
            {
                if (i.getAuthor().equals(string))
                    list.add(new articleWithNum(i.getAuthor(),i.getBody(),i.getTitle(),i.getKey(),hashMap.get(string)));
            }
        }
        for (int x = 0; x < list.size(); x++) { //sort hash map to put the logged in author articles first
            if (list.get(x).getAuthor().equals(MainActivity.userr.getUserName())) {
                articleWithNum article = list.get(x);
                list.remove(x);
                list.add(0, article);
            } }
        return list; //return sorted list of articles
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LinkedHashMap<String, Integer> sortHashMapByValues(LinkedHashMap<String, Integer> passedMap) {
        LinkedHashMap<String, Integer> reverseSortedMap=new LinkedHashMap<>();
        Map<String,Integer>unSortedMap=new LinkedHashMap<>(passedMap);
        unSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
    }

