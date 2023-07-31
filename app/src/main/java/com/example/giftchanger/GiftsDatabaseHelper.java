package com.example.giftchanger;
import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class GiftsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gifts.db";
    private static final int DATABASE_VERSION = 1;

    public GiftsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableGifts = "CREATE TABLE IF NOT EXISTS gifts (id INTEGER PRIMARY KEY, name TEXT, gift TEXT, gender TEXT)";
        db.execSQL(createTableGifts);

        String createTableGiftAges = "CREATE TABLE IF NOT EXISTS gift_ages (id INTEGER PRIMARY KEY, gift_id INTEGER, age_id INTEGER, FOREIGN KEY(gift_id) REFERENCES gifts(id))";
        db.execSQL(createTableGiftAges);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gift_ages");
        db.execSQL("DROP TABLE IF EXISTS gifts");
        onCreate(db);
    }



}
