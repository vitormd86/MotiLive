package com.example.henrique.list.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 15/04/2015.
 */
public class BreakTimeAdapter extends ArrayAdapter<Integer> {

    Button breakTimeRemoveButton;

    public BreakTimeAdapter(Context context) {
        super(context, R.layout.view_break_time);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View view = theInflator.inflate(R.layout.view_break_time, parent, false);

        breakTimeRemoveButton = (Button) view.findViewById(R.id.breakTimeRemoveButton);
        setBreakTimeRemoveListener(position);

        return view;
    }

    private void setBreakTimeRemoveListener(final int position){
        //esta funcao configura o listener do botao de remover intervalo
        breakTimeRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(position));
                //Toast.makeText(getContext(), thisAdapter.getCount()  + "Remover item da posicao " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
