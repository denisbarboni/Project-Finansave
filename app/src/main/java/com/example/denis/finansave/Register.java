package com.example.denis.finansave;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Register extends Activity {

    Usuario usuario = new Usuario();
    EditText etNomeCadastro, etEmailCadastro, etSenhaCadastro;
    Button btCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNomeCadastro = (EditText) findViewById(R.id.etNomeCadastro);
        etEmailCadastro = (EditText) findViewById(R.id.etEmailCadastro);
        etSenhaCadastro = (EditText) findViewById(R.id.etSenhaCadastro);

        btCadastro = (Button) findViewById(R.id.btCadastro);
    }



}
