package com.example.henrique.list;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterFreeHours;
import com.example.henrique.list.Adapters.MyAdapterServiceTypes;

import java.util.ArrayList;

public class HourConsult extends ActionBarActivity {


    ArrayList<String> freeHours = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_consult);

        //Deve buscar do banco ou da intent
        String professionalName = "Leandro Massaro Kubota";
        String occupation = "Massagista";
        String [] services = {"Serviço 1" , "Serviço2" , "Serviço3" , "Serviço4" , "Serviço5" , "Serviço6","Servico7", "Servico8", "servico8"};


        freeHours.add("1");
        freeHours.add("2");
        freeHours.add("3");

        //apontando items do layout
        TextView textProfessionalName = (TextView) findViewById(R.id.professionalName);
        TextView textProfession = (TextView) findViewById(R.id.profession);
        final ListView listHours = (ListView) findViewById(R.id.listHours);
        final ListView listServices = (ListView) findViewById(R.id.listServices);
        final ArrayAdapter myAdapterServiceTypes = new MyAdapterServiceTypes(this, services);
        final ArrayAdapter myAdapterFreeHours = new MyAdapterFreeHours(getBaseContext(), freeHours, listHours);

        //Configura as variaveis do cabecalho
        textProfessionalName.setText(professionalName);
        textProfession.setText(occupation);

        //Configurando lista de horas livre
        listHours.setAdapter(myAdapterFreeHours);

        //Configuraado lista de servicos
        listServices.setAdapter(myAdapterServiceTypes);


        //criando listeners dos servicos
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //caso clique no servico o horario mudara de acordo com o horario dispnivel pra aquele servico
                freeHours.add("4");
                freeHours.add("5");
                myAdapterFreeHours.notifyDataSetChanged();
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
