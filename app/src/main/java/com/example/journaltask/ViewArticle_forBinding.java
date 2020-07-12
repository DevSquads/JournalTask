package com.example.journaltask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewArticle_forBinding extends AppCompatActivity {

    @BindView(R.id.txt_authorName)
    TextView txtAuthorName;
    @BindView(R.id.txt_articleTitle)
    TextView txtArticleTitle;
    @BindView(R.id.txt_article)
    TextView txtArticle;
    @BindView(R.id.btn_sendArticle)
    Button btnSendArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article_for_binding);
        ButterKnife.bind(this);
        txtAuthorName.setText(Binding_Arcticles.choosenArticle.getAuthor()); //show  choosen article data
        txtArticleTitle.setText(Binding_Arcticles.choosenArticle.getTitle());
        txtArticle.setText(Binding_Arcticles.choosenArticle.getBody());
    }

    @OnClick(R.id.btn_sendArticle)
    public void onViewClicked() {         //approve on button clicked
        new FireBase().deleteFromBinding(Binding_Arcticles.choosenArticle, new callBack() {
            @Override
            public void onCallback(users user) {

            }

            @Override
            public void isSent(boolean sent) {
if (sent){
    Toast.makeText(getApplication(), "Approved", Toast.LENGTH_SHORT).show();
    finish();
}
            }

            @Override
            public void retriveArticles(List<articlesWithid> article) {

            }

            @Override
            public void retriveAllArticles(List<articlesWithid> article) {

            }
        });


    }
}
