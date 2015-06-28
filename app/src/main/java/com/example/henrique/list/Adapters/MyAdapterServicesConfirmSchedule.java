package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.DateUtil;

import java.util.ArrayList;

import br.com.motiserver.dto.ServiceDTO;


public class MyAdapterServicesConfirmSchedule extends ArrayAdapter <ServiceDTO> {
    public MyAdapterServicesConfirmSchedule(Context context, ArrayList<ServiceDTO> services) {
        super(context, R.layout.view_list_services_conf_schedule, services);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_list_services_conf_schedule, parent, false);


        TextView nameTV = (TextView) theView.findViewById(R.id.serviceNameTV_cust8);
        TextView timeTV = (TextView) theView.findViewById(R.id.serviceTimeTV_cust8);
        TextView priceTV = (TextView) theView.findViewById(R.id.servicePriceTV_cust8);

        ServiceDTO serviceDTO = getItem(position);

        nameTV.setText(serviceDTO.getName());
        timeTV.setText(DateUtil.getMinutesFromCalendar(serviceDTO.getTime()) + " min");
        priceTV.setText("R$ " + serviceDTO.getValue().toString());

        return theView;

    }

}
