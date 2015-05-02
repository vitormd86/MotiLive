package com.example.henrique.list.Profissional;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.henrique.list.R;

import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;

public class ProServiceNewActivity_6 extends ActionBarActivity {

    private ProfessionalDTO professional;
    private ServiceDTO serviceDTO;

    private EditText serviceNameET;
    private EditText serviceDescriptionET;
    private EditText serviceTimeET;
    private EditText serviceValueET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_new_6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO
        professional = null;

        serviceNameET = (EditText) findViewById(R.id.serviceNameProET_6);
        serviceDescriptionET = (EditText) findViewById(R.id.serviceDescriptionProET_6);
        serviceTimeET = (EditText) findViewById(R.id.serviceTimeProET_6);
        serviceValueET = (EditText) findViewById(R.id.serviceValueProET_6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.confirmButton:
                // location found
                confirmRegistration();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void confirmRegistration() {
        serviceDTO = getServiceDTO();


    }

    private ServiceDTO getServiceDTO() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setDelayTolerance(null);
        serviceDTO.setDescription(serviceDescriptionET.getText().toString());
        serviceDTO.setName(serviceNameET.getText().toString());
        serviceDTO.setProfessional(professional);
        // TODO
        serviceDTO.setTime(null);
        serviceDTO.setValue(null);
        return serviceDTO;
    }
}
