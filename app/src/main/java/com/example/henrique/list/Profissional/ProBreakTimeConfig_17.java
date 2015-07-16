package com.example.henrique.list.Profissional;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.henrique.list.Adapters.MyAdapterFreeHours;
import com.example.henrique.list.R;
import com.example.henrique.list.Utilidade_Publica.DateUtilMoti;
import com.example.henrique.list.Utilidade_Publica.ResizeAnimation;
import com.example.henrique.list.Utilidade_Publica.SchedulingCalculator.FreeTimeCalculator;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by Cristor on 15/07/2015.
 */
public class ProBreakTimeConfig_17 extends ActionBarActivity {
    ResizeAnimation resizeAnimation;
    int freeHourMinutesListWidth = 90;
    boolean isHoursOpened, isMinutesOpened;

    //Iniciando DTOs
    private ProfessionalDTO professionalDTO;
    private DailyScheduleDTO dailyScheduleDTO;
    private List<PeriodDTO> periodDTOList;

    //itens de layout
    Spinner breakTimeHourSP, breakTimeMinutesSP;
    ListView listHours, listMinutes;
    ArrayAdapter myAdapterFreeHours, myAdapterFreeMinutes;
    ArrayAdapter<String> breakTimeHourAdapter, breakTimeMinutesAdapter;

    //variaveis de tempo
    int selectedHour, selectedMinutes;
    ArrayList<Integer> screenFreeHours = new ArrayList<>();
    ArrayList<Integer> screenFreeMinutes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_break_time_config_17);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configurando itens da tela
        retrieveAttributes();
        initViews();


        //configurando listeners das listas
        setSpinnerListener();
        setHoursListener();
        setMinutesListener();
    }

    private void retrieveAttributes() {
        //recebe valores da fragment anterior
        Bundle extras = getIntent().getExtras();
        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
        dailyScheduleDTO = (DailyScheduleDTO) extras.getSerializable(SessionAttributes.DAILY_SCHEDULE);
        periodDTOList = (ArrayList<PeriodDTO>) extras.getSerializable(SessionAttributes.PERIOD_LIST);
    }

    private void initViews() {
        //alimentando items do layout
        breakTimeHourSP = (Spinner) findViewById(R.id.breakTimenHour_pro17);
        breakTimeMinutesSP = (Spinner) findViewById(R.id.breakTimeMinutes_pro17);
        listHours = (ListView) findViewById(R.id.listHours_pro17);
        listMinutes = (ListView) findViewById(R.id.listMinutes_pro17);

        myAdapterFreeHours = new MyAdapterFreeHours(this, screenFreeHours, listHours);
        myAdapterFreeMinutes = new MyAdapterFreeHours(this, screenFreeMinutes, listMinutes);

         //Configurando listas de servicos/horas/minutos livre
        listHours.setAdapter(myAdapterFreeHours);
        listMinutes.setAdapter(myAdapterFreeMinutes);

        //configurando spinners
        initSpinnerAdapters();
    }

    private void initSpinnerAdapters(){
        breakTimeHourAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_hour, getResources().getStringArray(R.array.hours));
        breakTimeHourAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown_hour);
        breakTimeMinutesAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_minutes, getResources().getStringArray(R.array.minutes));
        breakTimeMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakTimeHourSP.setAdapter(breakTimeHourAdapter);
        breakTimeMinutesSP.setAdapter(breakTimeMinutesAdapter);
        setSpinnerListener();
    }

    //metodo q cria listener da lista de servicos
    public void setSpinnerListener() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //caso nenhum servico selecionado, fechar listview de horas
                if (breakTimeHourSP.getSelectedItem().toString() == "00"
                        & breakTimeMinutesSP.getSelectedItem().toString() == "00"
                        & isHoursOpened) {
                    isHoursOpened = false;
                    resizeAnimation = new ResizeAnimation(listHours, 0);
                    resizeAnimation.setDuration(400);
                    listHours.startAnimation(resizeAnimation);
                } else {
                    //adicionando horas na lista
                    screenFreeHours.clear();
                    screenFreeHours.addAll(FreeTimeCalculator.findFreeHoursByCalendar(periodDTOList, dailyScheduleDTO,
                            DateUtilMoti.convertToCalendar(breakTimeHourSP.getSelectedItem().toString(), breakTimeMinutesSP.getSelectedItem().toString())));
                    myAdapterFreeHours.notifyDataSetChanged();

                    //verifica se a lista de minutos jah esta aberta, para fecha-la
                    if (isMinutesOpened) {
                        isMinutesOpened = false;
                        resizeAnimation = new ResizeAnimation(listMinutes, 0);
                        resizeAnimation.setDuration(400);
                        listMinutes.startAnimation(resizeAnimation);
                        listHours.clearChoices();
                    }

                    //verifica se a listview de horas esta fechada, para abri-la
                    if (!isHoursOpened) {
                        isHoursOpened = true;
                        resizeAnimation = new ResizeAnimation(listHours, freeHourMinutesListWidth);
                        resizeAnimation.setDuration(400);
                        listHours.startAnimation(resizeAnimation);
                    }


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        breakTimeHourSP.setOnItemSelectedListener(listener);
        breakTimeMinutesSP.setOnItemSelectedListener(listener);
    }

    //metodo q cria listener da lista de horas
    public void setHoursListener() {
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Armazena hora selecionada
                selectedHour = (int) myAdapterFreeHours.getItem(position);

                //caso clique na hora a lista de minutos mudara de acordo com o horario dispnivel pra aquele servico
                screenFreeMinutes.clear();
                screenFreeMinutes.addAll(FreeTimeCalculator.findFreeMinutesByCalendar(selectedHour, periodDTOList,
                        DateUtilMoti.convertToCalendar(breakTimeHourSP.getSelectedItem().toString(), breakTimeMinutesSP.getSelectedItem().toString())));
                myAdapterFreeMinutes.notifyDataSetChanged();


                if (!isMinutesOpened) {
                    //redimensiona listView de horas
                    isMinutesOpened = true;
                    resizeAnimation = new ResizeAnimation(listMinutes, freeHourMinutesListWidth);
                    resizeAnimation.setDuration(600);
                    listMinutes.startAnimation(resizeAnimation);
                }
            }
        });
    }

    //metodo q cria listener da lista de minutos
    public void setMinutesListener() {
        listMinutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Armazena servico/hora/servicos selecionados
                selectedMinutes = (int) myAdapterFreeMinutes.getItem(position);
                //Chama proxima tela em activity
                initConfirmActivity();

            }
        });
    }

    private void initConfirmActivity() {
        Intent intent = new Intent(this, ProDrawerMenu_15.class);
        intent.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
        intent.putExtra(SessionAttributes.DAILY_SCHEDULE, dailyScheduleDTO);
        intent.putExtra("selectedHour", selectedHour);
        intent.putExtra("selectedMinutes", selectedMinutes);
        intent.putExtra("isEditing", false);

        startActivity(intent);
    }

    @Override
    public void onStop() {
        //esse metodo eh chamado sempre q a fragment vai para BackStack (chamando outra atividade por exemplo)
        super.onStop();
        restartValues();
    }

    public void restartValues() {
        //reinicia valores deste fragment
        isHoursOpened = false;
        isMinutesOpened = false;
        screenFreeMinutes.clear();
        screenFreeHours.clear();
        listMinutes.clearChoices();
        listHours.clearChoices();
        listHours.getLayoutParams().width = 0;
        listMinutes.getLayoutParams().width = 0;
        listHours.requestLayout();
        listHours.requestLayout();
    }
}
