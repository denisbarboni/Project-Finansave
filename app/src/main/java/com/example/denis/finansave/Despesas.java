package com.example.denis.finansave;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Despesas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       //List<Movimentacao> lstMov =  new MovimentacaoDao(this).getMovimentacao("DESPESA");

        Float vlr = 0f;

       /* for (int i = 0; i < lstMov.size(); i++) {
            vlr += lstMov.get(i).getValor();
        }

        TextView lblValor = (TextView) findViewById(R.id.lblValor);

        lblValor.setText(vlr.toString()); */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabDespesas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Lancar.class);
                startActivity(i);
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.finansave2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.voltar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
