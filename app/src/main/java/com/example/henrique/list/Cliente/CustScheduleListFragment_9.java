package com.example.henrique.list.Cliente;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.ScheduleAdapter;
import com.example.henrique.list.Beans.ScheduleItem;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class CustScheduleListFragment_9 extends Fragment {

    private View v;
    private FragmentActivity fa;

    PinnedSectionListView listSchedules;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_cust_schedule_list_9, container, false);

        ArrayList<ScheduleItem> scheduleItems = initScheduleItems();

        listSchedules = (PinnedSectionListView) v.findViewById(R.id.pinnedListSchedules);
        ArrayAdapter schedulesAdapter = new ScheduleAdapter(getActivity(), scheduleItems);

        listSchedules.initShadow(false);
        listSchedules.setAdapter(schedulesAdapter);
        setListSchedulesListener();

        return v;
    }

    public ArrayList<ScheduleItem> initScheduleItems(){
        //inicia instancias de agendamento e itens a serem apresentados na lista
        //todo aqui deve ser gerada um vetor de AGENDAMENTO e de seus respectivos nomes de PROFISSIONAIS, para poder resgatar todos os dados
        String[] favoriteProfessionals = new String[]{"Leandro Massaru", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};
        ArrayList<ScheduleItem> items = new ArrayList<>();

        //recebe data atual e configura no calendario
        Date pinnedMenuDate = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(pinnedMenuDate);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");



        //inicia proximos itens de agendamento, e verifica se a data é a mesma. caso contrario gera outo item SECTION
        for (int i = 0; i < favoriteProfessionals.length; i++){
            ScheduleItem item = new ScheduleItem();

            //inicializa valores da view a partir dos agendamentos buscado no BD
            item.setNameProfessional(favoriteProfessionals[i]);
            //todo receber data do vetor de agendamento
            item.setScheduleDate(new Date());
            cal2.setTime(item.getScheduleDate());
            item.setSection(false);
            try {
                item.setScheduleInicialTime(new Time(sdf.parse("08:00").getTime()));
                item.setScheduleFinalTime(new Time(sdf.parse("08:15").getTime()));
                item.setScheduleDuration(new Time(sdf.parse("00:15").getTime()));
                item.setScheduleLeftTime(new Time(sdf.parse("01:10").getTime()));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            //Verifica se o item é o primeiro da lista, ou se há diferenca de DIA para criar uma SECTION
            if (i == 0 || cal.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)){
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
                Intent intent = new Intent(getActivity(),CustScheduleConfirmActivity_8.class);
                //todo deve passar nesta intent os dados do agendamento selecionados
                ScheduleItem selectedItem = (ScheduleItem) listSchedules.getItemAtPosition(position);
                intent.putExtra("professionalName", selectedItem.getNameProfessional());
                intent.putExtra("profession", "Sem dados");
                intent.putExtra("selectedServices", new ArrayList<String>());
                intent.putExtra("sDate", "Sem dados");
                intent.putExtra("selectedHour", 0);
                intent.putExtra("selectedMinutes", 0);
                intent.putExtra("totalTime", 0);
                intent.putExtra("totalPrice", 0);
                startActivity(intent);
            }
        });

    }
}