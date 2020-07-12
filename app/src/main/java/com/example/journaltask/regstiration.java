package com.example.journaltask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

public class regstiration extends AppCompatActivity {

    @BindView(R.id.txt_username)
    EditText txtUsername;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.switch_admin)
    Switch switchAdmin;
    @BindView(R.id.button)
    Button button;
boolean isAdmin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstiration);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.switch_admin, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_admin:        // check if user choosed to be admin or not
                if (switchAdmin.isChecked())
                    isAdmin=true;
                else
                    isAdmin=false;
                break;
            case R.id.button:
                signUp();
                break;
        }
    }

    private void signUp() {
        if (txtPassword.getText().toString().isEmpty()) //EditText validation
        {txtPassword.setError("Empty field");
        return;}
        if (txtUsername.getText().toString().isEmpty())
        {txtPassword.setError("Empty field");
        return;}
new FireBase().signup(new users(isAdmin, txtPassword.getText().toString(), txtUsername.getText().toString()), new callBack() {
    //send user object to firesbase function to registration and and get call back interface with response
    @Override
    public void onCallback(users user) {

    }

    @Override
    public void isSent(boolean sent) {
        if (sent) {
            finish();
            Toast.makeText(getApplication(),"added",Toast.LENGTH_LONG).show(); }
        else
            Toast.makeText(getApplication(),"Failed ",Toast.LENGTH_LONG).show();

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
