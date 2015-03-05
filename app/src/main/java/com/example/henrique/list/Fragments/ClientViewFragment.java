package com.example.henrique.list.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrique.list.Adapters.MyAdapterClientSmall;
import com.example.henrique.list.R;


public class ClientViewFragment extends Fragment {
    private View v;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.fragment_client_view, container, false);
        fa = super.getActivity();
        //Intent intent = getIntent();
        Bundle args = getArguments();

        //recebe usuario da fragment anterior
        String clientName = args.getString("client");

        //recebe usuario da Intent
        //String clientName = intent.getStringExtra("nameClient");

        String clientEmail = "massaro@karabacana.com.br"; // buscar do banco
        String clientPhoneNumber = "69696969"; // buscar do banco
        String [] schedules = {"Agend 1", "Agend 2", "Agend 3"}; // agendamentos do usuario atual

        TextView myTextView1 = (TextView) v.findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) v.findViewById(R.id.textView2);
        ListView myListView1 = (ListView) v.findViewById(R.id.ListView);
        ImageView phoneImage = (ImageView) v.findViewById(R.id.imageView2);

        ListAdapter myAdapter = new MyAdapterClientSmall(getActivity(), schedules);

        //Configurando as variaveis do cabecalho
        myTextView1.setText(clientName);
        myTextView2.setText(clientEmail);
        //configurando listener para telefonar.


        //Configurando lista dos clientes
        myListView1.setAdapter(myAdapter);

        return v;
    }


}
