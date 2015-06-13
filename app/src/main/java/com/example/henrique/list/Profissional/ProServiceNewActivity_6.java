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
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Service.ServiceService;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;
import com.example.henrique.list.Utilidade_Publica.DataValidatorUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;

import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;


public class ProServiceNewActivity_6 extends ActionBarActivity {

    //EditText
    EditText serviceNameET, serviceDescriptionET, sessionValueET;
    String serviceName, serviceDescription, sessionValueString;
    BigDecimal sessionValue;
    //Spinners
    Spinner sessionHoursSP, sessionMinutesSP;
    String sessionHours, sessionMinutes; // fazer  atrbiuições
    int sessionHoursInt, sessionMinutesInt; // fazer  atrbiuições
   //Objects
    Calendar calendar;
    //Seriazables
    ServiceDTO serviceDTO;
    ProfessionalDTO professionalDTO;
    //booleans
    boolean isEditing;
    boolean executaJSON;
    //Service
    private ProfessionalService professionalService;
    //Bundle
    String sProfessional_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service_new_6);

        //verifica se esta no modo de edicao ou de novo servico
        isEditing = isEditingService();
        if (!isEditing) {
            try {
                professionalService = new ProfessionalService();
                professionalDTO = professionalService.find((long) 6); //TODO depois.. recuperar id da tela anterior.
                if (professionalDTO == null)
                {
                    System.out.println("Professional ta vindo nulo!");
                }else{
                    System.out.println(professionalDTO.getAddressNumber());
                    System.out.println("Profissional recuperado com sucesso");

                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        //inicializa componentes da tela
        initViews();
        initSpinnerAdapters();

        //configurando BackNavigation button
        if (isEditing) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            try {
                serviceDTO = (ServiceDTO) getIntent().getSerializableExtra(SessionAttributes.SERVICE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            serviceDescriptionET.setText(serviceDTO.getDescription());
            serviceNameET.setText(serviceDTO.getName());
            System.out.println(serviceDTO.getName());
            sessionValueET.setText(serviceDTO.getValue().toString());
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ProServiceNewActivity_6.this, ProServiceListActivity_7.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        // Admininstra cliques da ActionBar

        switch (item.getItemId()) {
            case R.id.confirmButton:
                executaJSON = true;
                serviceDTO = new ServiceDTO();
                if (isEditingService()) {
                    //todo   agora quem vai tratar  edição e adição é o proprio back... soh precisamos da isEditing para sabermos como vamos tratar a tela


                } else {
                    //todo deve adicionar servico no banco
                    serviceDTO = new ServiceDTO();

                    initVariables();
                    System.out.println("InitVariables OK");
                    if (validatefields()) {
                        System.out.println("validateFields OK");
                        serviceDTO.setName(serviceName);
                        serviceDTO.setDescription(serviceDescription);
                        serviceDTO.setValue(sessionValue);
                        System.out.println(serviceDTO.getValue());
                        try {
                            serviceDTO.setTime(calendar);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        serviceDTO.setProfessional(professionalDTO);
                        System.out.println("filling serviceDTO OK");

                        try {
                            ServiceService serviceservice = new ServiceService();
                            serviceDTO = serviceservice.save(serviceDTO);
                            System.out.println("Salvou");

                            sProfessional_id = professionalDTO.getId().toString();
                            System.out.println(sProfessional_id);
                            Intent createAccountIntent = new Intent(ProServiceNewActivity_6.this, ProServiceListActivity_7.class);
                            createAccountIntent.putExtra(SessionAttributes.PROFESSIONAL_ID, sProfessional_id);
                            startActivity(createAccountIntent);
                        } catch (ServiceException e) {
                            e.printStackTrace();
                            System.out.println("Não Salvou");
                        }
                    }else{
                        System.out.println("Falha na validação fields");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        if (isEditing) {
            inflater.inflate(R.menu.menu_confirm_delete, menu);
        } else {
            inflater.inflate(R.menu.menu_confirm, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void initVariables() {

        //inicializando BigDecimal

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        //pegando os dados do Edit Texts

        serviceName = serviceNameET.getText().toString();
        serviceDescription = serviceDescriptionET.getText().toString();
        sessionValueString = sessionValueET.getText().toString();

        //pegando os dados dos Spinners

        sessionHours = sessionHoursSP.getSelectedItem().toString();
        sessionMinutes = sessionMinutesSP.getSelectedItem().toString();

        try {
            sessionHoursInt = Integer.parseInt(sessionHours);
            sessionMinutesInt = Integer.parseInt(sessionMinutes);
            System.out.println("Conseguiu fazer parsing dos Ints ");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, sessionHoursInt );
        System.out.println(sessionHoursInt);
        calendar.set(Calendar.MINUTE, sessionMinutesInt);
        System.out.println(sessionMinutesInt);

        try {
            sessionValue = (BigDecimal) decimalFormat.parse(sessionValueString);
            System.out.println("Conseguiu fazer parsing do BigDecilmal");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Não conseguiu fazer parsing do BigDecilmal");
        }
    }

    private boolean validatefields() {
        System.out.println("Entrou em validdate fields");
        if (!DataValidatorUtil.isValidTextWithSpace(serviceName)) {
            serviceNameET.setError("O nome não pode conter numeros");
            executaJSON = false;
        } else {
            if (serviceNameET.getError() != null)
                serviceNameET.setError(null);
        }

        if (!DataValidatorUtil.isValidTextWithSpace(serviceDescription)) {
            serviceDescriptionET.setError("A descrição pode conter qualquer coisa!");
            executaJSON = false;
        } else {
            if (serviceDescriptionET.getError() != null)
                serviceDescriptionET.setError(null);
        }

        if (!DataValidatorUtil.isValidBigDecimal(sessionValueString)) {
            sessionValueET.setError("A session Value só pode conter números ");
            executaJSON = false;
        } else {
            if (sessionValueET.getError() != null)
                sessionValueET.setError(null);
        }
        System.out.println("Passou validação Big Decimal");

        return executaJSON;
    }

    private void initSpinnerAdapters() {

        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hours, R.layout.view_spinner_text_hour);
        hourAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown_hour);
        sessionHoursSP.setAdapter(hourAdapter);

        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_dropdown_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionMinutesSP.setAdapter(minutesAdapter);
    }

    private boolean isEditingService() {
        boolean isEditing = false;
        return getIntent().getBooleanExtra("isEditing", false)|| false;
    }

    private void initViews() {
        //init Spinners
        sessionHoursSP = (Spinner) findViewById(R.id.sessionHoursProSP_6);
        sessionMinutesSP = (Spinner) findViewById(R.id.sessionMinutesProSP_6);
        //init Edit Texts
        serviceNameET = (EditText) findViewById(R.id.serviceNameETPro_6);
        serviceDescriptionET = (EditText) findViewById(R.id.serviceDescriptionProET_6);
        sessionValueET = (EditText) findViewById(R.id.sessionValueProET_6);

    }
}
