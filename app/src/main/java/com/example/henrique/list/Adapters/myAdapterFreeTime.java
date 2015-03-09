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

/**
 * Created by Massaru on 17/02/2015.
 */
public class myAdapterFreeTime extends ArrayAdapter<Integer> {
    int numberOfFreeHours;
    ListView thisListView;

    public myAdapterFreeTime(Context context, ArrayList clients, ListView listView) {
        super(context, R.layout.view_free_hour, clients);
        thisListView =  listView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_free_hour, parent, false);
        int listViewHeight = thisListView.getHeight();
        numberOfFreeHours = this.getCount();
        int hours = getItem(position);

        TextView textHour = (TextView) theView.findViewById(R.id.hour);
        int textHourHeight = listViewHeight / numberOfFreeHours;

        textHour.setText(Integer.toString(hours));
        textHour.setHeight(textHourHeight);

        return theView;
    }

 //   private int setItemHeight(){
 //       int height;
 //       numberOfFreeHours = this.getCount();


 //       return height;
//    }


}
