package com.example.henrique.list;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ClientView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        Intent intent = getIntent();

        //recebe usuario da Intent
        String clientName = intent.getStringExtra("nameClient");

        String clientEmail = "massaro@karabacana.com.br"; // buscar do banco
        String clientPhoneNumber = "69696969"; // buscar do banco
        String [] schedules = {"Agend 1", "Agend 2", "Agend 3"}; // agendamentos do usuario atual

        TextView myTextView1 = (TextView) findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) findViewById(R.id.textView2);
        ListView myListView1 = (ListView) findViewById(R.id.ListView);
        ImageView phoneImage = (ImageView) findViewById(R.id.imageView2);

        ListAdapter myAdapter = new MyAdapterClientSmall(this, schedules);

        //Configurando as variaveis do cabecalho
        myTextView1.setText(clientName);
        myTextView2.setText(clientEmail);
        //configurando listener para telefonar.



        //Configurando lista dos clientes
        myListView1.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
