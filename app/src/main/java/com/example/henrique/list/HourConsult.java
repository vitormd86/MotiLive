package com.example.henrique.list;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HourConsult extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_consult);

        //Deve buscar do banco ou da intent
        String professionalName = "Leandro Massaro Kubota";
        String occupation = "Massagista";
        String [] freeHours = {"1" , "2" , "3"};
        String [] services = {"Serviço 1" , "Serviço2" , "Serviço3" , "Serviço4" , "Serviço5" , "Serviço6","Servico7", "Servico8", "servico8"};

        //apontando items do layout
        TextView textProfessionalName = (TextView) findViewById(R.id.professionalName);
        TextView textProfession = (TextView) findViewById(R.id.profession);
        final ListView listHours = (ListView) findViewById(R.id.listHours);
        final ListView listServices = (ListView) findViewById(R.id.listServices);

        
        ListAdapter myAdapterFreeHours = new MyAdapterFreeHours(this, freeHours, listHours);
        final ListAdapter myAdapterServiceTypes = new MyAdapterServiceTypes(this, services);

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
                //muda a cor do item de acordo com o clique

                int count = 5;
                View v;
                for (int i = 0; i < count; i++){

                    v = listServices.getChildAt(i);
                    if (v == view) {
                        return;
                    }

                    v.setBackgroundColor(Color.WHITE);
                }
                view.setBackgroundColor(getResources().getColor(R.color.myBlue));


                //              CheckedTextView textView = (CheckedTextView) view;


                ////              for (int i = 0; i < listView.getCount(); i++) {
                //                  textView= (CheckedTextView) listView.getChildAt(i);
                //                   if (textView != null) {
                // /                      textView.setTextColor(Color.WHITE);
                //                   }
//
                //               }
                ///               listView.invalidate();
                //               textView = (CheckedTextView) view;
                //               if (textView != null) {
                //                   textView.setTextColor(Color.BLUE);
                //               }
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
