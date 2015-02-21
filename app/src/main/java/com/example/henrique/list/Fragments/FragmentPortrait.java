package com.example.henrique.list.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.myAdapter;
import com.example.henrique.list.HourConsult;
import com.example.henrique.list.R;
import com.example.henrique.list.SecondScreen;
import com.example.henrique.list.opcoesSelecionadas;

/**
 * Created by Henrique on 12/02/2015.
 */
public class FragmentPortrait extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.portrait_fragment, parent, false);

        ListView theListView = (ListView) v.findViewById(R.id.ListView); // inicializa a List View do fragment inflado

        String[] favoriteProfessionals;// aqui eu inicializo o array de opcoes
        favoriteProfessionals = new String[]{"Leandro Massaru Kubota", "Ivo Issao Tobioka",
                "Michel SantaGuida", "Henrique Tamashiro"};

        ListAdapter theAdapter; //inicializa o adaptador de array, pra encaixar o array na lista
        theAdapter = new myAdapter(v.getContext(), favoriteProfessionals);

        theListView.setAdapter(theAdapter);// seleciona o adaptador... no caso  "theAdapter" q eh do tipo myAdapter



        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // HIDE, mudanca provisoria...
                //opcoesSelecionadas opcoes = new opcoesSelecionadas(String.valueOf(parent.getItemAtPosition(position)), null, null);
                String opcoes = String.valueOf(parent.getItemAtPosition(position));
                Intent intentHourConsult;
                intentHourConsult = new Intent(getActivity() , HourConsult.class);

                intentHourConsult.putExtra("Escolhas", opcoes); // joga o objeto para a proxima activity
                startActivity(intentHourConsult);
            }
        });

        return v;
    }

}
