package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henrique.list.R;

import java.util.List;

import br.com.motiserver.dto.CustomerDTO;


public class ClientAdapter extends ArrayAdapter<CustomerDTO>{
    public ClientAdapter(Context context, List<CustomerDTO> values) {
        super(context, R.layout.view_list_cust_pro_schedule, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View v = theInflator.inflate(R.layout.view_list_cust_pro_schedule, parent, false);

        CustomerDTO customerDTO = getItem(position);

        TextView nameTV = (TextView) v.findViewById(R.id.profName_list_professional);
        TextView contactTV = (TextView) v.findViewById(R.id.profProfession_list_professional);
        ImageView photoIM =  (ImageView) v.findViewById(R.id.photo_list_professional);

        nameTV.setText(customerDTO.getName());
        contactTV.setText("(" + customerDTO.getPhoneCode() + ") " + customerDTO.getPhoneNumber());

        //todo-vitor recebr foto do profissionalDTO e aponta-la no setImage
        photoIM.setImageResource(R.drawable.img_photo_default);

        return v;
    }
}
