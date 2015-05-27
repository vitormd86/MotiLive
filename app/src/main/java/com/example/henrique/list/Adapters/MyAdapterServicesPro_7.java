package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.util.ArrayList;

import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by htamashiro on 5/25/15.
 */
public class MyAdapterServicesPro_7 extends ArrayAdapter<ServiceDTO> {

    ServiceDTO serviceDTO = new ServiceDTO();

    public MyAdapterServicesPro_7(Context context, ArrayList<ServiceDTO> services) {
        super(context, R.layout.view_professional_services, services);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        convertView = theInflator.inflate(R.layout.view_professional_services, parent, false);

        TextView serviceName = (TextView) convertView.findViewById(R.id.serviceNameETPro_6);
        ServiceDTO service = getItem(position);
        serviceName.setText(service.getName());

        return convertView;
    }
}
