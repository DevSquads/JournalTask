package com.example.journaltask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class home extends AppCompatActivity {

    @BindView(R.id.btn_create_article)
    Button btnCreateArticle;
    @BindView(R.id.btn_view_article)
    Button btnViewArticle;
    @BindView(R.id.btn_binding_articles)
    Button btnBindingArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (MainActivity.userr.isAdmin() == false) // if user not admin hide Binding Articles button that make admin the privilege of accepting articles
            btnBindingArticles.setVisibility(View.GONE);

    }

    @OnClick({R.id.btn_create_article, R.id.btn_view_article, R.id.btn_binding_articles})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_article:
                startActivity(new Intent(home.this,CreateArticle.class)); // go to create articles activity
                break;
            case R.id.btn_view_article:
                startActivity(new Intent(this,ViewAllArticles.class));   // go to view all approved articles activity
                break;
            case R.id.btn_binding_articles:
                    startActivity(new Intent(this,Binding_Arcticles.class)); // go to view binding articles that waiting admin approval
                break;
        }
    }
}
