package com.suza.labexam2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "suza2017", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE dcsdit2017 (id INTEGER PRIMARY KEY AUTOINCREMENT, fname TEXT, lname TEXT, age INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS dcsdit2017");
        onCreate(db);
    }

    public void register(String fname, String lname, int age) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fname", fname);
        values.put("lname", lname);
        values.put("age", age);

        db.insert("dcsdit2017", null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT fname, lname, age FROM dcsdit2017", null);

        while (cursor.moveToNext()) {
            HashMap<String, String> item = new HashMap<>();
            String fname = cursor.getString(cursor.getColumnIndex("fname"));
            String lname = cursor.getString(cursor.getColumnIndex("lname"));
            String fullName = fname + " " + lname;

            item.put("name", fullName);
            item.put("age", cursor.getString(cursor.getColumnIndex("age")) + " years");

            list.add(item);
        }

        return list;
    }
}
