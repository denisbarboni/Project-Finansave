package com.example.denis.finansave;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.dao.UserDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.util.Calendar;

public class Lancar extends FragmentActivity {

    private Movimentacao mov = new Movimentacao();
    EditText etData, etValor, etDesc;
    Button btCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar);

        etValor = (EditText) findViewById(R.id.etValor);
        etData = (EditText) findViewById(R.id.etData);
        etDesc = (EditText) findViewById(R.id.etDesc);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("nulo?", "CLICK CADASTRAR");
                salvarMovimentacao(v);
            }
        });
    }

    public void setDate(View v){
        DialogFragment newFragment = new PickerDialogs();
       // newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void salvarMovimentacao(View view){
        mov.setValor(Float.valueOf(etValor.getText().toString()));
        mov.setData(etData.getText().toString());
        mov.setDescricao(etDesc.getText().toString());
        mov.setTipo(TipoMovimentacao.DESPESA);


        MovimentacaoDao bd = new MovimentacaoDao(this);
        bd.inserirMovimentacao(mov);

        Toast.makeText(this, "Movimentacao inserido com sucesso", Toast.LENGTH_LONG).show();

    }
}
