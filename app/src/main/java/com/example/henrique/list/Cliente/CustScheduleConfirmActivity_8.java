package com.example.henrique.list.Cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterServicesConfirmSchedule;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import br.com.motiserver.dto.CustomerDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;


public class CustScheduleConfirmActivity_8 extends ActionBarActivity {

    String professionalName;

    //DTOs
    private CustomerDTO customerDTO;
    private ProfessionalDTO professionalDTO;
    private ArrayList<ServiceDTO> serviceDTOList;

    //Views
    ImageView imagePhoto;
    TextView textProfessionalName, textProfession, textDate ;
    TextView textInicialHour , textTotalTime, textFinalHour, textTotalPrice;
    ListView listServiceNames, listServicePrices;
    EditText streetET, numberET, cepET, complementET, districtET, cityET, stateET;

    //Variveis de data e hora
    String sDate;
    int selectedHour, selectedMinutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_schedule_confirm_8);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retriveAttributes();
        initViews();

        Bundle extras = getIntent().getExtras();
        String street = extras.getString("street");
        String number = extras.getString("number");
        String cep = extras.getString("cep");
        String complement = extras.getString("complement");
        String district = extras.getString("district");
        String city = extras.getString("city");
        String state = extras.getString("state");
        ArrayList<String> serviceNames = extras.getStringArrayList("selectedServices");
        long totalTime = extras.getLong("totalTime");
        double totalPrice = extras.getDouble("totalPrice");



        //comeca a formatar valores a serem apresentados
        //inicializando e configurando horarios
        SimpleDateFormat df = new SimpleDateFormat("HH' horas e 'mm' minutos'");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getDefault());
        df2.setTimeZone(TimeZone.getDefault());
        long inicialTime, finalTime;
        try{
            inicialTime = df2.parse(String.format("%02d",selectedHour) + ":" + String.format("%02d",selectedMinutes)).getTime();

        } catch (Exception e){
            inicialTime = 0;
            e.printStackTrace();
        }
        finalTime = inicialTime + totalTime + TimeZone.getDefault().getOffset(inicialTime);
        //iniciando adapter de nomes de servicos
        ArrayAdapter servicesNameAdapter = new MyAdapterServicesConfirmSchedule(this, serviceDTOList);
        listServiceNames.setAdapter(servicesNameAdapter);

        //iniciando adapter de precos de servicos
        //todo assim que esta classe receber intencias da classe SERVICE implementar adapter de listServicePrices

        //configura valores em suas views
        textTotalPrice.setText("R$: " + String.format("%.2f", totalPrice));
        textTotalTime.setText(df.format(totalTime));
        textFinalHour.setText(df2.format(finalTime));
        streetET.setText(street);
        numberET.setText(number);
        complementET.setText(complement);
        cepET.setText(cep);
        districtET.setText(district);
        cityET.setText(city);
        stateET.setText(state);
        //falta buscar endereco do usuario, ou profissional e precos isolados


        fillViews();
    }

    private void retriveAttributes(){
        //recebe valores da activity anterior
        Bundle extras = getIntent().getExtras();

        customerDTO = (CustomerDTO) extras.getSerializable(SessionAttributes.CUSTOMER);
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
        serviceDTOList = (ArrayList<ServiceDTO>) extras.getSerializable(SessionAttributes.SERVICE);
        sDate = extras.getString("sDate");
        selectedHour = extras.getInt("selectedHour");
        selectedMinutes = extras.getInt("selectedMinutes");
    }

    private void initViews(){
        //inicia objetos de layout
        imagePhoto = (ImageView) findViewById(R.id.photo);
        textProfessionalName = (TextView) findViewById(R.id.professionalName);
        textProfession = (TextView) findViewById(R.id.profession);
        textDate = (TextView) findViewById(R.id.date);
        listServiceNames = (ListView) findViewById(R.id.listServiceNames);
        textInicialHour = (TextView) findViewById(R.id.initialHour);
        textTotalTime = (TextView) findViewById(R.id.duration);
        textFinalHour = (TextView) findViewById(R.id.finalHour);
        listServicePrices = (ListView) findViewById(R.id.listServicePrices);
        textTotalPrice = (TextView) findViewById(R.id.totalPrice);
        streetET = (EditText) findViewById(R.id.street);
        numberET = (EditText) findViewById(R.id.number);
        cepET = (EditText) findViewById(R.id.cep);
        complementET = (EditText) findViewById(R.id.complement);
        districtET = (EditText) findViewById(R.id.district);
        cityET  = (EditText) findViewById(R.id.city);
        stateET = (EditText) findViewById(R.id.state);
    }

    private void fillViews(){
        textProfessionalName.setText(professionalDTO.getName());
        textProfession.setText(professionalDTO.getProfession().getName());
        textDate.setText(sDate);
        textInicialHour.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d",selectedMinutes));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Admininstra cliques da ActionBar
        switch (item.getItemId()) {
            case R.id.confirmButton:
                Intent confirmIntent = new Intent(this,CustDrawerMenu_10.class);
                confirmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //todo verificar se existe o agendamento. se existir alterar dados, se nao existir incluir novo no BD
                Toast.makeText(this, "Confirmado", Toast.LENGTH_SHORT).show();
                startActivity(confirmIntent);
                return true;

            case R.id.deleteButton:
                initCancelScheduleAlert();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initCancelScheduleAlert(){

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    AlertDialog popupAlert;

   //Alimenta o Alert Dialog para confirmar cancelamento
    builder.setTitle("Cancelar Agendamento");
    builder.setMessage("Voce deseja mesmo cancelar o agendamento com " + professionalName + "?");
    //define o listener dos botoes SIM / NAO do Alert Dialog
    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface arg0, int arg1){
            //caso clique sim, deve voltar para atividade anterior
            //todo verificar se existe agendamento no bd, caso exista, remover

            Intent cancelIntent = new Intent(getBaseContext(),CustDrawerMenu_10.class);
            cancelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(cancelIntent);
            Toast.makeText(getBaseContext(), "Cancelado", Toast.LENGTH_SHORT).show();
        }
    });
    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {
            //caso clique nao deve continuar na mesma atividade
        }
    });
    popupAlert = builder.create();
    popupAlert.show();
    }

}

