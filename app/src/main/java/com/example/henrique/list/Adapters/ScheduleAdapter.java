package com.example.henrique.list.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView;
import com.example.henrique.list.Utilidade_Publica.PinnedSectionListView.PinnedSectionListAdapter;
import com.example.henrique.list.R;

/**
 * Created by Henrique on 06/02/2015.
 */
/*Adaptador com mais de um TIPO de view.... ITEM e SECTION. Ele gera diferentes funcionalidade parqa cada tipo de view*/
public class ScheduleAdapter extends ArrayAdapter<String> implements PinnedSectionListAdapter{

    public static final int ITEM = 0;
    public static final int SECTION = 1;

    public ScheduleAdapter(Context context, String[] values) {
        super(context, R.layout.view_schedules, values);
    }

    //determina quantos tipos de view a lista contem
    @Override public int getViewTypeCount() {
        return 2;
    }
    //determina o tipo de view, para view da posicao indicada
    @Override public int getItemViewType(int position) {
        if(position == 0 || position == 5){
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
        String profissionais = getItem(position);

        switch (getItemViewType(position)){
            //Caso a view seja de SECAO
            case SECTION:
                view = theInflator.inflate(R.layout.view_schedules_dates_pinned, parent, false);
                TextView textDate = (TextView) view.findViewById(R.id.textScheduleDay);
                textDate.setText("15");
                //AQUI A VIEW DEVE RECEBER O BACKGROUND para nao aparecer o item saindo.

                break;
            //Caso a view seja ITEM
            default:
                view = theInflator.inflate(R.layout.view_schedules, parent, false);
                TextView textTitle = (TextView) view.findViewById(R.id.textProfessionalName);
                textTitle.setText(profissionais);
        }


        return view;
    }

}
