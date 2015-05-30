package com.example.henrique.list.Cliente;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.motiserver.dto.SchedulingDTO;

public class CustScheduleListFragment_9 extends Fragment {

    private View v;
    private FragmentActivity fa;

    PinnedSectionListView listSchedules;
    ImageButton addScheduleBT;

    long customerID = 2; //todo recuperar ID do usuario atual
    List<SchedulingDTO> schedules;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_cust_schedule_list_9, container, false);


        initScheduleList();
        setListSchedulesListener();
        setAddServiceListener();

        return v;
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

        //verifica valores no banco

        SchedulingService scheduleService = new SchedulingService();
        ArrayList<ScheduleItem> items = new ArrayList<>();

        try{
            schedules = scheduleService.findUpcomingSchedulingByCustomerId(customerID);
        } catch (Exception e){
            schedules = null;
            Toast.makeText(getActivity(), "Ocorreu um erro interno. Favor contactar o administrador!", Toast.LENGTH_SHORT).show();
        }

        //recebe data atual e configura no calendario
        Date pinnedMenuDate = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(pinnedMenuDate);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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
            item.setSection(false);
            try {
                //todo falta calcular o left time
                item.setScheduleLeftTime(new Time(sdf.parse("01:10").getTime()));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            //Verifica se o item é o primeiro da lista, ou se há diferenca de DIA para criar uma SECTION
            if (i == 0 || cal.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)
                    || cal.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)
                    || cal.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)){
                ScheduleItem sectionItem = new ScheduleItem();
                sectionItem.setSection(true);
                sectionItem.setScheduleDate(item.getScheduleDate());
                items.add(sectionItem);
                cal = cal2;
            }
        items.add(item);
        }
        return items;
    }

    public void setListSchedulesListener(){
        listSchedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //todo deve passar nesta intent os dados do agendamento selecionados
                ScheduleItem selectedItem = (ScheduleItem) listSchedules.getItemAtPosition(position);
                SchedulingDTO selectedSchedule = schedules.get(selectedItem.getListPosition());
                if(!selectedItem.isSection()) {
                    Intent intent = new Intent(getActivity(), CustScheduleConfirmActivity_8.class);
                    intent.putExtra("professionalName", selectedSchedule.getDailySchedule().getProfessional().getName());
                    intent.putExtra("street", selectedSchedule.getAddressStreet());
                    intent.putExtra("number", selectedSchedule.getAddressNumber());
                    intent.putExtra("cep", selectedSchedule.getAddressZipCode());
                    intent.putExtra("complement", selectedSchedule.getAddressComplement());
                    intent.putExtra("district", selectedSchedule.getAddressDistrict());
                    intent.putExtra("city", selectedSchedule.getAddressCity());
                    intent.putExtra("state", selectedSchedule.getAddressState().getCode());
                    intent.putExtra("profession", selectedSchedule.getDailySchedule().getProfessional().getProfession());
                    intent.putExtra("selectedServices", new ArrayList<String>()); //todo buscar todos agendamentos do servico
                    intent.putExtra("sDate", "Sem dados"); //todo tranformar os dados da data em int e strings para proxima tela
                    intent.putExtra("selectedHour", 0);
                    intent.putExtra("selectedMinutes", 0);
                    intent.putExtra("totalTime", 0);
                    intent.putExtra("totalPrice", 0);
                    startActivity(intent);
                }
            }
        });
    }
    private void setAddServiceListener(){
        addScheduleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustScheduleDateFragmentPortrait_6 custScheduleDateFragmentPortrait_6 = new CustScheduleDateFragmentPortrait_6();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, custScheduleDateFragmentPortrait_6);

                //este metodo permite q o usuario navegue de volta
                ft.addToBackStack(null);

                ft.commit();
            }
        });
    }
}
