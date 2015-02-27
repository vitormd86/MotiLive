package com.example.henrique.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrique.list.Adapters.MyAdapterFreeTime;
import com.example.henrique.list.Adapters.MyAdapterServiceTypes;
import com.example.henrique.list.DAO.PessoaDAO;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import java.util.ArrayList;

public class HourConsult extends ActionBarActivity {


    PessoaDAO pessoaDAO = new PessoaDAO(this);
    ArrayList<String> freeHours = new ArrayList<>();
    ArrayList<String> freeMinutes = new ArrayList<>();
    ResizeAnimation resizeAnimation;
    boolean isHoursOpened;
    boolean isMinutesOpened;
    int freeHourMinutesWidth = 90;
    String selectedHour;
    String selectedMinutes;
    String date;
    String professionalName;
    String totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_consult);
        Intent activityThatCalled = getIntent();

        //Deve buscar do banco ou da intent
        professionalName = activityThatCalled.getStringExtra("Escolhas");
        //try {
        //    pessoaDAO.execute().get();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}

        //try {
        //   professionalName = pessoaDAO.getPessoa(2);
        //}  catch (SQLException e1) {
        //e1.printStackTrace();
        //}

        date = activityThatCalled.getStringExtra("Date");
        String occupation = "Massagista";
        String [] services = {"Serviço 1" , "Serviço2" , "Serviço3" , "Serviço4" , "Serviço5" , "Serviço6","Servico7", "Servico8", "servico8"};



        //adicionando horas.... deve receber do banco de dados e tratar em seguida
        freeHours.add("1");
        freeHours.add("2");
        freeHours.add("3");
        freeMinutes.add("10");
        freeMinutes.add("20");
        freeMinutes.add("30");

        //apontando items do layout
        TextView textProfessionalName = (TextView) findViewById(R.id.professionalName);
        TextView textProfession = (TextView) findViewById(R.id.profession);
        final ListView listHours = (ListView) findViewById(R.id.listHours);
        final ListView listMinutes = (ListView) findViewById(R.id.listMinutes);
        final ListView listServices = (ListView) findViewById(R.id.listServices);
        final ArrayAdapter myAdapterServiceTypes = new MyAdapterServiceTypes(this, services);
        final ArrayAdapter myAdapterFreeHours = new MyAdapterFreeTime(getBaseContext(), freeHours, listHours);
        final ArrayAdapter myAdapterFreeMinutes = new MyAdapterFreeTime(getBaseContext(), freeMinutes, listHours);

        //Configura as variaveis do cabecalho
        textProfessionalName.setText(professionalName);
        textProfession.setText(occupation);

        //Configurando lista de horas livre
        listHours.setAdapter(myAdapterFreeHours);
        listMinutes.setAdapter(myAdapterFreeMinutes);

        //Configuraado lista de servicos
        listServices.setAdapter(myAdapterServiceTypes);
        listServices.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //criando listeners dos servicos
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //caso clique no servico o horario mudara de acordo com o horario dispnivel pra aquele servico
                freeHours.clear();
                freeHours.add("4");
                freeHours.add("5");
                myAdapterFreeHours.notifyDataSetChanged();

                //verifica se a listview de horas ja esta aberta
                if(!isHoursOpened) {
                    //redimensiona listView de horas
                    isHoursOpened = true;
                    resizeAnimation = new ResizeAnimation(listHours, freeHourMinutesWidth);
                    resizeAnimation.setDuration(600);
                    listHours.startAnimation(resizeAnimation);
                }

                SparseBooleanArray checked = listServices.getCheckedItemPositions();
                TextView textServico = (TextView) view.findViewById(R.id.serviceLength);
                totalTime = "";

                for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
                    if (checked.get(i)) {
                        totalTime = totalTime + " " + textServico.getText();
                        //TextView tv = (TextView) myAdapterServiceTypes.getView(i, findViewById(R.id.serviceLength), (ViewGroup) listServices);
                        //totalTime = totalTime + tv.getText();
                    }
                }
            }
        });

        //criando listeners do hourlist
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Armazena hora selecionada
                selectedHour = "" + myAdapterFreeHours.getItem(position);

                //caso clique na hora a lista de minutos mudara de acordo com o horario dispnivel pra aquele servico
                freeMinutes.clear();
                freeMinutes.add("00");
                freeMinutes.add("05");
                freeMinutes.add("15");
                freeMinutes.add("40");
                freeMinutes.add("50");
                myAdapterFreeMinutes.notifyDataSetChanged();

                if(!isMinutesOpened) {
                    //redimensiona listView de horas]
                    isMinutesOpened = true;
                    resizeAnimation = new ResizeAnimation(listMinutes, freeHourMinutesWidth);
                    resizeAnimation.setDuration(600);
                    listMinutes.startAnimation(resizeAnimation);
                }
            }
        });


        //criando listeners do listMinutes
        listMinutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedMinutes = "" + myAdapterFreeMinutes.getItem(position);
                //Gera um alerta, para confirmar o agendamento
                AlertDialog.Builder builder = new AlertDialog.Builder(HourConsult.this);
                AlertDialog confirmAlert;

                //Determinando campo de servicos selecionados
                SparseBooleanArray checked = listServices.getCheckedItemPositions();
                String selectedServices = "";

                for (int i = 0; i < listServices.getAdapter().getCount(); i++) {
                    if (checked.get(i)) {

                        selectedServices = selectedServices + " " + myAdapterServiceTypes.getItem(i);
                        //TextView tv = (TextView) myAdapterServiceTypes.getView(i, findViewById(R.id.serviceLength), (ViewGroup) listServices);
                        //totalTime = totalTime + tv.getText();
                    }
                }
                //Determinando campo de hora inicial


                //Alimenta o Alert Dialog com informacoes do agendameto a ser confirmado
                builder.setTitle("Confirmar agendamento?");
                builder.setMessage("Profissional: " + professionalName +
                        "\nServico(s): " + selectedServices +
                        "\nDia: " + date +
                        "\nInicio: " + selectedHour + ":" + selectedMinutes + "h" +
                        "\nPeríodo: " + totalTime +
                        "\nFim: " + "02:00h" +
                        "\nValor: " + "R$99,99");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){
                        //OPCAO SIM = confirma agendamento
                        //DEVE ACRESCENTAR OS DADOS NO BD
                        //DEVE VERIFICAR SE TODAS AS LISTAS ESTAO SELECIONADAS

                        //Redireciona usuario para a tela inicial de agendamento
                        Intent endOfScheduleActivity = new Intent(HourConsult.this,MainActivity.class);
                        startActivity(endOfScheduleActivity);

                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){

                        //REINICIA ListMinutes/ListHours Menu
                        resizeAnimation = new ResizeAnimation(listHours, 0);
                        listHours.startAnimation(resizeAnimation);
                        resizeAnimation = new ResizeAnimation(listMinutes, 0);
                        listMinutes.startAnimation(resizeAnimation);

                        isHoursOpened = false;
                        isMinutesOpened = false;

                        listMinutes.clearChoices();
                        listHours.clearChoices();
                        listServices.clearChoices();
                        Toast.makeText(getBaseContext(), "Nao", Toast.LENGTH_SHORT).show();
                    }
                });
                confirmAlert = builder.create();
                confirmAlert.show();

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setServicesListener(){

    }
}
