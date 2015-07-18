package com.example.henrique.list.Profissional;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.henrique.list.Adapters.ScheduleAdapter;
import com.example.henrique.list.Beans.ScheduleItem;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.BreakService;
import com.example.henrique.list.Service.SchedulingService;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import br.com.motiserver.dto.BreakDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.builder.PeriodDTOBuilder;


public class ProScheduleListFragment_14 extends Fragment {

    private View v;

    PinnedSectionListView listSchedules;
    ImageButton addScheduleBT;

    List<SchedulingDTO> schedulingList;
    List<BreakDTO> breakList;
    List<PeriodDTO> busyTimePeriodDTOList;
    ProfessionalDTO professionalDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.fragment_pro_schedule_list_14, container, false);

        retrieveAttributes();
        initScheduleList();
        setListSchedulesListener();
        setNewScheduleButtonListener();

        return v;
    }

    private void retrieveAttributes(){
        Bundle extras = this.getArguments();
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
        if (professionalDTO == null){
            professionalDTO = new ProfessionalDTO();
            System.out.println("Erro ao receber professionalDTO da intent");
            Toast.makeText(getActivity(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        SchedulingService schedulingService = new SchedulingService();
        try{
            schedulingList = schedulingService.findUpcomingSchedulingByProfessionalId(professionalDTO.getId(), Calendar.getInstance(TimeZone.getDefault()));
        } catch (Exception e){
            e.printStackTrace();
            schedulingList = new ArrayList<>();
            System.out.println("Erro ao recuperar agendamentos do profissional.");
            Toast.makeText(getActivity(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        BreakService breakService = new BreakService();
        try{
            breakList = breakService.findUpcomingPersonalInterval(professionalDTO.getId(), Calendar.getInstance(TimeZone.getDefault()));
        } catch (Exception e){
            e.printStackTrace();
            breakList = new ArrayList<>();
            System.out.println("Erro ao recuperar breaks do profissional.");
            Toast.makeText(getActivity(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initScheduleList(){
        ArrayList<ScheduleItem> scheduleItems = initScheduleItems();

        listSchedules = (PinnedSectionListView) v.findViewById(R.id.pinnedListSchedules);
        addScheduleBT = (ImageButton) v.findViewById(R.id.addSchedule);
        ArrayAdapter schedulesAdapter = new ScheduleAdapter(getActivity(), scheduleItems);

        listSchedules.initShadow(false);
        listSchedules.setAdapter(schedulesAdapter);
    }

    public ArrayList<ScheduleItem> initScheduleItems(){
        //inicia instancias de agendamento, break e itens a serem apresentados na lista
        ArrayList<ScheduleItem> items = new ArrayList<>();

        //recebe data atual e configura no calendario
        Date pinnedMenuDate = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(pinnedMenuDate);


        //recupera schedulings e breaks para gravar em array de items
        Set<SchedulingDTO> schedulingDTOSet = new HashSet<>(schedulingList);
        Set<BreakDTO> breakDTOSet = new HashSet<>(breakList);
        busyTimePeriodDTOList = PeriodDTOBuilder.buildBusyTimesList(schedulingDTOSet, breakDTOSet);

        for (int i = 0; i < busyTimePeriodDTOList.size(); i++){
            ScheduleItem item = new ScheduleItem();

            //inicializa valores da view a partir dos agendamentos buscado no BD
            if(busyTimePeriodDTOList.get(i) instanceof SchedulingDTO) {
                SchedulingDTO schedulingDTO = (SchedulingDTO) busyTimePeriodDTOList.get(i);
                item.setPersonName(schedulingDTO.getCustomer().getName());
                item.setScheduleDate(schedulingDTO.getDailySchedule().getDate().getTime());
                item.setScheduleInicialTime(schedulingDTO.getStartTime().getTime());
                item.setScheduleFinalTime(schedulingDTO.getEndTime().getTime());
            }
            if(busyTimePeriodDTOList.get(i) instanceof BreakDTO){
                BreakDTO breakDTO = (BreakDTO) busyTimePeriodDTOList.get(i);
                item.setPersonName("INTERVALO");
                item.setScheduleDate(breakDTO.getDailySchedule().getDate().getTime());
                item.setScheduleInicialTime(breakDTO.getStartTime().getTime());
                item.setScheduleFinalTime(breakDTO.getEndTime().getTime());
                //item.se
            }
            item.setListPosition(i);
            cal2.setTime(item.getScheduleDate());

            //manipulacao de secion (tipo de item da lista)
            System.out.println("ScheduleDate de item:  " + item.getScheduleDate().getDate() + "/" + item.getScheduleDate().getMonth());
            System.out.println("Cal1 " + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH));
            System.out.println("Cal2 " + cal2.get(Calendar.DAY_OF_MONTH) + "/" + cal2.get(Calendar.MONTH));
            item.setSection(false);

            //Verifica se o item é o primeiro da lista, ou se há diferenca de DIA para criar uma SECTION
            if (i == 0 || cal.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)
                    || cal.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)
                    || cal.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)){
                ScheduleItem sectionItem = new ScheduleItem();
                sectionItem.setSection(true);
                sectionItem.setScheduleDate(item.getScheduleDate());
                items.add(sectionItem);
                cal.setTime(cal2.getTime());

                System.out.println("Entrou no if");
            }
            items.add(item);
        }

        return items;
    }

    //listener da lista para consultar agendamento
    public void setListSchedulesListener(){
        listSchedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleItem selectedItem = (ScheduleItem) listSchedules.getItemAtPosition(position);
                SchedulingDTO selectedSchedule = schedulingList.get(selectedItem.getListPosition());

                //verifica se usuario nao clicou em um titulo
                if(!selectedItem.isSection()) {
                    Intent intent = new Intent(getActivity(), ProScheduleConfirmActivity_13.class);
                    intent.putExtra(SessionAttributes.SCHEDULING, selectedSchedule);
                    startActivity(intent);
                }
            }
        });
    }

    private void setNewScheduleButtonListener(){
        addScheduleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putSerializable(SessionAttributes.PROFESSIONAL, professionalDTO);

                ProScheduleDateFragment_10 proScheduleDateFragment_10 = new ProScheduleDateFragment_10();
                proScheduleDateFragment_10.setArguments(extras);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, proScheduleDateFragment_10);

                //este metodo permite q o usuario navegue de volta
                ft.addToBackStack(null);

                ft.commit();
            }
        });
    }
}
