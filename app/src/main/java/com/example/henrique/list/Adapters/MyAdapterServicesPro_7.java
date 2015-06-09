package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import br.com.motiserver.dto.ServiceDTO;


public class MyAdapterServicesPro_7 extends ArrayAdapter<ServiceDTO> {

    ServiceDTO serviceDTO = new ServiceDTO();

    public MyAdapterServicesPro_7(Context context, ArrayList<ServiceDTO> services) {
        super(context, R.layout.view_professional_services, services);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        convertView = theInflator.inflate(R.layout.view_professional_services, parent, false);
        Calendar calendar;

        TextView serviceName = (TextView) convertView.findViewById(R.id.serviceNameETPro_7);
        TextView sessionTime = (TextView) convertView.findViewById(R.id.sessionTimeTVPro_7);
        TextView sessionPrice = (TextView) convertView.findViewById(R.id.sessionPriceTVPro_7);

        ServiceDTO service = null;
        service = getItem(position);
        try {

            serviceName.setText(service.getName());
            calendar = service.getTime();
            calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

            int Hora = calendar.get(Calendar.HOUR);
            int Minuto = calendar.get(Calendar.MINUTE);
            sessionTime.setText(""+Hora+":"+Minuto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BigDecimal price = null;
            try {
                price = service.getValue();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("getValue returned 0");
            }
            if (price!=null){
                sessionPrice.setText("R$"+ price.toString());
            }else{
                System.out.println("price veio null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
