package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.ClientAdapter;
import com.example.henrique.list.Cliente.CustScheduleHourActivity_7;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.CustomerService;
import com.example.henrique.list.Service.DailyScheduleService;
import com.example.henrique.list.Service.ServiceService;
import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;
import com.example.henrique.list.Utilidade_Publica.SchedulingCalculator.FreeTimeCalculator;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;
import br.com.motiserver.dto.builder.PeriodDTOBuilder;
import br.com.motiserver.util.constants.Status;


public class ProScheduleDateFragment_10 extends Fragment {

    private ProfessionalDTO professionalDTO;
    private List<CustomerDTO> customerDTOList;
    private DailyScheduleDTO dailyScheduleDTO;

    ListView listClients;
    Button newClientButton, addBreakTimeButton;

    private CalendarPickerView screenCalendar;
    private View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pro_schedule_date_10, parent, false);

        retrieveAttributes();

        initViews();
        initCalendar();

        //configurando listeners
        setNewClientListener();
        setAddBreakTimeListener();
        setClientsListener();

        return v;
    }

    private void  retrieveAttributes(){
        Bundle extras = this.getArguments();
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);

        if(professionalDTO != null){
            CustomerService customerService = new CustomerService();
            try{
                customerDTOList = customerService.findCustomerContactsByProfessionalId(professionalDTO.getId());
            } catch (ServiceException ex){
                ex.printStackTrace();
                customerDTOList = new ArrayList<>();
                System.out.println("Falha ao buscar os contatos.");
            }
        }
    }

    private void initViews(){
        listClients = (ListView) v.findViewById(R.id.ListViewPro_10); // inicializa a List View do fragment inflado
        newClientButton = (Button) v.findViewById(R.id.newClientButton);
        addBreakTimeButton = (Button) v.findViewById(R.id.addBreakTimeButton);
        screenCalendar = (CalendarPickerView) v.findViewById(R.id.calendar_view);

        ListAdapter clientAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        clientAdapter = new ClientAdapter(v.getContext(), customerDTOList);
        listClients.setAdapter(clientAdapter);// seleciona o adaptador... no caso  "clientAdapter"
    }

    private void initCalendar(){
        //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        Calendar initDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 3);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(initDate.getTime());

    }

    private void setNewClientListener(){
        //esse metodo gera o listener do botao Novo Cliente
        newClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newClientIntent = new Intent(getActivity(),ProScheduleFirstActivity_11.class);

                startActivity(newClientIntent);

            }
        });
    }

    private void setAddBreakTimeListener(){
        //esse metodo gera o listener do botao Novo Cliente
        addBreakTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUtilDate(screenCalendar.getSelectedDate(), professionalDTO)){
                    List<PeriodDTO> periodDTOList = PeriodDTOBuilder.buildFreeTimeList(dailyScheduleDTO);

                    Intent breakTimeIntent = new Intent(getActivity(), ProBreakTimeConfig_17.class);
                    breakTimeIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                    breakTimeIntent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
                    breakTimeIntent.putExtra(SessionAttributes.PERIOD_LIST, (ArrayList) periodDTOList);
                    startActivity(breakTimeIntent);
                } else {
                    Toast.makeText(getActivity(), "Você não está livre este dia", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setClientsListener(){
        //este metodo gera os listener da lista de cliente
        listClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recupera profissional q esta usando o aplicatico e customer q ele quer agendar
                CustomerDTO selectedCustomerDTO = (CustomerDTO) parent.getItemAtPosition(position);

                //verifica se a data do profissional esta livre
                if(isUtilDate(screenCalendar.getSelectedDate(), professionalDTO)){
                    //recupera lista de periodo e de servicos do profissional, para verificar se tem hora livre
                    List<PeriodDTO> periodDTOList = PeriodDTOBuilder.buildFreeTimeList(dailyScheduleDTO);
                    List<ServiceDTO> serviceDTOList = new ArrayList<>();
                    ServiceService serviceService = new ServiceService();
                    try {
                        serviceDTOList = serviceService.findAllByProfessionalId(professionalDTO.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Erro ao buscar servicos do profissional selecionado");
                    }
                    System.out.println("Size da array de Servicos antes da intent " + serviceDTOList.size());
                    if (FreeTimeCalculator.isFreeHourDay(periodDTOList, dailyScheduleDTO, serviceDTOList)) {
                        //inicia chamada de agendamento de horario

                        System.out.println("Size da array de Servicos depois do if " + serviceDTOList.size());
                        Intent toHourIntent = new Intent(getActivity(), ProScheduleHoursActivity_12.class);
                        toHourIntent.putExtra(SessionAttributes.CUSTOMER, selectedCustomerDTO);
                        toHourIntent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                        toHourIntent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
                        toHourIntent.putExtra(SessionAttributes.PERIOD_LIST, (ArrayList) periodDTOList);
                        toHourIntent.putExtra(SessionAttributes.SERVICE, (ArrayList) serviceDTOList);
                        System.out.println("Array Servicos saindo com size " + serviceDTOList.size());
                        startActivity(toHourIntent);
                    } else {
                        Toast.makeText(getActivity(), "Você não está livre este dia", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Você não está livre este dia", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isUtilDate(Date date, ProfessionalDTO userProfessionalDTO) {
        //recupera dailySchedule para verificar se o o dia é util para o profissional
        DailyScheduleService dailyScheduleService = new DailyScheduleService();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        try {
            dailyScheduleDTO = dailyScheduleService.findByProfessionalIdAndDate(userProfessionalDTO.getId(), cal);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao recuperar dailySchedule");
        }
        if (dailyScheduleDTO == null || dailyScheduleDTO.getWorkDay() == Status.FALSE) {
            return false;
        } else {
            return true;
        }
    }

}


