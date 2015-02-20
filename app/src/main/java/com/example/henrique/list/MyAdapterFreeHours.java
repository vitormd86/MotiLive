package com.example.henrique.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Massaru on 17/02/2015.
 */
public class MyAdapterFreeHours extends ArrayAdapter<String> {
    int numberOfFreeHours;
    ListView thisListView;

    public MyAdapterFreeHours(Context context, ArrayList clients, ListView listView) {
        super(context, R.layout.view_free_hour, clients);
        thisListView =  listView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_free_hour, parent, false);
        int listViewHeight = thisListView.getHeight();
        numberOfFreeHours = this.getCount();
        final String hours = getItem(position);

        TextView textHour = (TextView) theView.findViewById(R.id.hour);
        int textHourHeight = listViewHeight / numberOfFreeHours;

        textHour.setText(hours);
        textHour.setHeight(textHourHeight);

        return theView;
    }

 //   private int setItemHeight(){
 //       int height;
 //       numberOfFreeHours = this.getCount();


 //       return height;
//    }


}
