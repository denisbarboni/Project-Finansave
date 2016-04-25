package com.example.denis.finansave.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.denis.finansave.Despesas;
import com.example.denis.finansave.model.Usuario;

/**
 * Created by denis on 16/03/2016.
 */
public class UserDao {

    BDCore auxbd;

    public static  String TABLE_NAME = "usuario";
    public static  String COLUMN_NAME_ID = "_id";
    public static  String COLUMN_NAME = "nome";
    public static  String COLUMN_NAME_EMAIL = "email";
    public static  String COLUMN_NAME_SENHA = "senha";

    public static  String SQL_CREATE_USUARIO =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_NAME_EMAIL + " TEXT, "+
                    COLUMN_NAME_SENHA + " TEXT );";


    public UserDao(Context context){
        auxbd = new BDCore(context);
   }

    public void inserirUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME, usuario.getNome());
        valores.put(COLUMN_NAME_EMAIL, usuario.getEmail());
        valores.put(COLUMN_NAME_SENHA, usuario.getSenha());

        Long id = auxbd.getWritableDatabase().insert(TABLE_NAME, null, valores);

        Log.i("inserir usuario","o id eh "+ id);

    }

    public Usuario getUser(String email, String senha) {
        SQLiteDatabase db = auxbd.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_NAME_ID,
                        COLUMN_NAME, COLUMN_NAME_EMAIL, COLUMN_NAME_SENHA }, COLUMN_NAME_EMAIL + "= ? AND "+ COLUMN_NAME_SENHA + "= ?",
                new String[] { email, senha }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Usuario usuario = new Usuario(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

        Log.i("select usuario","o id eh "+ usuario.toString());

        return usuario;
    }

}
