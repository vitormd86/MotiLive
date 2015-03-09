package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.Bean.Servico;
import com.example.henrique.list.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Massaru on 17/02/2015.
 */
public class myAdapterServiceTypes extends ArrayAdapter<Servico> {
    public myAdapterServiceTypes(Context context, Servico[] clients) {
        super(context, R.layout.view_service_type, clients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_service_type, parent, false);

        Servico service = getItem(position);
        TextView textService = (TextView) theView.findViewById(R.id.service);
        TextView textServiceLenght = (TextView) theView.findViewById(R.id.serviceLength);
        TextView textPrice = (TextView) theView.findViewById(R.id.price);

        textService.setText(service.getNome());
        Time timeServiceLenght = service.getTempo();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getDefault());

        try {
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        textServiceLenght.setText("Duração: " + df.format(timeServiceLenght) + "h");
        textPrice.setText("R$" + String.format("%.2f", service.getValor()));

        return theView;
    }
}
