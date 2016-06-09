package com.example.denis.finansave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denis.finansave.dao.UserDao;
import com.example.denis.finansave.model.Usuario;

import javax.xml.validation.Validator;

public class Register extends Activity {

    private Usuario usuario = new Usuario();
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

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validaRegistro()) {
                   salvarUsuario(v);

                   Intent intent = new Intent(getApplicationContext(), Login.class);
                   startActivity(intent);
                   finish();
               }
            }
        });
    }

    public void salvarUsuario(View view){
        usuario.setNome(etNomeCadastro.getText().toString());
        usuario.setEmail(etEmailCadastro.getText().toString());
        usuario.setSenha(etSenhaCadastro.getText().toString());

        UserDao bd = new UserDao(this);
        bd.inserirUsuario(usuario);

        Toast.makeText(this, "Usuario inserido com sucesso", Toast.LENGTH_LONG).show();

    }

    public boolean validaRegistro(){
        String nome = etNomeCadastro.getText().toString().trim();
        String senha = etSenhaCadastro.getText().toString().trim();
        String email = etEmailCadastro.getText().toString().trim();
        return (!isEmptyFields(nome, senha, email));
    }

    private boolean isEmptyFields(String nome, String senha, String email) {
        if (TextUtils.isEmpty(nome)) {
            etNomeCadastro.requestFocus(); //seta o foco para o campo nome
            etNomeCadastro.setError("Preencha o campo Nome");
            return true;
        } else if (TextUtils.isEmpty(email)) {
            etEmailCadastro.requestFocus(); //seta o foco para o campo Email
            etEmailCadastro.setError("Preencha o campo Email");
            return true;
        }else if (TextUtils.isEmpty(senha)) {
            etSenhaCadastro.requestFocus(); //seta o foco para o campo senha
            etSenhaCadastro.setError("Preencha o campo Senha");
            return true;
        }
        return false;
    }

}
