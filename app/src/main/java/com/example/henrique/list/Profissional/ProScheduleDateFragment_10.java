package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.ClientAdapter;
import com.example.henrique.list.Adapters.ProfessionalAdapter;
import com.example.henrique.list.Cliente.CustScheduleHourFragment_7;
import com.example.henrique.list.R;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.motiserver.dto.CustomerDTO;

/**
 * Created by htamashiro on 3/21/15.
 */
public class ProScheduleDateFragment_10 extends Fragment {

    String[] favoriteClients;
    ListView listClients;
    CalendarView myCalendarView;
    Button newClientButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pro_schedule_date_10, parent, false);

        favoriteClients = new String[]{"Leandro Massaru Kubota (Cliente)", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};

        listClients = (ListView) v.findViewById(R.id.ListViewPro_10); // inicializa a List View do fragment inflado
        myCalendarView = (CalendarView) v.findViewById(R.id.calendarViewPro_10); // inicializa a Calendar View do fragment inflado
        newClientButton = (Button) v.findViewById(R.id.newClientButton);

        ListAdapter clientAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        clientAdapter = new ClientAdapter(v.getContext(), favoriteClients);

        listClients.setAdapter(clientAdapter);// seleciona o adaptador... no caso  "clientAdapter"

        //TODO: colocar o objeto q vem la de traz do login

        //supondo q personDTO já exista por causa do login.
        long teste = 50;
        CustomerDTO customerDTO = new CustomerDTO();
        //customerDTO.setCustomerId(teste);

        //configurando listeners
        setNewClientListener();
        setClientsListener();

        return v;
    }

    //este metodo retorna a data selecionada no calendario formatada em String
    private String getCalendarDate(CalendarView cv){
        String sDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sDate = sdf.format(new Date(cv.getDate()));
        return sDate;
    }

    public void setNewClientListener(){
        //esse metodo gera o listener do botao Novo Cliente
        newClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newClientIntent = new Intent(getActivity(),ProScheduleFirstActivity_11.class);
                newClientIntent.putExtra("nextScreen", 12);
                startActivity(newClientIntent);
            }
        });
    }
    public void setClientsListener(){
        //este metodo gera os listener da lista de cliente
        listClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recebe a data selecionada para passar para a proxima tela
                String selectedDate = getCalendarDate(myCalendarView);

                //opcoesSelecionadas opcoes = new opcoesSelecionadas(String.valueOf(parent.getItemAtPosition(position)), null, null);
                String selectedClient = String.valueOf(parent.getItemAtPosition(position));

                //instancia proximo fragment a ser iniciado
                ProScheduleHoursFragment_12 proScheduleHoursFragment12 = new ProScheduleHoursFragment_12();

                //inicia valores que serao enviados para a proxima Fragment
                Bundle args = new Bundle();
                args.putString("selectedClient", selectedClient);
                args.putString("selectedDate", selectedDate);
                proScheduleHoursFragment12.setArguments(args);

                //inicia a transacao de Fragments
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, proScheduleHoursFragment12);

                //este metodo permite q o usuario navegue de volta
                ft.addToBackStack(null);

                ft.commit();
            }
        });
    }

    public void recoverListProfessionals(View view)
    {
        RequestParams params = new RequestParams();
        // params.put("id_customer", customerDTO);
    }

}


