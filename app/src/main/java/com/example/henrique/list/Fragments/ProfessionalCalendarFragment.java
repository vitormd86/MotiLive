package com.example.henrique.list.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.myAdapterClients;
import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 03/03/2015.
 */
public class ProfessionalCalendarFragment extends Fragment {
    private View v;
    private FragmentActivity fa;
    ArrayList<String> clientesAgendados = new ArrayList<>();
    CalendarView myCalendarView;
    ListView myListView1;
    ArrayAdapter clientsAdapter;

    long date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_professional_calendar, container, false);
        myCalendarView = (CalendarView) v.findViewById(R.id.calendarView);
        ListView myListView1 = (ListView) v.findViewById(R.id.listView);


        //Configurando lista dos clientes
        clientesAgendados.clear();
        clientesAgendados.add("Cliente 1 ID");// buscar do banco os clientes do dia atual
        clientesAgendados.add("Cliente 2 ID");
        clientesAgendados.add("Cliente 3 ID");
        clientsAdapter = new myAdapterClients(getActivity(), clientesAgendados, this);
        myListView1.setAdapter(clientsAdapter);

        date = myCalendarView.getDate();
        setCalendarListener();

        return v;
    }

    //Configura botoes (listener) do calendario que altera lista
    public void setCalendarListener(){

        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //corrige bug da rolagem
                if (myCalendarView.getDate() != date ){
                    date = myCalendarView.getDate();
                    clientesAgendados.clear();
                    clientesAgendados.add("Cliente 4 ID");// buscar do banco os clientes do dia SELECIONADO
                    clientesAgendados.add("Cliente 5 ID");
                    clientesAgendados.add("Cliente 6 ID");
                    clientesAgendados.add("Cliente 7 ID");
                    clientesAgendados.add("Cliente 8 ID");
                    clientesAgendados.add("Cliente 9 ID");
                    clientesAgendados.add("Cliente 10 ID");
                    clientsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
