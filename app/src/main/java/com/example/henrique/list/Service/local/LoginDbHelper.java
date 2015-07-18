package com.example.henrique.list.Service.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoginMoti.db";

    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + LoginDbContract.TABLE_NAME + " (" +
                LoginDbContract._ID + " INTEGER PRIMARY KEY," +
                LoginDbContract.COLUMN_NAME_ID + " INTEGER, " +
                LoginDbContract.COLUMN_NAME_LOGIN + " TEXT, " +
                LoginDbContract.COLUMN_NAME_PASSWORD + " TEXT, " +
                LoginDbContract.COLUMN_NAME_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + LoginDbContract.TABLE_NAME;

    public LoginDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
