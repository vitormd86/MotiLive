package com.example.henrique.list.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.henrique.list.Mapeamento_de_Classes.Servico;
import com.example.henrique.list.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class MyAdapterServicesSchedule extends ArrayAdapter<Servico> {
    public MyAdapterServicesSchedule(Context context, Servico[] clients) {
        super(context, R.layout.view_schedule_service_type, clients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_schedule_service_type, parent, false);

        Servico service = getItem(position);
        TextView textService = (TextView) theView.findViewById(R.id.service);
        TextView textServiceLenght = (TextView) theView.findViewById(R.id.serviceLength);
        TextView textPrice = (TextView) theView.findViewById(R.id.price);
        Button infoButton = (Button) theView.findViewById(R.id.infoButton);

        setInfoButtonListener(infoButton, position);

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

    private void setInfoButtonListener(Button bt,final int pos){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog popupAlert;
                //Alimenta o Alert Dialog para apresentar dados do servico selecionado
                builder.setTitle(getItem(pos).getNome());
                builder.setMessage(getItem(pos).getDescricao());
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                popupAlert = builder.create();
                popupAlert.show();
            }
        });
    }

}
