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
import android.widget.Toast;

import com.example.henrique.list.Adapters.ProfessionalAdapter;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.DailyScheduleService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;
import com.example.henrique.list.Utilidade_Publica.DateUtil;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.Status;

/**
 * Created by Henrique on 12/02/2015.
 * Edited by Vitor on 23/02/2015.
 */

/*Tela de selecao de data e profissional para o agendamento*/
public class CustScheduleDateFragmentPortrait_6 extends Fragment {

    private CustomerDTO customerDTO;
    private List<ProfessionalDTO> favoriteProfessionals;
    private DailyScheduleDTO dailyScheduleDTO;

    private ListView professionalLV;

    private CalendarPickerView screenCalendar;
    private View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_cust_schedule_date_6_p, parent, false);

        retriveAttributes();

        initViews();
        initCalendar();

        setProfessionalListener();
        return v;
    }

    public void retriveAttributes() {
        Bundle extras = this.getArguments();
        customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);

        if (customerDTO != null) {
            ProfessionalService professionalService = new ProfessionalService();
            try {
                favoriteProfessionals = professionalService.findProfessionalContactsByCustomerId(customerDTO.getId());
            } catch (ServiceException ex) {
                System.out.println("Falha ao buscar os contatos.");
            }
        }

    }

    private void initViews() {
        professionalLV = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado
        screenCalendar = (CalendarPickerView) v.findViewById(R.id.calendarView); // inicializa a Calendar View do fragment inflado
        ListAdapter professionalAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        professionalAdapter = new ProfessionalAdapter(v.getContext(), favoriteProfessionals);

        professionalLV.setAdapter(professionalAdapter);// seleciona o adaptador... no caso  "professionalAdapter" q eh do tipo myAdapter
    }


    private void initCalendar() {
        //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        Calendar initDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 3);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(initDate.getTime());

    }

    private void setProfessionalListener() {
        professionalLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recupera profissional selecionado
                ProfessionalDTO selectedProfessionalDTO = (ProfessionalDTO) parent.getItemAtPosition(position);
                //recupera data selecionada
                if (isUtilDate(screenCalendar.getSelectedDate(), selectedProfessionalDTO)) {
                    String selectedDate = getCalendarStringDate(screenCalendar);
                    //inicia chamada de agendamento de horario
                    Intent toHourIntent = new Intent(getActivity(), CustScheduleHourActivity_7.class);

                    toHourIntent.putExtra(SessionAttributes.CUSTOMER, customerDTO);
                    toHourIntent.putExtra(SessionAttributes.PROFESSIONAL, selectedProfessionalDTO);
                    toHourIntent.putExtra("selectedDate", selectedDate);
                    toHourIntent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
                    startActivity(toHourIntent);
                } else {
                    Toast.makeText(getActivity(), "O profissional selecionado não está livre este dia", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //este metodo retorna a data selecionada no calendario formatado em String
    private String getCalendarStringDate(CalendarPickerView calendar) {
        String sDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sDate = sdf.format(new Date(calendar.getSelectedDate().getTime()));
        return sDate;
    }

    private boolean isUtilDate(Date date, ProfessionalDTO selectedProfessionalDTO) {
        //recupera dailySchedule para verificar se o o dia é util para o profissional
        DailyScheduleService dailyScheduleService = new DailyScheduleService();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        try {
            dailyScheduleDTO = dailyScheduleService.findByProfessionalIdAndDate(selectedProfessionalDTO.getId(), cal);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao recuperar dailySchedule");
        }
        if (dailyScheduleDTO == null) {
            return false;
        } else if (dailyScheduleDTO.getWorkDay() == Status.FALSE) {
            return false;
        } else {
            return true;
        }
    }
}
