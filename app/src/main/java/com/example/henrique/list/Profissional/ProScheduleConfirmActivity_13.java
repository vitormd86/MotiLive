package com.example.henrique.list.Profissional;

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

import com.example.henrique.list.Adapters.ServicesNameAdapter;
import com.example.henrique.list.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by htamashiro on 3/21/15.
 */
public class ProScheduleConfirmActivity_13 extends ActionBarActivity {
    String clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_confirm_13);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recebe valores da activity anterior
        Bundle extras = getIntent().getExtras();
        clientName = extras.getString("clientName");
        String street = extras.getString("street");
        String number = extras.getString("number");
        String cep = extras.getString("cep");
        String complement = extras.getString("complement");
        String district = extras.getString("district");
        String city = extras.getString("city");
        String state = extras.getString("state");
        ArrayList<String> serviceNames = extras.getStringArrayList("selectedServices");
        String sDate = extras.getString("sDate");
        int selectedHour = extras.getInt("selectedHour");
        int selectedMinutes = extras.getInt("selectedMinutes");
        long totalTime = extras.getLong("totalTime");
        double totalPrice = extras.getDouble("totalPrice");


        //inicia objetos de layout
        ImageView imagePhoto = (ImageView) findViewById(R.id.photo);
        TextView textClientName = (TextView) findViewById(R.id.clientName);
        TextView textDate = (TextView) findViewById(R.id.date);
        ListView listServiceNames = (ListView) findViewById(R.id.listServiceNames);
        TextView textInicialHour = (TextView) findViewById(R.id.initialHour);
        TextView textTotalTime = (TextView) findViewById(R.id.duration);
        TextView textFinalHour = (TextView) findViewById(R.id.finalHour);
        ListView listServicePrices = (ListView) findViewById(R.id.listServicePrices);
        TextView textTotalPrice = (TextView) findViewById(R.id.totalPrice);
        EditText streetET = (EditText) findViewById(R.id.street);
        EditText numberET = (EditText) findViewById(R.id.number);
        EditText cepET = (EditText) findViewById(R.id.cep);
        EditText complementET = (EditText) findViewById(R.id.complement);
        EditText districtET = (EditText) findViewById(R.id.district);
        EditText cityET  = (EditText) findViewById(R.id.city);
        EditText stateET = (EditText) findViewById(R.id.state);


        //comeca a formatar valores a serem apresentados
        //inicializando e configurando horarios
        SimpleDateFormat df = new SimpleDateFormat("HH' horas e 'mm' minutos'");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getDefault());
        df2.setTimeZone(TimeZone.getDefault());
        long inicialTime, finalTime;
        try {
            inicialTime = df2.parse(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinutes)).getTime();

        } catch (Exception e) {
            inicialTime = 0;
            e.printStackTrace();
        }
        finalTime = inicialTime + totalTime + TimeZone.getDefault().getOffset(inicialTime);
        //iniciando adapter de nomes de servicos
        ArrayAdapter servicesNameAdapter = new ServicesNameAdapter(this, serviceNames);
        listServiceNames.setAdapter(servicesNameAdapter);

        //iniciando adapter de precos de servicos
        //todo assim que esta classe receber intencias da classe SERVICE implementar adapter de listServicePrices

        //configura valores em suas views
        textClientName.setText(clientName);
        textDate.setText(sDate);
        textTotalPrice.setText("R$: " + String.format("%.2f", totalPrice));
        textInicialHour.setText(df2.format(inicialTime));
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                Intent confirmIntent = new Intent(this, ProDrawerMenu_15.class);
                confirmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //todo verificar se existe o agendamento. se existir alterar dados, se nao existir incluir novo no BD
                Toast.makeText(this, "Cliente adicionado", Toast.LENGTH_SHORT).show();
                startActivity(confirmIntent);
                return true;

            case R.id.deleteButton:
                initCancelScheduleAlert();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initCancelScheduleAlert() {
        //este metodo gera um alerta para o cancelamento
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog popupAlert;

        //Alimenta o Alert Dialog para confirmar cancelamento
        builder.setTitle("Cancelar Agendamento");
        builder.setMessage("Voce deseja mesmo cancelar o agendamento com " + clientName + "?");
        //define o listener dos botoes SIM / NAO do Alert Dialog
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //caso clique sim, deve voltar para atividade anterior
                //todo verificar se existe agendamento no bd, caso exista, remover

                Intent cancelIntent = new Intent(getBaseContext(), ProDrawerMenu_15.class);
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