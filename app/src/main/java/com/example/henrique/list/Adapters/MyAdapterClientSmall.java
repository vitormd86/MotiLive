package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henrique.list.R;

/**
 * Created by Massaru on 16/02/2015.
 */
//PRECISO PASSAR MAIS DE UMA ARRAY COMO ARGUMENTO NO CONSTRUTOR... (HORARIO E STATUS)
public class MyAdapterClientSmall  extends ArrayAdapter<String> {
    public MyAdapterClientSmall(Context context, String[] clients) {
        super(context, R.layout.view_schedule_client_small, clients);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.view_schedule_client_small, parent, false);

        final String schedules = getItem(position);
        TextView status = (TextView) theView.findViewById(R.id.textView1);
        TextView dataHora = (TextView) theView.findViewById(R.id.textView2);
        TextView service = (TextView) theView.findViewById(R.id.textView3);

        status.setText("Confirmado");
        dataHora.setText(schedules);
        service.setText("Tântrico");

        return theView;

    }
}
