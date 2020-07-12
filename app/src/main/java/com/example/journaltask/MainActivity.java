package com.example.journaltask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_signIn)
    Button btnSignIn;
    @BindView(R.id.btn_signUp)
    Button btnSignUp;
    @BindView(R.id.edTxt_username)
    EditText edTxtUsername;
    @BindView(R.id.edTxt_password)
    EditText edTxtPassword;
static users userr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_signIn, R.id.btn_signUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                btnSignIn();
                break;
            case R.id.btn_signUp:
                startActivity(new Intent(MainActivity.this,regstiration.class));
                break;
        }
    }

    private void btnSignIn() {
     if (edTxtUsername.getText().toString().isEmpty()) //EditText Validation
     {
         edTxtUsername.setError("Empty Field");
         return;
     }
        if (edTxtPassword.getText().toString().isEmpty())
        {
            edTxtPassword.setError("Empty Field");
            return;
        }
        new FireBase().signIn(edTxtUsername.getText().toString(), edTxtPassword.getText().toString(), new callBack() {//run signIn that search in firebase for matched user and return result with call back
            @Override
            public void onCallback(users user) {
                if (user==null) //if user = null so no matched user in firebase
                {  Toast.makeText(getApplication(),"Wrong password or username",Toast.LENGTH_LONG).show();
            return ;}
               userr=user;                                                             // assign logged in user to static variable to be accessed  throw all the app
                Intent intent=new Intent(MainActivity.this,home.class); // go to home  application
                startActivity(intent);
                finish();
            }

            @Override
            public void isSent(boolean sent) {

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
