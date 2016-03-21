package com.example.denis.finansave;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Usuario usuario = new Usuario();
    EditText etEmail, etSenha;
    Button btEntrar;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btEntrar = (Button) findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(etEmail.getText().toString(), etSenha.getText().toString());
            }
        });
    }


    public void Login(String email, String senha){
        if(email.contentEquals("teste") && senha.contentEquals("123")){
            telaPrincipal();
        }
    }

    public void telaPrincipal(){
        Intent intent = new Intent(getApplicationContext(), TelaPrincipal.class);
        startActivity(intent);
    }

    public void verificaSessao(){
        sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean logado = sharedPref.getBoolean("logado", false);
        if(logado){
            ////iniciaTelaPrincipal();
        }
    }

    public boolean validaLogin(String email, String senha){
        if(email.isEmpty())
            return false;

        if(senha.isEmpty())
            return false;

        return true;
    }



}
