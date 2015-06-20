package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.util.ArrayList;


public class MyAdapterFreeMinutes extends ArrayAdapter<Integer> {
    ListView thisListView;

    public MyAdapterFreeMinutes(Context context, ArrayList minutes, ListView listView) {
        super(context, R.layout.view_list_free_minutes, minutes);
        thisListView =  listView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View v = theInflator.inflate(R.layout.view_list_free_minutes, parent, false);
        int minutes = getItem(position);

        TextView textMinutes = (TextView) v.findViewById(R.id.minutes);

        textMinutes.setText(":" + String.format("%02d", minutes));
        textMinutes.setHeight(getItemHeight());

        return v;
    }

    private int getItemHeight(){
        //calcula altura de cada campo
        int listViewHeight = thisListView.getHeight();
        int numberOfFreeHours = this.getCount();

        return listViewHeight / numberOfFreeHours;
    }


}
