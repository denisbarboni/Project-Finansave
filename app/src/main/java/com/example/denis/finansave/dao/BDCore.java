package com.example.denis.finansave.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by denis on 16/03/2016.
 */
public class BDCore extends SQLiteOpenHelper {


    public static String DATABASE = "finansave.db";
    public static int VERSION = 1;

    public BDCore(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDao.SQL_CREATE_USUARIO);
        db.execSQL(MovimentacaoDao.SQL_CREATE_MOVIMENTACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
