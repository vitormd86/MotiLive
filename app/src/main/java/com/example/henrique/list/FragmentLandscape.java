package com.example.henrique.list;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class FragmentLandscape extends Fragment  {

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.landscape_fragment, parent, false);

        ListView theListView = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado

        String[] favoriteProfessionals;// aqui eu inicializo o array de opcoes
        favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro"};

        ListAdapter theAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        theAdapter = new myAdapter(v.getContext(), favoriteProfessionals);

        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "theAdapter" q eh do tipo myAdapter
       //teste de conexao




        //teste conexao



        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                opcoesSelecionadas opcoes = new opcoesSelecionadas(String.valueOf(parent.getItemAtPosition(position)), null, null);
                Intent opcoesEscolhidas;
                opcoesEscolhidas = new Intent(getActivity() , SecondScreen.class);

                opcoesEscolhidas.putExtra("Escolhas", opcoes); // joga o objeto para a proxima activity
                startActivity(opcoesEscolhidas);
            }
        });
     /* CalendarView myCalendarView = (CalendarView) v.findViewById(R.id.calendarView);
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
         /  public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {


            //    Envia dados para proxima Activity (ProfessionalHours)
                nextCalendarActivity.putExtra("dayOfMonth", Integer.toString(dayOfMonth));
                nextCalendarActivity.putExtra("month", Integer.toString(month));
               // nextCalendarActivity.putExtra("year", Integer.toString(year));
          //  }
        //});
*/
        return v;
            }
}
