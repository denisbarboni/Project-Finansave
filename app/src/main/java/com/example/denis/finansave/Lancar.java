package com.example.denis.finansave;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.dao.UserDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.List;

public class Lancar extends Activity {

    private Movimentacao movimentacao;
    EditText etData, etValor, etDesc;
    Button btCadastrar;
    ImageView imgFoto, imgFotoTirada;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar);

        etValor = (EditText) findViewById(R.id.etValor);
        etData = (EditText) findViewById(R.id.etData);
        etDesc = (EditText) findViewById(R.id.etDesc);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        imgFotoTirada = (ImageView) findViewById(R.id.imgFotoTirada);

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamera();
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaLancamento()) {
                    salvarMovimentacao(v);
                    finish();
                }
            }
        });

        movimentacao = new Movimentacao();
        movimentacao.setTipo(TipoMovimentacao.values()[getIntent().getIntExtra("tipo", 0)]);

        if (getIntent().getLongExtra("id", 0) != 0){
            movimentacao.setId(getIntent().getLongExtra("id", 0));
            movimentacao.setValor(getIntent().getFloatExtra("valor", 0f));
            movimentacao.setData(getIntent().getStringExtra("data"));
            movimentacao.setDescricao(getIntent().getStringExtra("desc"));
        }

        if (movimentacao != null){
            etData.setText(movimentacao.getData());
            etValor.setText(String.valueOf(movimentacao.getValor()));
            etDesc.setText(movimentacao.getDescricao());
        }
    }
    //Mostrar o Campo Data
    public void showDatePickerDialog(View v) {
        PickerDialogs dialog = new PickerDialogs(v);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, "DatePicker");
    }

    public void salvarMovimentacao(View view){
        movimentacao.setValor(Float.valueOf(etValor.getText().toString()));
        movimentacao.setData(etData.getText().toString());
        movimentacao.setDescricao(etDesc.getText().toString());

        MovimentacaoDao bd = new MovimentacaoDao(this);
        bd.inserirMovimentacao(movimentacao);

        //bd.editarMovimentacao(movimentacao);

        //Toast.makeText(this, "Despesa inserida com sucesso", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "Receita inserida com sucesso", Toast.LENGTH_LONG).show();
    }

    //Abrir Camera
    public void abrirCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requesCode, int resultCode, Intent data){
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                Bitmap img = (Bitmap) bundle.get("data");
                if (imgFoto != null)
                imgFotoTirada.setImageBitmap(img);
            }
        }
    }

    public boolean validaLancamento(){
        Float valor = Float.valueOf(etValor.getText().toString().trim());
        String data = etData.getText().toString().trim();
        String descricao = etDesc.getText().toString().trim();
        return (!isEmptyFields(valor, data, descricao));
    }

    public boolean isEmptyFields(Float valor, String data, String descricao){

        if (TextUtils.isEmpty(valor.toString().trim())){
            etValor.requestFocus(); //seta o foco para o campo Email
            etValor.setError("Preencha o campo Valor");
            return true;
        }else if (TextUtils.isEmpty(data)) {
            etData.requestFocus(); //seta o foco para o campo Senha
            etData.setError("Preencha o campo Data");
            return true;
        }else if (TextUtils.isEmpty(descricao)) {
            etDesc.requestFocus(); //seta o foco para o campo Senha
            etDesc.setError("Preencha o campo Descricão");
            return true;
        }
        return false;
    }
}
