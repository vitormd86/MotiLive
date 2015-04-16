package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 15/04/2015.
 */
public class BreakTimeAdapter extends ArrayAdapter<String> {
    public BreakTimeAdapter(Context context) {
        super(context, R.layout.view_break_time);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View view = theInflator.inflate(R.layout.view_break_time, parent, false);

        //BreakTimeItem breakTimeItem = getItem(position);
        //EditText breakTimeStart = (EditText) view.findViewById(R.id.breakStart);
       // EditText breakTimeEnd = (EditText) view.findViewById(R.id.breakEnd);

        return view;
    }
}
