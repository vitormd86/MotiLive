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

import com.example.henrique.list.Adapters.MyAdapterServicesPro_7;
import com.example.henrique.list.R;
import com.example.henrique.list.Service.ServiceService;
import com.example.henrique.list.Utilidade_Publica.Globals;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.util.ArrayList;

import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by Cristor on 5/9/15.
 */
public class ProServiceListActivity_7 extends ActionBarActivity{

    //ImageButton
    ImageButton addServiceBT;
    //ListView
    ListView servicesLV;
    //objects
    ProfessionalDTO professionalDTO;
    ServiceDTO serviceDTO;
    //ints
    Long idProfessional;
    //String
    String idProfessionalString;

    //Arrays
    ArrayAdapter myServiceAdapter;
    ArrayList<ServiceDTO> servicesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_list_7);
        //desabilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //obtem o id_professional atravẽs da session
        System.out.println("Entrando na teLa 7");
        retrieveAttributes();
        initViews();

        setSelectServiceListener();
        setAddServiceListener();
    }

    @Override
    protected void onResume(){
        super.onResume();
        //todo deve receber todos os servicos do bd e adicionar ao vetor

        try {
            idProfessional = Long.parseLong(idProfessionalString);
            System.out.println("parsing idProfissional OK!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error parsing idProfissional");
        }
        try {
            ServiceService serviceService = new ServiceService();
            servicesList = (ArrayList<ServiceDTO>) serviceService.findAllByProfessionalId(idProfessional);
            System.out.println("Baixou os servi�os");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algum problema na recupera��o da lista");
        }
//        //todo verificar se isso eh o suficiente para inicializar a lista ou precisa de mais coisas
        settingAdapters();

        //atualiza dados da listView
        myServiceAdapter.notifyDataSetChanged();
    }

    private void initViews(){
        //inicia valores dos servicos
        servicesList = new ArrayList<>();

        //inicia componentes da tela
        addServiceBT = (ImageButton) findViewById(R.id.addServiceProBTN_7);
        servicesLV = (ListView) findViewById(R.id.serviceListProLV_7);

    }
    private void settingAdapters(){
        myServiceAdapter = new MyAdapterServicesPro_7(getApplicationContext(), servicesList);
        servicesLV.setAdapter(myServiceAdapter);
    }

    private void setSelectServiceListener(){
        servicesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //todo arrumar para  receber e eenviar objetos
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String serviceToIntent = String.valueOf(parent.getItemAtPosition(position));
                Globals.service = servicesList.get(position);
                System.out.println("Id do servi�o recuperada do banco");

                System.out.println(servicesList.get(position).getId());
                Intent intent = new Intent(ProServiceListActivity_7.this, ProServiceNewActivity_6.class);
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
    private void retrieveAttributes() {
        idProfessionalString    = getIntent().getStringExtra(SessionAttributes.PROFESSIONAL_ID);
        System.out.println("retrieveAttributes OK!");
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
                intent.putExtra(SessionAttributes.PROFESSIONAL_ID, idProfessionalString);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
