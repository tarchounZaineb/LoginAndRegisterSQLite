package com.example.loginandregistersqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper    extends SQLiteOpenHelper{
        public static final String DBNAME = "Login.db";

        public DatabaseHelper(@Nullable Context context) {
            super(context, "Login.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table usre(email text primary key,password text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists user");
        }

        //inserting in database
        public boolean insert(String email, String password) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("email", email);
            contentValues.put("password", password);
            long ins = db.insert("user", null, contentValues);
            if (ins == 1) return false;
            else return true;
        }

        //checking if email exists
        public Boolean chkemail(String email) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
            if (cursor.getCount() > 0) return true;
            else return false;

        }

        //checking if email and password
        public Boolean chkemailpass(String email, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? and password=?", new String[]{email, password});
            if (cursor.getCount() > 0) return true;
            else return false;

        }

}

