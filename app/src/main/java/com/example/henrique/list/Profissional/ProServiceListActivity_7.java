package com.example.henrique.list.Profissional;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.henrique.list.Adapters.MyAdapterServicesPro;
import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 5/9/15.
 */
public class ProServiceListActivity_7 extends ActionBarActivity{

    ListView servicesLV;
    ArrayAdapter myServiceAdapter;
    ArrayList<String> servicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_list_7);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
    }

    private void initViews(){

        //inicia valores dos servicos
        servicesList = new ArrayList<>();
        servicesList.add("Servico 1");
        servicesList.add("Servico 2");
        servicesList.add("Servico 3");

        //inicia componentes da tela
        servicesLV = (ListView) findViewById(R.id.serviceListProLV_7);
        myServiceAdapter = new MyAdapterServicesPro(getApplicationContext(), servicesList);
        servicesLV.setAdapter(myServiceAdapter);
    }

}
