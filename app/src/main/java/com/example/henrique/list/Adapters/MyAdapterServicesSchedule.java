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

import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.DateUtilMoti;

import java.util.Date;
import java.util.List;

import br.com.motiserver.dto.ServiceDTO;


public class MyAdapterServicesSchedule extends ArrayAdapter<ServiceDTO> {
    public MyAdapterServicesSchedule(Context context, List<ServiceDTO> serviceDTOList) {
        super(context, R.layout.view_schedule_service_type, serviceDTOList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View v = theInflator.inflate(R.layout.view_schedule_service_type, parent, false);


        TextView textService = (TextView) v.findViewById(R.id.service);
        TextView textServiceLenght = (TextView) v.findViewById(R.id.serviceLength);
        TextView textPrice = (TextView) v.findViewById(R.id.price);
        Button infoButton = (Button) v.findViewById(R.id.infoButton);


        ServiceDTO service = getItem(position);

        Date serviceLenghtDate = service.getTime().getTime();

        //alimentando campos da view
        textService.setText(service.getName());
        textServiceLenght.setText(DateUtilMoti.getMinutesFromDate(serviceLenghtDate) + "min");
        textPrice.setText("R$ " + service.getValue());

        //configurando listener do botao de Info
        setInfoButtonListener(infoButton, position);
        return v;
    }

    private void setInfoButtonListener(Button bt,final int pos){
        //configura listener do botao de Info
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog popupAlert;
                //Alimenta o Alert Dialog para apresentar dados do servico selecionado
                builder.setTitle(getItem(pos).getName());
                builder.setMessage(getItem(pos).getDescription());
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
