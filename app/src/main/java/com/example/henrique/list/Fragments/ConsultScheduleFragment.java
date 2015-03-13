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
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;

public class ConsultScheduleFragment extends Fragment {

    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_consult_schedules, container, false);

        String[] favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7", "Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro", "Vitor Mendes", "Professional 6", "Professional 7"};

        PinnedSectionListView listSchedules = (PinnedSectionListView) v.findViewById(R.id.pinnedListSchedules);
        ArrayAdapter adap = new ScheduleAdapter(getActivity(), favoriteProfessionals);

        listSchedules.initShadow(false);
        listSchedules.setAdapter(adap);

        return v;
    }

}
