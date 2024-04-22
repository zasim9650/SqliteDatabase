package com.example.sqlitdatabasedemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText log_email,log_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_email = findViewById(R.id.log_email);
        log_password = findViewById(R.id.log_password);
    }
    public void loginUser(View view){

        String email = log_email.getText().toString();
        String log_pass = log_password.getText().toString();

        DbHelper dbHelper = new DbHelper(this);
        boolean loggedin = dbHelper.login(email,log_pass);

        if(loggedin){
            Intent intent= new Intent(LoginActivity.this,ProfileActivity.class);
            intent.putExtra("key_email",email);
            startActivity(intent);
        }

        else{
            Toast.makeText(this, "email and password did,t match", Toast.LENGTH_SHORT).show();
        }

    }
}