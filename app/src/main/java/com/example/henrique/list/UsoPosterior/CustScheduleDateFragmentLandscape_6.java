package com.example.henrique.list.UsoPosterior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.ProfessionalAdapter;
import com.example.henrique.list.Cliente.CustScheduleHourActivity_7;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;

/*Tela de selecao de data e profissional para o agendamento*/
public class CustScheduleDateFragmentLandscape_6 extends Fragment  {

    private CustomerDTO customerDTO;
    private List<ProfessionalDTO> favoriteProfessionals;
    private ProfessionalService professionalService;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cust_schedule_date_l, parent, false);

        ListView theListView = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado
        final CalendarView myCalendarView = (CalendarView) v.findViewById(R.id.calendarView); // inicializa a Calendar View do fragment inflado

        customerDTO = (CustomerDTO) getActivity().getIntent().getSerializableExtra(SessionAttributes.CUSTOMER);
        professionalService = new ProfessionalService();
        try {
            favoriteProfessionals = professionalService.findProfessionalContactsByCustomerId(customerDTO.getId());
        } catch(ServiceException ex) {
            Toast.makeText(parent.getContext(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        ListAdapter theAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        theAdapter = new ProfessionalAdapter(v.getContext(), favoriteProfessionals);

        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "theAdapter" q eh do tipo myAdapter

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //recebe dados selecionada para passar para a proxima tela
                String selectedDate = getCalendarDate(myCalendarView);
                String selectedProfessional = String.valueOf(parent.getItemAtPosition(position));

                //inicia chamada de agendamento de horario
                Intent toHourIntent = new Intent(getActivity(), CustScheduleHourActivity_7.class);
                toHourIntent.putExtra("selectedProfessional", selectedProfessional);
                toHourIntent.putExtra("selectedDate", selectedDate);

                //Intent intentHourConsult;
                //intentHourConsult = new Intent(getActivity() , HourConsult.class);
                //intentHourConsult.putExtra("Escolhas", opcoes); // joga o objeto para a proxima activity
                //intentHourConsult.putExtra("Date", selectedDate); // joga o objeto para a proxima activity
                //startActivity(intentHourConsult);
            }
        });

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
