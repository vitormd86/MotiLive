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

import com.example.henrique.list.Adapters.MyAdapterFreeTime;
import com.example.henrique.list.Adapters.MyAdapterServiceTypes;

import java.util.ArrayList;

public class HourConsult extends ActionBarActivity {

    ArrayList<String> freeHours = new ArrayList<>();
    ArrayList<String> freeMinutes = new ArrayList<>();
    ResizeAnimation resizeAnimation;
    boolean isHoursOpened;
    boolean isMinutesOpened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_consult);

        //Deve buscar do banco ou da intent
        String professionalName = "Leandro Massaro Kubota";
        String occupation = "Massagista";
        String [] services = {"Serviço 1" , "Serviço2" , "Serviço3" , "Serviço4" , "Serviço5" , "Serviço6","Servico7", "Servico8", "servico8"};


        //adicionando horas.... deve receber do banco de dados e tratar em seguida
        freeHours.add("1");
        freeHours.add("2");
        freeHours.add("3");
        freeMinutes.add("10");
        freeMinutes.add("20");
        freeMinutes.add("30");

        //apontando items do layout
        TextView textProfessionalName = (TextView) findViewById(R.id.professionalName);
        TextView textProfession = (TextView) findViewById(R.id.profession);
        final ListView listHours = (ListView) findViewById(R.id.listHours);
        final ListView listMinutes = (ListView) findViewById(R.id.listMinutes);
        final ListView listServices = (ListView) findViewById(R.id.listServices);
        final ArrayAdapter myAdapterServiceTypes = new MyAdapterServiceTypes(this, services);
        final ArrayAdapter myAdapterFreeHours = new MyAdapterFreeTime(getBaseContext(), freeHours, listHours);
        final ArrayAdapter myAdapterFreeMinutes = new MyAdapterFreeTime(getBaseContext(), freeMinutes, listHours);

        //Configura as variaveis do cabecalho
        textProfessionalName.setText(professionalName);
        textProfession.setText(occupation);

        //Configurando lista de horas livre
        listHours.setAdapter(myAdapterFreeHours);
        listMinutes.setAdapter(myAdapterFreeMinutes);

        //Configuraado lista de servicos
        listServices.setAdapter(myAdapterServiceTypes);
        listServices.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //criando listeners dos servicos
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //caso clique no servico o horario mudara de acordo com o horario dispnivel pra aquele servico
                freeHours.clear();
                freeHours.add("4");
                freeHours.add("5");
                myAdapterFreeHours.notifyDataSetChanged();

                //verifica se a listview de horas ja esta aberta
                if(!isHoursOpened) {
                    //redimensiona listView de horas
                    isHoursOpened = true;
                    resizeAnimation = new ResizeAnimation(listHours, 180);
                    resizeAnimation.setDuration(600);
                    listHours.startAnimation(resizeAnimation);
                }
            }
        });

        //criando listeners do hourlist
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //caso clique na hora a lista de minutos mudara de acordo com o horario dispnivel pra aquele servico
                freeMinutes.clear();
                freeMinutes.add("00");
                freeMinutes.add("05");
                freeMinutes.add("15");
                freeMinutes.add("40");
                freeMinutes.add("50");
                myAdapterFreeMinutes.notifyDataSetChanged();

                if(!isMinutesOpened) {
                    //redimensiona listView de horas]
                    isMinutesOpened = true;
                    resizeAnimation = new ResizeAnimation(listMinutes, 180);
                    resizeAnimation.setDuration(600);
                    listMinutes.startAnimation(resizeAnimation);
                }
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
