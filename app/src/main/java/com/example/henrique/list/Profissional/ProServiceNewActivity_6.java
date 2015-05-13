package com.example.henrique.list.Profissional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.henrique.list.R;

import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by Cristor on 01/05/2015.
 */
public class ProServiceNewActivity_6 extends ActionBarActivity {

    EditText serviceNameET, serviceDescriptionET, sessionValueET;
    Spinner sessionHoursSP, sessionMinutesSP;
    ServiceDTO serviceDTO;

    Boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_new_6);

        //verifica se esta no modo de edicao ou de novo servico
        isEditing = isEditingService();

        //inicializa componentes da tela
        initViews();
        initSpinnerAdapters();

        //configurando BackNavigation button
        if(isEditing){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void initViews(){
        sessionHoursSP = (Spinner) findViewById(R.id.sessionHours);
        sessionMinutesSP = (Spinner) findViewById(R.id.sessionMinutes);
        serviceNameET = (EditText) findViewById(R.id.serviceName);
        serviceDescriptionET = (EditText) findViewById(R.id.serviceDescription);
        sessionValueET = (EditText) findViewById(R.id.sessionValue);

        if (isEditing){
            Bundle extras = getIntent().getExtras();
            serviceNameET.setText(extras.getString("service"));
        }
    }

    private void initSpinnerAdapters(){
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hours, R.layout.view_spinner_text_hour);
        hourAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown_hour);
        sessionHoursSP.setAdapter(hourAdapter);

        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_dropdown_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionMinutesSP.setAdapter(minutesAdapter);
    }

    private boolean isEditingService(){
        boolean isEditing = false;
        if(getIntent().getBooleanExtra("isEditing", false)){
            isEditing = true;
        }
        return isEditing;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        if(isEditing){
            inflater.inflate(R.menu.menu_confirm_delete, menu);
        } else {
            inflater.inflate(R.menu.menu_confirm, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ProServiceNewActivity_6.this, ProServiceListActivity_7.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                if(isEditingService()){
                    //todo deve alterar servico selecionado no banco
                } else {
                    //todo deve adicionar servico no banco
                    intent.putExtra("service", serviceNameET.getText().toString());
                }
                Toast.makeText(this, serviceNameET.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                return true;
            case R.id.deleteButton:
                //todo deve apagar servico escolhido do banco
                Toast.makeText(this, "Deletar Serviço", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
