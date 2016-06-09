package com.example.denis.finansave;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.denis.finansave.adapters.TesteAdapter;
import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Despesas extends AppCompatActivity {

    ListView listView;
    TextView txtValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtValorTotal = (TextView) findViewById(R.id.txtValorTotal);
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movimentacao movimentacao = (Movimentacao) listView.getAdapter().getItem(position);

                Intent intent = new Intent(getApplicationContext(), Lancar.class);
                intent.putExtra("id", movimentacao.getId());
                intent.putExtra("valor", movimentacao.getValor());
                intent.putExtra("desc", movimentacao.getDescricao());
                intent.putExtra("data", movimentacao.getData());
                intent.putExtra("tp", movimentacao.getTipo());
                intent.putExtra("foto", movimentacao.getFoto());

                startActivity(intent);
            }
        });

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabDespesas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Lancar.class);
                i.putExtra("tipo", TipoMovimentacao.DESPESA.ordinal());
                startActivity(i);
            }
        });
    }

    //Calculo do Valor Total das Despesas acumuladas
    private Float calculaTotalDespesasAcumuladas() {
        List<Movimentacao> lstMov =  new MovimentacaoDao(this).getMovimentacao(TipoMovimentacao.DESPESA);

        Float vlr = 0f;

        for (int i = 0; i < lstMov.size(); i++) {
            vlr += lstMov.get(i).getValor();
        }
        return vlr;
    }

    public void onStart(){
        super.onStart();
        Log.i("onStart Despesas", "Despesas");
        LoadThread loadThread = new LoadThread();
        loadThread.execute();
        listView.setAdapter(new TesteAdapter(this, new MovimentacaoDao(this).getMovimentacao(TipoMovimentacao.DESPESA)));

    }

    //Cria uma Thread para realizar a soma das Despesas Acumuladas
    class LoadThread extends AsyncTask<Void,Void,Float> {

        @Override
        protected Float doInBackground(Void... params) {
            return calculaTotalDespesasAcumuladas();
        }

        @Override
        protected void onPostExecute(Float soma) {
            super.onPostExecute(soma);
            NumberFormat numberFormat = new DecimalFormat(".##");
            txtValorTotal.setText(numberFormat.format(soma));
        }
    }

   /* public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.adicionar) {
            Intent i = new Intent(getApplicationContext(), Lancar.class);
            i.putExtra("tipo", TipoMovimentacao.DESPESA.ordinal());
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
