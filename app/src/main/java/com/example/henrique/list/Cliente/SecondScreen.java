package com.example.henrique.list.Cliente;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.myAdapterServicos;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.opcoesSelecionadas;

public class SecondScreen extends Activity {
    @Override
// inicia a atividadde da segunda tela
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent activityThatCalled = getIntent();

        	        // String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        final opcoesSelecionadas opcoes = (opcoesSelecionadas) activityThatCalled.getSerializableExtra("Escolhas"); // pega o objeto q foi serializado na activity anterior

        String[] Servicos = {"Massagem", "Acumputura", "Shiatsu"};// aqui eu inicializa o array de opcoes
        ListAdapter theAdapter = new myAdapterServicos(this, Servicos); //inicializa o adaptador de array, pra encaixar o array na lsita
        ListView theListView; // inicializa a view
        theListView = (ListView) findViewById(R.id.Lista_Servico);
        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "my adapter"

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getNameScreenIntent = new Intent(SecondScreen.this, thirdScreen.class);
                opcoes.setServicoEscolhido(String.valueOf(parent.getItemAtPosition(position)));
        // prepara os dados pra proxima activity
                getNameScreenIntent.putExtra("Escolhas",opcoes );

                startActivity(getNameScreenIntent);
            }
        });
    }
}


