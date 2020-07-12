package com.example.journaltask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class CreateArticle extends AppCompatActivity {

    @BindView(R.id.txt_authorName)
    EditText txtAuthorName;
    @BindView(R.id.txt_articleTitle)
    EditText txtArticleTitle;
    @BindView(R.id.txt_article)
    EditText txtArticle;
    @BindView(R.id.btn_sendArticle)
    Button btnSendArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);
        ButterKnife.bind(this);
txtAuthorName.setText(MainActivity.userr.getUserName());
    }

    @OnClick(R.id.btn_sendArticle)
    public void onViewClicked() {
        if (txtAuthorName.getText().toString().isEmpty()) //EditText validation
        {txtAuthorName.setError("Empty Field");
        return;}
        if (txtArticle.getText().toString().isEmpty())
        {txtAuthorName.setError("Empty Field");
            return;}
        if (txtArticleTitle.getText().toString().isEmpty())
        {txtAuthorName.setError("Empty Field");
            return;}
        new FireBase().bindingArticles(new articles(txtAuthorName.getText().toString(), //sending article object to firebase function with call back object to send it to forebase as binding article and resive operation status from the callback interface
                txtArticle.getText().toString(),
                txtArticleTitle.getText().toString()), new callBack() {
            @Override
            public void onCallback(users user) {

            }

            @Override
            public void isSent(boolean sent) {
                if (sent) {
                    Toast.makeText(getApplication(), "your article is binding for approvel", Toast.LENGTH_LONG).show();
                finish(); //if it was successful operation finish activity
                }
                else
                    Toast.makeText(getApplication(),"Some thing went wrong please try again later",Toast.LENGTH_LONG).show();
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
