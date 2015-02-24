package com.example.henrique.list.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.myAdapter;
import com.example.henrique.list.HourConsult;
import com.example.henrique.list.R;
import com.example.henrique.list.SecondScreen;
import com.example.henrique.list.opcoesSelecionadas;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentLandscape extends Fragment  {

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.landscape_fragment, parent, false);

        ListView theListView = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado
        final CalendarView myCalendarView = (CalendarView) v.findViewById(R.id.calendarView); // inicializa a Calendar View do fragment inflado


        String[] favoriteProfessionals;// aqui eu inicializo o array de opcoes
        favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro"};

        ListAdapter theAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        theAdapter = new myAdapter(v.getContext(), favoriteProfessionals);

        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "theAdapter" q eh do tipo myAdapter

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recebe a data selecionada para passar para a proxima tela
                String selectedDate= getCalendarDate(myCalendarView);

                // HIDE, mudanca provisoria...
                //opcoesSelecionadas opcoes = new opcoesSelecionadas(String.valueOf(parent.getItemAtPosition(position)), null, null);
                String opcoes = String.valueOf(parent.getItemAtPosition(position));

                Intent intentHourConsult;

                intentHourConsult = new Intent(getActivity() , HourConsult.class);
                intentHourConsult.putExtra("Escolhas", opcoes); // joga o objeto para a proxima activity
                intentHourConsult.putExtra("Date", selectedDate); // joga o objeto para a proxima activity

                startActivity(intentHourConsult);
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
    //este metodo retorna a data selecionada no calendario
    private String getCalendarDate(CalendarView cv){
        String sDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sDate = sdf.format(new Date(cv.getDate()));
        return sDate;
    }
}
