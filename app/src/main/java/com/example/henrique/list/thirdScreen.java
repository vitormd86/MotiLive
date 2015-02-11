package com.example.henrique.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Henrique on 09/02/2015.
 */
//inicia a terceira view, que está vazia no momento.
public class thirdScreen extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        Intent intent = getIntent();
        final String Profissional;
        Profissional = intent.getStringExtra("Profissional");
        Intent intent2 = getIntent();
        final String Servico;
        Servico = intent2.getStringExtra("Servico");
        // ´pequeno teste pra ver se chegaram os dados q eu queria
        Toast.makeText(thirdScreen.this, Profissional, Toast.LENGTH_SHORT).show();
        Toast.makeText(thirdScreen.this, Servico, Toast.LENGTH_SHORT).show();


    }
}
