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

    public static String TABLE_NAME = "movimentacao";
    public static String COLUMN_NAME_ID = "id";
    public static String COLUMN_NAME_VALOR = "valor";
    public static String COLUMN_NAME_DATE = "data";
    public static String COLUMN_NAME_DESCRICAO = "descricao";
    public static String COLUMN_NAME_TIPO = "tipo";


    public static  String SQL_CREATE_MOVIMENTACAO =
            "CREATE TABLE movimentacao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "valor TEXT, " +
                    "data TEXT, "+
                    "descricao TEXT, " +
                    "tipo integer );";

    public MovimentacaoDao(Context context){
        auxbd = new BDCore(context);
    }

    public ArrayList<Movimentacao> getMovimentacao(TipoMovimentacao tipoMovimentacao) {

        ArrayList<Movimentacao> lstMov = new ArrayList<Movimentacao>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_NAME_TIPO+" = "+ tipoMovimentacao.ordinal() +" ORDER BY " +COLUMN_NAME_DATE+ " DESC ";

        SQLiteDatabase db = auxbd.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Movimentacao mov = null;

        if (cursor.moveToFirst()) {
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
        return lstMov;
    }

    public void inserirMovimentacao(Movimentacao movimentacao){

        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME_VALOR, movimentacao.getValor());
        valores.put(COLUMN_NAME_DATE, movimentacao.getData());
        valores.put(COLUMN_NAME_DESCRICAO, movimentacao.getDescricao());
        valores.put(COLUMN_NAME_TIPO, movimentacao.getTipo().ordinal());

        Long id = auxbd.getWritableDatabase().insert(TABLE_NAME, null, valores);

        Log.i("inserir Movimentação","id " + id);

    }

    public void editarMovimentacao(Movimentacao mov) {

        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME_VALOR, mov.getValor());
        valores.put(COLUMN_NAME_DATE, mov.getData());
        valores.put(COLUMN_NAME_DESCRICAO, mov.getDescricao());
        valores.put(COLUMN_NAME_TIPO, mov.getTipo().ordinal());
        String[] args = new String[]{Long.toString(mov.getId())};

        int id = auxbd.getWritableDatabase().update(TABLE_NAME, valores, "id = ?", args);
        Log.i("update Movimentação", "o id eh " + id);
    }
}
