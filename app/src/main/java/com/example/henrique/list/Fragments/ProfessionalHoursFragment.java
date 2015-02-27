package com.example.henrique.list.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.myAdapterClients;
import com.example.henrique.list.R;

/**
 * Created by Cristor on 25/02/2015.
 */
public class ProfessionalHoursFragment extends Fragment {
    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = super.getActivity();
        v = inflater.inflate(R.layout.fragment_professional_hours, container, false);

        //recebe argumentos da Fragment anterior
        Bundle args = getArguments();


        String professionalName = "Leandro Massaro Kubota"; // buscar do banco
        String[] clientesAgendados = {"Cliente 1 ID", "Cliente 2 ID", "Cliente 3 ID"};  // buscar do banco

        TextView myTextView1 = (TextView) v.findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) v.findViewById(R.id.textView2);
        ListView myListView1 = (ListView) v.findViewById(R.id.ListView);

        ListAdapter myAdapter = new myAdapterClients(getActivity(), clientesAgendados);

        //Configurando as variaveis do cabecalho
        myTextView1.setText(professionalName);
        //Configurando data a partir dos dados da Intent
        myTextView2.setText(
                args.getInt("dayOfMonth") + "/" +
                args.getInt("month") + "/" +
                args.getInt("year")
        //        activityThatCalled.getStringExtra("dayOfMonth") + "/" +
        //                activityThatCalled.getStringExtra("month") + "/" +
        //                activityThatCalled.getStringExtra("year")
        );
        //Configurando lista dos clientes
        myListView1.setAdapter(myAdapter);

        return v;

    }


}
