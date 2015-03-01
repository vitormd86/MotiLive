package com.example.henrique.list.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.CalendarView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.example.henrique.list.R;

/**
 * Created by Cristor on 25/02/2015.
 */
public class ProfessionalDaysFragment extends Fragment {

    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_professional_days, container, false);

        String professionalName = "Leandro Massaro Kubota";
        String occupation = "Massagista";
        TextView myTextView1 = (TextView) v.findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) v.findViewById(R.id.textView2);
        final CalendarView myCalendarView = (CalendarView) v.findViewById(R.id.calendarView);
        final long date = myCalendarView.getDate();
        //Configura as variaveis do cabecalho
        myTextView1.setText(professionalName);
        myTextView2.setText(occupation);

        //Configura botoes (listener) do calendario
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //corrige bug da rolagem
                if (myCalendarView.getDate() != date ){
                    //instancia proximo fragment a ser iniciado e o manager paraa transacao
                    ProfessionalHoursFragment nextFragment = new ProfessionalHoursFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //armazena dados para proximo fragment
                    Bundle args = new Bundle();
                    args.putInt("dayOfMonth", dayOfMonth);
                    args.putInt("month",month + 1);
                    args.putInt("year", year);
                    nextFragment.setArguments(args);

                    //inicia a transacao de Fragments
                    ft.replace(R.id.content_frame, nextFragment);

                    //este metodo permite q o usuario navegue de volta
                    ft.addToBackStack(null);

                    ft.commit();
                } else {

                }


            }
        });
        return v;
    }
}