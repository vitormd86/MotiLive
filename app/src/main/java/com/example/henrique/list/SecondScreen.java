package com.example.henrique.list;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SecondScreen extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        String[] Servicos = {"Massagem", "Acumputura", "Shiatsu"};// aqui eu inicializa o array de opcoes
        ListAdapter theAdapter = new myAdapterServicos(this, Servicos); //inicializa o adaptador de array, pra encaixar o array na lsita
        ListView theListView; // inicializa a view
        theListView = (ListView) findViewById(R.id.Lista_Servico);
        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "my adapter"

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getNameScreenIntent = new Intent(SecondScreen.this, thirdScreen.class);


                startActivity(getNameScreenIntent);
            }
        });
    }
}


