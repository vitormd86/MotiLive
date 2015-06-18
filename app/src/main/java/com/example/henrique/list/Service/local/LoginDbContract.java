package com.example.henrique.list.Service.local;

import android.provider.BaseColumns;

public abstract class LoginDbContract implements BaseColumns {
    // TABLE AND COLUMN NAMES
    public static final String TABLE_NAME = "CUSTOMER";
    public static final String COLUMN_NAME_ID = "ID";
    public static final String COLUMN_NAME_LOGIN = "LOGIN";
    public static final String COLUMN_NAME_PASSWORD = "PASSWORD";
    public static final String COLUMN_NAME_TYPE = "TYPE";
}
