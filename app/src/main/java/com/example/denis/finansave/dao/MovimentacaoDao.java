package com.example.denis.finansave.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis on 13/04/2016.
 */
public class MovimentacaoDao {

    BDCore auxbd;

    public static  String TABLE_NAME = "movimentacao";
    public static  String COLUMN_NAME_ID = "_id";
    public static  String COLUMN_NAME_VALOR = "valor";
    public static  String COLUMN_NAME_DATE = "data";
    public static  String COLUMN_NAME_DESCRICAO = "descricao";
    public static  String COLUMN_NAME_TIPO = "tipo";

    public static  String SQL_CREATE_MOVIMENTACAO =
            "CREATE TABLE movimentacao (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "valor TEXT, " +
                    "data TEXT, "+
                    "descricao TEXT, " +
                    "tipo integer );";


    public MovimentacaoDao(Context context){
        auxbd = new BDCore(context);
    }

    public void inserirMovimentacao(Movimentacao movimentacao){

        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME_VALOR, movimentacao.getValor());
        valores.put(COLUMN_NAME_DATE, movimentacao.getData());
        valores.put(COLUMN_NAME_DESCRICAO, movimentacao.getDescricao());
        valores.put(COLUMN_NAME_TIPO, movimentacao.getTipo().ordinal());

        Long id = auxbd.getWritableDatabase().insert(TABLE_NAME, null, valores);

        Log.i("inserir Movimentação", "o id eh " + id);

    }

    public List<Movimentacao> getMovimentacao(String tipo) {
        Log.i("Entrou"," get Movimentacao");
        List<Movimentacao> lstMov = new LinkedList<Movimentacao>();

        String query = "SELECT * FROM " + TABLE_NAME+" WHERE tipo="+ tipo;

        SQLiteDatabase db = auxbd.getReadableDatabase();
        Log.i("getReadble","DataBase");

        Log.i("nulo?", db.toString());

        Cursor cursor = db.rawQuery(query, null);

        Log.i("cursor","passou");

        Log.i("lst", "lst");

        Movimentacao mov = null;

        if (cursor.moveToFirst()) {
            Log.i("Deveria ter entrado", "entrou?");
            do {
                mov = new Movimentacao();
                mov.setId(Integer.parseInt(cursor.getString(0)));
                mov.setValor(cursor.getFloat(1));
                mov.setData(cursor.getString(2));
                mov.setDescricao(cursor.getString(3));
                mov.setTipo(TipoMovimentacao.values()[cursor.getInt(4)]);

                lstMov.add(mov);

            }while(cursor.moveToNext());
        }
        Log.d("getMovimentacao()", lstMov.toString());
        return lstMov;
    }
}
