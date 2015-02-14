package com.example.henrique.list;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfessionalHours extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_hours);
        Intent intent = getIntent();

        String professionalName = "Leandro Massaro Kubota";
        TextView myTextView1 = (TextView) findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) findViewById(R.id.textView2);

        //Configurando as variaveis do cabecalho
        myTextView1.setText(professionalName);
        //Configurando data a partir dos dados da Intent
        myTextView2.setText(
               intent.getStringExtra("dayOfMonth") + "/" +
               intent.getStringExtra("month") + "/" +
               intent.getStringExtra("year")
        );
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
