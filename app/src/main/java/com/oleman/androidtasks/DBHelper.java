package com.oleman.androidtasks;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "catsDb";
    public static final String TABLE_CATS = "cats";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COLOR = "color";
    public static final String KEY_AGE = "age";
    public static final String KEY_CAREER = "career";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate - метод, который будет вызван, если БД, к которой мы хотим подключиться – не существует
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_CATS+ "("
                +KEY_ID+" integer primary key,"
                +KEY_NAME+" text,"
                +KEY_AGE+" INT,"
                +KEY_COLOR+" text,"
                +KEY_CAREER+" text"
                + ")");

    }

    /**
     * onUpgrade - будет вызван в случае, если мы пытаемся подключиться к БД более новой версии, чем существующая
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_CATS);

        onCreate(sqLiteDatabase);
    }
}
