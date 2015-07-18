package com.example.henrique.list.Service.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.henrique.list.Service.CustomerService;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.util.constants.PersonType;

public class LocalLoginService {

    private SQLiteDatabase db = null;

    public LocalLoginService(Context context) {
        LoginDbHelper loginDbHelper = new LoginDbHelper(context);
        db = loginDbHelper.getWritableDatabase();
    }

    public CustomerDTO login() {
        // COLUMNS TO RETURN
        String[] projection = {
            LoginDbContract.COLUMN_NAME_ID,
            LoginDbContract.COLUMN_NAME_LOGIN,
            LoginDbContract.COLUMN_NAME_PASSWORD,
            LoginDbContract.COLUMN_NAME_TYPE};
        // EXECUTE QUERY
        Cursor cursor = db.query(LoginDbContract.TABLE_NAME, projection,
                null, null, null, null, null);
        // GET THE DATA
        CustomerDTO customerDTO = null;
        if (cursor.moveToFirst()) {
            customerDTO = new CustomerDTO();
            customerDTO.setId(cursor.getLong(cursor
                    .getColumnIndexOrThrow(LoginDbContract.COLUMN_NAME_ID)));
            customerDTO.setLogin(cursor.getString(cursor
                    .getColumnIndexOrThrow(LoginDbContract.COLUMN_NAME_LOGIN)));
            customerDTO.setPassword(cursor.getString(cursor
                    .getColumnIndexOrThrow(LoginDbContract.COLUMN_NAME_PASSWORD)));
            PersonType personType = PersonType.getEnumFromValue(cursor
                    .getString(cursor.getColumnIndexOrThrow(LoginDbContract.COLUMN_NAME_TYPE)));
            customerDTO.setType(personType);
        }
        return customerDTO;
    }

    public void logoff() {
        // DELETE ALL ROWS ON THE TABLE
        db.delete(LoginDbContract.TABLE_NAME, null, null);
    }

    public void registerLogin(CustomerDTO customerDTO) {
        // REGISTER THE LOGIN ON THE TABLE
        ContentValues values = new ContentValues();
        values.put(LoginDbContract.COLUMN_NAME_ID, customerDTO.getId());
        values.put(LoginDbContract.COLUMN_NAME_LOGIN, customerDTO.getLogin());
        values.put(LoginDbContract.COLUMN_NAME_PASSWORD, customerDTO.getPassword());
        values.put(LoginDbContract.COLUMN_NAME_TYPE, customerDTO.getType().getValue());
        db.insertOrThrow(LoginDbContract.TABLE_NAME, null, values);
    }
}
