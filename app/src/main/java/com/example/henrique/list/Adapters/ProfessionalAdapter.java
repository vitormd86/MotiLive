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

import br.com.motiserver.dto.ProfessionalDTO;


//Adaptador ele joga as  a imagem e um EditText dentro de uma lista
public class ProfessionalAdapter extends ArrayAdapter<ProfessionalDTO> {
    public ProfessionalAdapter(Context context, List<ProfessionalDTO> values) {
        super(context, R.layout.view_person, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_person, parent, false);

        ProfessionalDTO professionalDTO = getItem(position);
        TextView textView = (TextView) theView.findViewById(R.id.textView1);
        textView.setText(professionalDTO.getName());
        ImageView theImageView =  (ImageView) theView.findViewById(R.id.imageView1);
        theImageView.setImageResource(R.drawable.img_photo_default);

        return theView;

        }
}
