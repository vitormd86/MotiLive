package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

/**
 * Created by Henrique on 06/02/2015.
 */
// mesma coisa que o myAdapter só que eu tirei o ImageView, pra fazer uma lista de serviços
public class myAdapterServicos extends ArrayAdapter<String> {
    public myAdapterServicos(Context context, String[] values) {
        super(context, R.layout.row_servicos, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.row_servicos, parent, false);

        String Servicos = getItem(position);
        TextView textView = (TextView) theView.findViewById(R.id.textView1);

        textView.setText(Servicos);

        return theView;


    }
}
