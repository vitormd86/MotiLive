package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 24/03/2015.
 */
public class ServicesNameAdapter extends ArrayAdapter <String> {
    public ServicesNameAdapter (Context context, ArrayList<String> values) {
        super(context, R.layout.view_services_name, values);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_services_name, parent, false);


        String services = getItem(position);
        TextView textView = (TextView) theView.findViewById(R.id.serviceNameETPro_6);
        textView.setText(services);

        return theView;

    }

}
