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
import com.example.henrique.list.Service.ServiceService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.Utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by Cristor on 01/05/2015.
 */
public class ProServiceNewActivity_6 extends ActionBarActivity {
    //EditText
    EditText serviceNameET, serviceDescriptionET, sessionValueET;
    String serviceName, serviceDescription, sessionValueString;
    BigDecimal  sessionValue;
    //Spinners
    Spinner sessionHoursSP, sessionMinutesSP;
    String sessionHours, sessionMinutes;

    //Seriazables
    ServiceDTO serviceDTO;
    //booleans
    Boolean isEditing;
    boolean executaJSON;


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
        //init Spinners
        sessionHoursSP =        (Spinner) findViewById(R.id.sessionHoursProSP_6);
        sessionMinutesSP =      (Spinner) findViewById(R.id.sessionMinutesProSP_6);
        //init Edit Texts
        serviceNameET =         (EditText) findViewById(R.id.serviceNameETPro_6);
        serviceDescriptionET =  (EditText) findViewById(R.id.serviceDescriptionProET_6);
        sessionValueET =        (EditText) findViewById(R.id.sessionValueProET_6);

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
                executaJSON = true;
                if(isEditingService()){
                    //todo   agora quem vai tratar  edição e adição é o proprio back... soh precisamos da isEditing para sabermos como vamos tratar a tela

                } else {
                    //todo deve adicionar servico no banco
                    initVariables();
                    if(validatefields()){
                        ServiceService serviceservice = new ServiceService();
                        if (executaJSON) {
                            serviceDTO.setName(serviceName);
                            serviceDTO.setDescription(serviceDescription);
                            serviceDTO.setValue(sessionValue);
                            try {
                                serviceDTO = serviceservice.save(serviceDTO);
                                System.out.println("Salvou");
                                startActivity(intent);
                            } catch (ServiceException e) {
                                e.printStackTrace();
                                System.out.println("Não Salvou");
                            }
                        } else {
                            System.out.println("");
                        }
                    }


                    intent.putExtra("service", serviceNameET.getText().toString());
                }
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

    private void initVariables() {

        //inicializando BigDecimal
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        serviceName = serviceNameET.getText().toString();
        serviceDescription = serviceDescriptionET.getText().toString();
        sessionValueString = sessionValueET.getText().toString();
        try {
            sessionValue = (BigDecimal) decimalFormat.parse(sessionValueString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private boolean validatefields() {

        if (!Utility.isValidTextWithSpace(serviceName)) {
            serviceNameET.setError("O nome não pode conter numeros");
            executaJSON = false;
        }else{
            if(serviceNameET.getError() != null)
                serviceNameET.setError(null);
        }

        if (!Utility.isValidTextWithSpace(serviceDescription)) {
            serviceDescriptionET.setError("A descrição pode conter qualquer coisa!");
            executaJSON = false;
        }else{
            if(serviceDescriptionET.getError() != null)
                serviceDescriptionET.setError(null);
        }

        if (!Utility.isValidBigDecimal(sessionValueString)) {
            sessionValueET.setError("A session Value só pode conter números ");
            executaJSON = false;
        }else{
            if(sessionValueET.getError() != null)
                sessionValueET.setError(null);
        }
        if(executaJSON)
            return true;
        else
            return false;
    }
}