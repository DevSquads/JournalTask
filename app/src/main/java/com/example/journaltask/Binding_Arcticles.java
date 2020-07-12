package com.example.journaltask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journaltask.adapters.ExampleAdapter2;
import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Binding_Arcticles extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
RecyclerView.LayoutManager lm;
ExampleAdapter2 exampleAdapter2;
    static articlesWithid choosenArticle;
    ModelViewModel modelViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding__arcticles);
        ButterKnife.bind(this);
                lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(lm);
        func();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        func(); //run this function this time every time user come back to activity
    }

   private void func(){
        modelViewModel= ViewModelProviders.of(this).get(ModelViewModel.class); //create object from mvvm
        modelViewModel.getArticles();                                                  // run finction getArticles from mvvm
        modelViewModel.liveData.observe(this, new Observer<List<articlesWithid>>() { // observe livedata for changes
            @Override
            public void onChanged(List<articlesWithid> articlesWithids) { //observed kivedata return kist of articles
                exampleAdapter2=new ExampleAdapter2(articlesWithids);     // passs list of articles to adapter
                recyclerView.setAdapter(exampleAdapter2);                 // pass adapter to recyclerView
                exampleAdapter2.setOnitemClickedListener(new ExampleAdapter2.onClickListener() { // on recyclerView item clicked lister
                    @Override
                    public void onItemClick(int position) {
                        startActivity(new Intent(Binding_Arcticles.this,ViewArticle_forBinding.class));
                        choosenArticle=articlesWithids.get(position);
                    }
                });
            }

        });

    }
}
