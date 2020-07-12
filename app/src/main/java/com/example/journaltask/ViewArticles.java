package com.example.journaltask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewArticles extends AppCompatActivity {

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
        setContentView(R.layout.activity_view_articles);
        ButterKnife.bind(this);
        txtAuthorName.setText(ViewAllArticles.choosenArticle.getAuthor());
        txtArticle.setText(ViewAllArticles.choosenArticle.getBody());
        txtArticleTitle.setText(ViewAllArticles.choosenArticle.getTitle());
        if (!MainActivity.userr.isAdmin()) //if user not admin hide delete button
            btnSendArticle.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_sendArticle)
    public void onViewClicked() { // delete article by the admin
        new FireBase().DeleteArtcle(ViewAllArticles.choosenArticle, new callBack() {
            @Override
            public void onCallback(users user) {

            }

            @Override
            public void isSent(boolean sent) {
            if (sent) {
               Toast.makeText(getApplication(), "Deleted Successfully", Toast.LENGTH_LONG).show();
               finish(); }
            else
                Toast.makeText(getApplication(), "Something wrong  happened", Toast.LENGTH_LONG).show(); }

            @Override
            public void retriveArticles(List<articlesWithid> article) {

            }

            @Override
            public void retriveAllArticles(List<articlesWithid> article) {

            }
        });
        finish();

    }
}
