package com.example.sqlitdatabasedemo3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "demo_db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE Register(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT ,password TEXT,gender TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Register");
        onCreate(db);

    }

    public boolean registerUserHelper(String name, String email, String pass, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", pass);
        contentValues.put("gender", gender);

        long l= sqLiteDatabase.insert("Register", null, contentValues);
        sqLiteDatabase.close();

        if(l>0){
            return true;

        }
        else {
            return false;
        }
    }
    boolean loggedin;
    public  boolean login(String email1,String pass1){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT* FROM register WHERE email ='"+email1+"'AND password ='"+pass1+"'",null);
        if(cursor.moveToFirst()){
            loggedin= true;
        }
        else{
            loggedin= false;
        }
        return loggedin;
    }

    public ArrayList<UserModal> getLoggedinUserDetail(String email1){
        ArrayList<UserModal> al = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " SELECT * FROM register WHERE email = '"+email1+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String gender = cursor.getString(4);

            UserModal userModal = new UserModal();

            userModal.setName(name);
            userModal.setEmail(email);
            userModal.setGender(gender);


            al.add(userModal);
        }
        return al;
    }

    public ArrayList getAllUserDetailsHelper(){
        ArrayList aluser = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register",null);

        if(cursor.moveToFirst()){
            do{
                ArrayList al = new ArrayList();
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String gender = cursor.getString(4);

                al.add(name);
                al.add(email);
                al.add(gender);

                aluser.add(al);
            }while (cursor.moveToNext());

        }

        return aluser;
    }

    public boolean updateProfileHelper(String email1,String name1,String gender1){
       SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("name",name1);
       contentValues.put("gender",gender1);

      int i= sqLiteDatabase.update("register",contentValues,"email=?",new String[]{email1});

      if(i>0){
        return true;
      }
      else {
          return false;
      }

    }

    public boolean deleteProfileHelper(String email1){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       int i= sqLiteDatabase.delete("register","email =?",new String[]{email1});

       if(i>0){
           return true;
       }
       else {
           return false;
       }


    }

    public boolean checkEmailIdExistHelper(String email1){

       SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
       Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email ='"+email1+"'",null);

       if(cursor.moveToFirst()){
         return true;

       }
       else {
           return false;
       }
    }

}

