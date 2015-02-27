package com.example.henrique.list;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.myAdapterClients;

public class ProfessionalHours extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_professional_hours);
        Intent activityThatCalled = getIntent();

        String professionalName = "Leandro Massaro Kubota"; // buscar do banco
        String[] clientesAgendados = {"Cliente 1 ID", "Cliente 2 ID", "Cliente 3 ID"};  // buscar do banco

        TextView myTextView1 = (TextView) findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) findViewById(R.id.textView2);
        ListView myListView1 = (ListView) findViewById(R.id.ListView);

        ListAdapter myAdapter = new myAdapterClients(this, clientesAgendados);

        //Configurando as variaveis do cabecalho
        myTextView1.setText(professionalName);
        //Configurando data a partir dos dados da Intent
        myTextView2.setText(
              activityThatCalled.getStringExtra("dayOfMonth") + "/" +
              activityThatCalled.getStringExtra("month") + "/" +
              activityThatCalled.getStringExtra("year")
        );
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
