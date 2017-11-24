package com.example.michael.pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Michael on 20-11-2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static TodoDatabase getInstance(Context context) {
       if(instance == null) {
           instance = new TodoDatabase(context, "todos", null, 1);
       }
       return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table todos (_id INTEGER PRIMARY KEY, title TEXT, completed INTEGER);");
        //sqLiteDatabase.execSQL("INSERT INTO todos VALUES(1 ,'homework', 1);");
        //sqLiteDatabase.execSQL("INSERT INTO todos VALUES(2 ,'homework1', 0);");
        //sqLiteDatabase.execSQL("INSERT INTO todos VALUES(3 ,'homework2', 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(sqLiteDatabase);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos", null);
        return cursor;
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("completed", completed);

        getWritableDatabase().insert("todos", null, cv);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("completed", completed);
        db.update("todos", cv, "_id = " + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("todos", "_id = " + id, null);
    }
}