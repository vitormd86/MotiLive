package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.henrique.list.Beans.ScheduleItem;
import com.example.henrique.list.Utilidade_Publica.DateUtil;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView.PinnedSectionListAdapter;
import com.example.henrique.list.R;

import java.util.ArrayList;
import java.util.Calendar;


/*Adaptador com mais de um TIPO de view.... ITEM e SECTION. Ele gera diferentes funcionalidade parqa cada tipo de view*/
public class ScheduleAdapter extends ArrayAdapter<ScheduleItem> implements PinnedSectionListAdapter{

    public static final int ITEM = 0;
    public static final int SECTION = 1;

    public ScheduleAdapter(Context context, ArrayList<ScheduleItem> values) {
        super(context, R.layout.view_pinnedlist_schedules, values);
    }

    //determina quantos tipos de view a lista contem
    @Override public int getViewTypeCount() {
        return 2;
    }
    //determina o tipo de view, para view da posicao indicada
    @Override public int getItemViewType(int position) {
        if(getItem(position).isSection()){
            return SECTION;
        } else {
            return ITEM;
        }
    }
    //sempre que este metodo retornar positivo a view fica presa no topo da listview
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == SECTION;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View view;
        Calendar cal = Calendar.getInstance();
        cal.setTime(getItem(position).getScheduleDate());

        switch (getItemViewType(position)){
            //Caso a view seja de SECAO
            case SECTION:
                view = theInflator.inflate(R.layout.view_pinnedlist_schedules_datetitle, parent, false);
                TextView textDay = (TextView) view.findViewById(R.id.textScheduleDayAndMonth);
                TextView textDayOfWeek = (TextView) view.findViewById(R.id.textScheduleDayOfWeek);

                textDay.setText(cal.get(Calendar.DAY_OF_MONTH) + " de " + DateUtil.getMonthString(cal.get(Calendar.MONTH)));
                textDayOfWeek.setText("  -  " + DateUtil.getDayOfWeekString(cal.get(Calendar.DAY_OF_WEEK)));
                //AQUI A VIEW DEVE RECEBER O BACKGROUND para nao aparecer o item saindo.
                break;
            //Caso a view seja ITEM
            default:
                view = theInflator.inflate(R.layout.view_pinnedlist_schedules, parent, false);
                String profissionalName = getItem(position).getPersonName();
                String inicialTime = getItem(position).getScheduleInicialTime();
                String finalTime = getItem(position).getScheduleFinalTime();
                String leftTime = getItem(position).getScheduleLeftTime();

                TextView durationTV = (TextView) view.findViewById(R.id.textScheduleDuration);
                TextView nameTV = (TextView) view.findViewById(R.id.textScheduleName);
                TextView leftTimeTV = (TextView) view.findViewById(R.id.textLeftTime);

                nameTV.setText(profissionalName);
                durationTV.setText(inicialTime + "h ~ " + finalTime + "h");
                leftTimeTV.setText(leftTime);
        }


        return view;
    }



}
