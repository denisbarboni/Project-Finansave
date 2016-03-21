package dao;

import android.content.Context;

/**
 * Created by denis on 16/03/2016.
 */
public class UserDao {

    public static  String TABLE_NAME = "usuario";
    public static  String COLUMN_NAME_ID = "_id";
    public static  String COLUMN_NAME = "nome";
    public static  String COLUMN_NAME_EMAIL = "email";
    public static  String COLUMN_NAME_SENHA = "senha";

    public static  String SQL_CREATE_USUARIO =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + "TEXT," +
                    COLUMN_NAME_EMAIL + "TEXT,"+
                    COLUMN_NAME_SENHA + "TEXT )";


    //public UserDao(Context context){
      //  super(context);
    //}
}
