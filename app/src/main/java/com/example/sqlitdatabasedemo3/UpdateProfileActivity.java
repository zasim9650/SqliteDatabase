package com.example.sqlitdatabasedemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UpdateProfileActivity extends AppCompatActivity {
        EditText up_name,up_gender;
        TextView up_email;
        UserModal userModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        up_name =(EditText) findViewById(R.id.up_profile_user_name);
        up_gender =(EditText)  findViewById(R.id.up_profile_user_gender);
        up_email =(TextView) findViewById(R.id.up_profile_user_email);

        userModal = (UserModal) getIntent().getSerializableExtra("key_usermodal");
        up_email.setText(userModal.getEmail());
        up_name.setText(userModal.getName());
        up_gender.setText(userModal.getGender());

    }

    public void updateMyProfile(View view){

    String name1 = up_name.getText().toString();
    String gender1 = up_gender.getText().toString();

    DbHelper dbHelper= new DbHelper(this);
   boolean b= dbHelper.updateProfileHelper(userModal.getEmail(),name1,gender1);

   if(b==true){
       Toast.makeText(this, "Values Updated Successfully", Toast.LENGTH_SHORT).show();
   }

   else {
       Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
   }

    }


}