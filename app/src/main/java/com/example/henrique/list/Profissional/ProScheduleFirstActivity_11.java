package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.henrique.list.R;

/**
 * Created by Cristor on 08/04/2015.
 */
public class ProScheduleFirstActivity_11 extends ActionBarActivity {

    int nextScreen;
    EditText nameText;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_first_11);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //iniciando componentes da tela
        nameText = (EditText) findViewById(R.id.completeNameProET_11);
        //recebendo int que avisa o drawer qual fragment abrir
        Bundle extras = getIntent().getExtras();
        nextScreen = extras.getInt("nextScreen");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                //inicia argumentos para a tela proScheduleHoursFragment12 atraves da drawerActivity
                Bundle extras = new Bundle();
                extras.putString("selectedClient", nameText.getText().toString());
                extras.putString("selectedDate", "XX/XX/XX");

                //inicia intent
                Intent confirmIntent = new Intent(this, ProDrawerMenu_15.class);
                confirmIntent.putExtra("nextScreen", nextScreen);
                confirmIntent.putExtra("extras", extras);
                confirmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //todo acrescentar o novo usuario no BD
                Toast.makeText(this, "Cliente adicionado a sua lista.", Toast.LENGTH_SHORT).show();
                startActivity(confirmIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
