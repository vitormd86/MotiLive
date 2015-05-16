package com.example.henrique.list.Cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.ProfessionalAdapter;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.motiserver.dto.CustomerDTO;

/**
 * Created by Henrique on 12/02/2015.
 * Edited by Vitor on 23/02/2015.
 */

/*Tela de selecao de data e profissional para o agendamento*/
public class CustScheduleDateFragmentPortrait_6 extends Fragment {

    View v;
    ListView professionalLV;
    String[] favoriteProfessionals;


    CalendarPickerView screenCalendar;
    Calendar initDate, endDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_cust_schedule_date_6_p, parent, false);

        favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};

        initViews();
        initCalendar();


        //TODO: colocar o objeto q vem la de traz do login

        //supondo q personDTO já exista por causa do login.
        long teste = 50;


        CustomerDTO customerDTO = new CustomerDTO();
        //customerDTO.setCustomerId(teste);

        setProfessionalListener();

        return v;
    }

    private void initViews(){
        professionalLV = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado
        screenCalendar = (CalendarPickerView) v.findViewById(R.id.calendarView); // inicializa a Calendar View do fragment inflado
        ListAdapter professionalAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        professionalAdapter = new ProfessionalAdapter(v.getContext(), favoriteProfessionals);

        professionalLV.setAdapter(professionalAdapter);// seleciona o adaptador... no caso  "professionalAdapter" q eh do tipo myAdapter

    }


    private void initCalendar(){
    //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        initDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 3);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(initDate.getTime());

    }

    private void setProfessionalListener(){
        professionalLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recebe dados selecionada para passar para a proxima tela
                String selectedDate = getCalendarDate(screenCalendar);
                String selectedProfessional = String.valueOf(parent.getItemAtPosition(position));

                //inicia chamada de agendamento de horario
                Intent toHourIntent = new Intent(getActivity(), CustScheduleHourActivity_7.class);
                toHourIntent.putExtra("selectedProfessional", selectedProfessional);
                toHourIntent.putExtra("selectedDate", selectedDate);
                startActivity(toHourIntent);
            }
        });

    }
    //este metodo retorna a data selecionada no calendario formatado em String
    private String getCalendarDate(CalendarPickerView calendar){
        String sDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sDate = sdf.format(new Date(calendar.getSelectedDate().getTime()));
        return sDate;
    }
    public void recoverListProfessionals(View view)
    {
        //RequestParams params = new RequestParams();
       // params.put("id_customer", customerDTO);
    }

}
