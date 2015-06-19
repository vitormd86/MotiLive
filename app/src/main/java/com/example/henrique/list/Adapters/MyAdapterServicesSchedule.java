package com.example.henrique.list.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.henrique.list.Mapeamento_de_Classes.Servico;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.DateUtil;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

        Calendar serviceLenghtCal = service.getTime();

        //para testar hora separada de minutos
        //textServiceLenght.setText(serviceLenghtCal.get(Calendar.HOUR) + " " + serviceLenghtCal.get(Calendar.MINUTE));

        //alimentando campos da view
        textService.setText(service.getName());
        textServiceLenght.setText(DateUtil.calendarToMinutes(serviceLenghtCal) + "min");
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
