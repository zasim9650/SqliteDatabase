package com.example.sqlitdatabasedemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void login(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    public void register(View view){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}