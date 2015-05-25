package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterServicesPro;
import com.example.henrique.list.R;

import java.util.ArrayList;

/**
 * Created by Cristor on 5/9/15.
 */
public class ProServiceListActivity_7 extends ActionBarActivity{

    ImageButton addServiceBT;
    ListView servicesLV;

    ArrayAdapter myServiceAdapter;
    ArrayList<String> servicesList;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_list_7);

        //desabilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        initViews();
        setSelectServiceListener();
        setAddServiceListener();
    }

    private void initViews(){
        //inicia valores dos servicos
        servicesList = new ArrayList<>();

        //inicia componentes da tela
        addServiceBT = (ImageButton) findViewById(R.id.addService);
        servicesLV = (ListView) findViewById(R.id.serviceListProLV_7);
        myServiceAdapter = new MyAdapterServicesPro(getApplicationContext(), servicesList);
        servicesLV.setAdapter(myServiceAdapter);
    }

    private void setSelectServiceListener(){
        servicesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String serviceToIntent = String.valueOf(parent.getItemAtPosition(position));
                Intent intent = new Intent(ProServiceListActivity_7.this, ProServiceNewActivity_6.class);
                intent.putExtra("service", serviceToIntent);
                intent.putExtra("isEditing", true);
                startActivity(intent);
            }
        });
    }
    private void setAddServiceListener(){
        addServiceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProServiceListActivity_7.this, ProServiceNewActivity_6.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //todo deve receber todos os servicos do bd e adicionar ao vetor
        extras = getIntent().getExtras();
        if(extras != null) {
            //recuperando dados da tela anterior
            servicesList.add(extras.getString("service"));
        }

        //atualiza dados da listView
        myServiceAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                Intent intent = new Intent(ProServiceListActivity_7.this, ProScheduleConfig_8.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
