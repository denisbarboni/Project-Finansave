package com.example.denis.finansave;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.denis.finansave.dao.MovimentacaoDao;
import com.example.denis.finansave.model.Movimentacao;
import com.example.denis.finansave.model.TipoMovimentacao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Finansave extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imgMenu;
    TextView lblDespesas;
    TextView lblReceitas;
    TextView lblVisaoGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finansave);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        TextView nomeMenu = (TextView) header.findViewById(R.id.NomeMenu);
        TextView emailMenu = (TextView) header.findViewById(R.id.EmailMenu);
        //imgMenu = (ImageView) header.findViewById(R.id.imgMenu);

        String nome = getIntent().getStringExtra("email");
        String email = getIntent().getStringExtra("nome");

        if((nome != "" || nome != null) &&(email != "" || email != null) ) {
            nomeMenu.setText(nome);
            emailMenu.setText(email);
        }

        LinearLayout Receitas = (LinearLayout) findViewById(R.id.Receitas);
        LinearLayout Despesas = (LinearLayout) findViewById(R.id.Despesas);
        lblDespesas = (TextView) findViewById(R.id.lblDespesas);
        lblReceitas = (TextView) findViewById(R.id.lblReceitas);
        lblVisaoGeral = (TextView) findViewById(R.id.lblVisaoGeral);

        //Botão que chama a Tela Receitas
        Receitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Receitas.class);
                startActivity(i);
            }
        });

        //Botão que chama a Tela Despesas
        Despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Despesas.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("onStart Finansave", "Finansave");
        calculoTotalVisaoGeral();
        LoadThread loadThreadDespesa = new LoadThread();
        LoadThreadReceita loadThreadReceita = new LoadThreadReceita();
        loadThreadDespesa.execute();
        loadThreadReceita.execute();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i("onRestart Finansave", "Finansave");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("onPause Finansave", "Finansave");
    }

    private void calculoTotalVisaoGeral(){
        Float somaDespesas = calculaTotalDespesasAcumuladas();
        Float somaReceitas = calculaTotalReceitasAcumuladas();

        double total = (somaReceitas - somaDespesas);
        NumberFormat numberFormat = new DecimalFormat(".##");
        lblVisaoGeral.setText(numberFormat.format(total));
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

    //Cria uma Thread para realizar a soma das Despesas Acumuladas
    class LoadThread extends AsyncTask<Void,Void,Float>{

        @Override
        protected Float doInBackground(Void... params) {
            return calculaTotalDespesasAcumuladas();
        }

        @Override
        protected void onPostExecute(Float soma) {
            super.onPostExecute(soma);
            //lblDespesas.setText(soma.toString());
            NumberFormat numberFormat = new DecimalFormat(".##");
            lblDespesas.setText(numberFormat.format(soma));
        }
    }

    //Calculo do Valor Total das Receitas acumuladas
    private Float calculaTotalReceitasAcumuladas() {
        List<Movimentacao> lstMov =  new MovimentacaoDao(this).getMovimentacao(TipoMovimentacao.RECEITA);

        Float vlr = 0f;

        for (int i = 0; i < lstMov.size(); i++) {
            vlr += lstMov.get(i).getValor();
        }
        return vlr;
    }

    //Thread para realizar a soma do Total das Receitas
    class LoadThreadReceita extends AsyncTask<Void,Void,Float>{

        @Override
        protected Float doInBackground(Void... params) {
            return calculaTotalReceitasAcumuladas();
        }

        @Override
        protected void onPostExecute(Float soma) {
            super.onPostExecute(soma);
            //lblReceitas.setText(soma.toString());
            NumberFormat numberFormat = new DecimalFormat(".##");
            lblReceitas.setText(numberFormat.format(soma));
        }
    }

    //Chamar Menu DrawLayout
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.finansave, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
           // finish();
        }

        return super.onOptionsItemSelected(item);
    }
*/

    // MENU DRAW LAYOUT
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);
    }

    public void onActivityResult(int requesCode, int resultCode, Intent data){
        if(data!=null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                Bitmap img = (Bitmap) bundle.get("data");
                //if (imgMenu != null)
                    //imgMenu.setImageBitmap(img);
            }
        }
    }


}
