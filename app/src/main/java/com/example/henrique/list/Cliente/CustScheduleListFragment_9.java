package com.example.henrique.list.Cliente;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.example.henrique.list.Service.SchedulingService;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.SchedulingDTO;
import br.com.motiserver.dto.ServiceDTO;
import br.com.motiserver.dto.ServiceSchedulingDTO;

public class CustScheduleListFragment_9 extends Fragment {

    private View v;

    PinnedSectionListView listSchedules;
    ImageButton addScheduleBT;

    List<SchedulingDTO> schedules;
    CustomerDTO customerDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.fragment_cust_schedule_list_9, container, false);


        retrieveAttributes();
        initScheduleList();
        setListSchedulesListener();
        setNewScheduleButtonListener();

        return v;
    }

    public void retrieveAttributes(){
        Bundle extras = this.getArguments();
        customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);
        if (customerDTO == null) {
            customerDTO = new CustomerDTO();
           System.out.println("Erro ao receber customerDTO da intent");
           Toast.makeText(getActivity(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        SchedulingService scheduleService = new SchedulingService();
        try{
            schedules = scheduleService.findUpcomingSchedulingByCustomerId(customerDTO.getId());
        } catch (Exception e){
            e.printStackTrace();
            schedules = new ArrayList<>();
            System.out.println("Erro ao recuperar agendamentos do usuario.");
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
        //inicia instancias de agendamento e itens a serem apresentados na lista
        ArrayList<ScheduleItem> items = new ArrayList<>();

        //recebe data atual e configura no calendario
        Date pinnedMenuDate = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(pinnedMenuDate);

        //inicia proximos itens de agendamento, e verifica se a data é a mesma. caso contrario gera outo item SECTION
        for (int i = 0; i < schedules.size(); i++){
            ScheduleItem item = new ScheduleItem();

            //inicializa valores da view a partir dos agendamentos buscado no BD
            item.setListPosition(i);
            item.setPersonName(schedules.get(i).getDailySchedule().getProfessional().getName());
            item.setScheduleDate(schedules.get(i).getDailySchedule().getDate().getTime());
            item.setScheduleInicialTime(schedules.get(i).getStartTime().getTime());
            item.setScheduleFinalTime(schedules.get(i).getEndTime().getTime());
            cal2.setTime(item.getScheduleDate());

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
                SchedulingDTO selectedSchedule = schedules.get(selectedItem.getListPosition());

                //verifica se usuario nao clicou em um titulo
                if(!selectedItem.isSection()) {
                    Intent intent = new Intent(getActivity(), CustScheduleConfirmActivity_8.class);
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
                extras.putSerializable(SessionAttributes.CUSTOMER, customerDTO);

                CustScheduleDateFragmentPortrait_6 custScheduleDateFragmentPortrait_6 = new CustScheduleDateFragmentPortrait_6();
                custScheduleDateFragmentPortrait_6.setArguments(extras);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, custScheduleDateFragmentPortrait_6);

                //este metodo permite q o usuario navegue de volta
                ft.addToBackStack(null);

                ft.commit();
            }
        });
    }
}
