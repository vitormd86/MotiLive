package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.util.ArrayList;


public class MyAdapterServicesPro extends ArrayAdapter<String>{
//todo por que esta classe existe?
    public MyAdapterServicesPro(Context context, ArrayList<String> services) {
        super(context, R.layout.view_list_services_professional, services);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        convertView = theInflator.inflate(R.layout.view_list_services_professional, parent, false);

        TextView serviceName = (TextView) convertView.findViewById(R.id.serviceNameETPro_6);
        String service = getItem(position);
        serviceName.setText(service);

        return convertView;
    }
}
