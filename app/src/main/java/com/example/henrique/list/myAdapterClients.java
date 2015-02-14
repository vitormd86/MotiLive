package com.example.henrique.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vitor on 14/02/2015.
**/
//PRECISO PASSAR MAIS DE UMA ARRAY COMO ARGUMENTO NO CONSTRUTOR... (HORARIO E STATUS)
public class myAdapterClients extends ArrayAdapter<String> {
    public myAdapterClients(Context context, String[] clients) {
        super(context,R.layout.schedule_client, clients);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.schedule_client, parent, false);

        String clients = getItem(position);
        TextView name = (TextView) theView.findViewById(R.id.textView1);
        TextView status = (TextView) theView.findViewById(R.id.textView2);
        TextView hour = (TextView) theView.findViewById(R.id.textView3);

        ImageView theImageView =  (ImageView) theView.findViewById(R.id.imageView1);

        name.setText(clients);
        status.setText("STATUS");
        hour.setText("HORARIO");
        theImageView.setImageResource(R.drawable.hide);

        return theView;

    }
}
