package com.example.henrique.list;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    //OnCreate, é o q a Activity faz logo que inicia, neste caso, inicia os vetores, variáveis e adaptadores
    //Inicia tb a Listener das views( botoes)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] favoriteProfessionals = {"Leandro Massaru Kubota", "Ivo Issao Tobioka", "Michel SantaGuida", "Henrique Ta/mashiro"};// aqui eu inicializa o array de opcoes
        ListAdapter theAdapter = new myAdapter(this, favoriteProfessionals); //inicializa o adaptador de array, pra encaixar o array na lsita
        ListView theListView = (ListView) findViewById(R.id.ListView); // inicializa a view
        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "my adapter"

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getNameScreenIntent = new Intent(MainActivity.this,SecondScreen.class);
                String escolha;
                escolha = String.valueOf(parent.getItemAtPosition(position));

                getNameScreenIntent.putExtra("Escolha",escolha ); // joga os dados para a proxima activity
                startActivity(getNameScreenIntent);
            }
        });
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
