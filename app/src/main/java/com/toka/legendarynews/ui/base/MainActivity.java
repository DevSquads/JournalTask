package com.toka.legendarynews.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.toka.legendarynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}