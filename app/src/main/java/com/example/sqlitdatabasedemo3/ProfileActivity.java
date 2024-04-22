package com.example.sqlitdatabasedemo3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    UserModal userModal;
    String email1;
    TextView profile_name,profile_email,profile_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_name = (TextView) findViewById(R.id.profile_user_name);
        profile_email =  (TextView) findViewById(R.id.profile_user_email);
        profile_gender =(TextView) findViewById(R.id.profile_user_gender);

        email1= getIntent().getStringExtra("key_email");

        getUserDetails();
    }
    public void userLogout(View view){
        startActivity(new Intent(ProfileActivity.this,LoginActivity.class));


    }

    public  void getUserDetails(){
        DbHelper dbHelper= new DbHelper(this);
        ArrayList<UserModal> al= dbHelper.getLoggedinUserDetail(email1);
         userModal= al.get(0);

        profile_name.setText(userModal.getName());
        profile_email.setText(userModal.getEmail());
        profile_gender.setText(userModal.getGender());


    }

    public  void getAllUserDetails(View view){
        DbHelper dbHelper= new DbHelper(this);
        ArrayList al = dbHelper.getAllUserDetailsHelper();
        Toast.makeText(this, ""+al, Toast.LENGTH_SHORT).show();
    }

    public  void updateProfile(View view){

        Intent intent = new Intent(ProfileActivity.this,UpdateProfileActivity.class);
        intent.putExtra("key_usermodal",userModal);
        startActivity(intent);

    }

    public void deleteProfile(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Profile");
        builder.setMessage("Are you sure you want to delete your profile:?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DbHelper dbHelper= new DbHelper(ProfileActivity.this);
               boolean b= dbHelper.deleteProfileHelper(userModal.getEmail());

               if(b){
                   Toast.makeText(ProfileActivity.this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(ProfileActivity.this,MainActivity.class));
               }

               else {
                   Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
               }

            }
        });
        builder.show();

    }
}