package com.example.henrique.list.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.ScheduleAdapter;
import com.example.henrique.list.Beans.ScheduleItem;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class ConsultScheduleFragment extends Fragment {

    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_consult_schedules, container, false);

        ArrayList<ScheduleItem> scheduleItems = initScheduleItems();

        String[] favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};

        PinnedSectionListView listSchedules = (PinnedSectionListView) v.findViewById(R.id.pinnedListSchedules);
        ArrayAdapter schedulesAdapter = new ScheduleAdapter(getActivity(), scheduleItems);

        listSchedules.initShadow(false);
        listSchedules.setAdapter(schedulesAdapter);

        return v;
    }

    public ArrayList<ScheduleItem> initScheduleItems(){
        String[] favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru Kubota", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru Kubota", "Ivo Issao Tobioka",
            "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};
        ArrayList<ScheduleItem> items = new ArrayList<>();
        ScheduleItem item = new ScheduleItem();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (int i = 0; i < 15; i++){
            item.setNameProfessional(favoriteProfessionals[i]);
            item.setScheduleDate(new Date());
            try {
                item.setScheduleInicialTime(new Time(sdf.parse("08:00").getTime()));
                item.setScheduleFinalTime(new Time(sdf.parse("08:15").getTime()));
                item.setScheduleDuration(new Time(sdf.parse("00:15").getTime()));
                item.setScheduleLeftTime(new Time(sdf.parse("01:10").getTime()));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        items.add(item);
        }
        return items;
    }
}
