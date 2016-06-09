package com.example.denis.finansave;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.denis.finansave.adapters.TesteAdapter;
import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Receitas extends AppCompatActivity {

    ListView lvReceitas;
    TextView txtValorTotalReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtValorTotalReceita = (TextView) findViewById(R.id.txtValorTotalReceita);
        lvReceitas = (ListView) findViewById(R.id.lvReceitas);

        lvReceitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movimentacao c = (Movimentacao) lvReceitas.getAdapter().getItem(position);

                Intent t = new Intent(getApplicationContext(), Lancar.class);
                t.putExtra("id", c.getId());
                t.putExtra("valor", c.getValor());
                t.putExtra("desc", c.getDescricao());
                t.putExtra("data", c.getData());
                t.putExtra("tp", c.getTipo());

                startActivity(t);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabReceitas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Lancar.class);
                i.putExtra("tipo", TipoMovimentacao.RECEITA.ordinal());
                startActivity(i);
            }
        });
    }

    private Float calculaTotalReceitasAcumuladas() {
        //Calculo do Valor Total das Receitas acumuladas
        List<Movimentacao> lstMov =  new MovimentacaoDao(this).getMovimentacao(TipoMovimentacao.RECEITA);

        Float vlr = 0f;

        for (int i = 0; i < lstMov.size(); i++) {
            vlr += lstMov.get(i).getValor();
        }
        return vlr;
    }

    public void onStart(){
        super.onStart();
        Log.i("onStart Receitas", "Receitas");
        LoadThread loadThread = new LoadThread();
        loadThread.execute();
        lvReceitas.setAdapter(new TesteAdapter(this, new MovimentacaoDao(this).getMovimentacao(TipoMovimentacao.RECEITA)));

    }
    //Cria uma Thread para realizar a soma das Receitas Acumuladas
    class LoadThread extends AsyncTask<Void,Void,Float> {

        @Override
        protected Float doInBackground(Void... params) {
            return calculaTotalReceitasAcumuladas();
        }

        @Override
        protected void onPostExecute(Float soma) {
            super.onPostExecute(soma);
            NumberFormat numberFormat = new DecimalFormat(".##");
            txtValorTotalReceita.setText(numberFormat.format(soma));
        }
    }


}
