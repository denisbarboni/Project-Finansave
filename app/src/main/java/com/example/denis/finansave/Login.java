package com.example.denis.finansave;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denis.finansave.dao.UserDao;
import com.example.denis.finansave.model.Usuario;

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
                if(validaLogin()){
                    Login(etEmail.getText().toString(), etSenha.getText().toString());
                }

            }
        });
    }

    public void Login(String email, String senha){
        usuario = new UserDao(this).getUser(email, senha);

        if(usuario != null)
            telaPrincipal();
    }

    public void telaPrincipal(){
        Intent intent = new Intent(getApplicationContext(), Finansave.class);
        intent.putExtra("email", usuario.getEmail());
        intent.putExtra("nome", usuario.getNome());
        startActivity(intent);
        finish();
    }

    public void verificaSessao(){
        sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean logado = sharedPref.getBoolean("logado", false);

        if(logado){
            //telaPrincipal();
        }
    }

    public boolean validaLogin(){
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        return (!isEmptyFields(email, senha));
    }

    public boolean isEmptyFields(String email, String senha){
        if (TextUtils.isEmpty(email)) {
            etEmail.requestFocus(); //seta o foco para o campo Email
            etEmail.setError("Preencha o campo Email");
            return true;
        }else if (TextUtils.isEmpty(senha)) {
            etSenha.requestFocus(); //seta o foco para o campo Senha
            etSenha.setError("Preencha o campo Senha");
            return true;
        }
        return false;
    }



}
