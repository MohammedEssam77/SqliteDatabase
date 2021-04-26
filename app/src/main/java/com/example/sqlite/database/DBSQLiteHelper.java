package com.example.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DBSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data.db";
    private static final String TABLE_NAME = "mytable";
    private static final String RESULTS_COLUMN_ID = "id";
    private static final String RESULTS_COLUMN_NAME = "name";
    private static final String RESULTS_COLUMN_NUMBER = "number";
    private static final String RESULTS_COLUMN_EMAIL = "email";

    public DBSQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                RESULTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RESULTS_COLUMN_NAME + " TEXT, " +
                RESULTS_COLUMN_EMAIL + " TEXT, " +
                RESULTS_COLUMN_NUMBER + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(sqLiteDatabase);

    }

    public void insertData(String name, String email, String number) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("number", number);
        sqLiteDatabase.insert("mytable", null, contentValues);

    }

    public List<DataModel> getdata() {
        List<DataModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from mytable", null);
        DataModel dataModel;
        while (cursor.moveToNext()) {
            dataModel = new DataModel();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String email = cursor.getString(3);
            dataModel.setId(id);
            dataModel.setName(name);
            dataModel.setMail(email);
            dataModel.setNumber(number);
            data.add(dataModel);
        }
        return data;
    }

    public void clearDatabase() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String clearDBQuery = "DELETE FROM " + "mytable";
        sqLiteDatabase.execSQL(clearDBQuery);
    }

    public void deleteEntry(int row) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("mytable", RESULTS_COLUMN_ID + "=" + row, null);
    }
}
