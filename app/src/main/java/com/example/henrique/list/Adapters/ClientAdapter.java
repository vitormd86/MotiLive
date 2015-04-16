package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henrique.list.R;

/**
 * Created by Massaru on 04/04/2015.
 */
public class ClientAdapter extends ArrayAdapter<String>{
    public ClientAdapter(Context context, String[] values) {
        super(context, R.layout.view_person, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_person, parent, false);

        String clients = getItem(position);
        TextView textView = (TextView) theView.findViewById(R.id.textView1);

        textView.setText(clients);
        ImageView theImageView =  (ImageView) theView.findViewById(R.id.imageView1);
        theImageView.setImageResource(R.drawable.hide);

        return theView;

    }
}