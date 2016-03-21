package com.example.denis.finansave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btAcessarConta, btCadPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAcessarConta = (Button) findViewById(R.id.btAcessarConta);
        btCadPrincipal = (Button) findViewById(R.id.btCadPrincipal);


        btAcessarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Eae", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        btCadPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Eae", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }


}
