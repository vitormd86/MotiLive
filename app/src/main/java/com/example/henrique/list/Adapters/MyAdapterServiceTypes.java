package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

/**
 * Created by Massaru on 17/02/2015.
 */
public class MyAdapterServiceTypes extends ArrayAdapter<String> {
    public MyAdapterServiceTypes(Context context, String[] clients) {
        super(context, R.layout.view_service_type, clients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_service_type, parent, false);

        String service = getItem(position);
        TextView textService = (TextView) theView.findViewById(R.id.service);
        TextView textServiceLenght = (TextView) theView.findViewById(R.id.serviceLength);
        TextView textPrice = (TextView) theView.findViewById(R.id.price);

        textService.setText(service);
        textServiceLenght.setText("20 minutos");
        textPrice.setText("R$12,22");

        return theView;

    }
}
