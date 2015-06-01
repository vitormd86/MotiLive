package com.example.henrique.list.Profissional;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;
import com.example.henrique.list.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Cristor on 14/04/2015.
 */
public class ProScheduleConfig_8 extends ActionBarActivity {

    Spinner expedientStartHourSP, expedientStartMinutesSP, expedientEndHourSP, expedientEndMinutesSP;
    Spinner breakStartHourSP, breakStartMinutesSP, breakEndHourSP, breakEndMinutesSP;
    Spinner intervalBetweenHour, intervalBeteewMinutes;
    RadioGroup breakTimeRadioGroup;
    CheckBox sunCB, monCB, tueCB, wedCB, thuCB, friCB, satCB;

    CalendarPickerView screenCalendar;
    Calendar initDate, endDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_config_8);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configurando views do layout
        initViews();
        //configurando calendario
        initCalendar();
        //configurando adapters dos spinners
        initSpinnersAdapters();
        //configurando listeners
        initBreakTimeRadioListeners();
        initCheckBoxListeners();


    }

    private void initViews(){
        //este metodo inicializa as views
        expedientStartHourSP = (Spinner) findViewById(R.id.expedientStartHourSP_pro8);
        expedientStartMinutesSP = (Spinner) findViewById(R.id.expedientStartMinutesSP_pro8);
        expedientEndHourSP = (Spinner) findViewById(R.id.expedientEndHourSP_pro8);
        expedientEndMinutesSP = (Spinner) findViewById(R.id.expedientEndMinutesSP_pro8);
        breakStartHourSP = (Spinner) findViewById(R.id.breakTimeStartHour);
        breakStartMinutesSP = (Spinner) findViewById(R.id.breakTimeStartMinutes);
        breakEndHourSP = (Spinner) findViewById(R.id.breakTimeEndHour);
        breakEndMinutesSP = (Spinner) findViewById(R.id.breakTimeEndMinutes);
        intervalBetweenHour = (Spinner) findViewById(R.id.intervalBetweenHour);
        intervalBeteewMinutes = (Spinner) findViewById(R.id.intervalBetweenMinutes);

        sunCB = (CheckBox) findViewById(R.id.checkboxSun);
        monCB = (CheckBox) findViewById(R.id.checkboxMon);
        tueCB = (CheckBox) findViewById(R.id.checkboxTue);
        wedCB = (CheckBox) findViewById(R.id.checkboxWed);
        thuCB = (CheckBox) findViewById(R.id.checkboxThu);
        friCB = (CheckBox) findViewById(R.id.checkboxFri);
        satCB = (CheckBox) findViewById(R.id.checkboxSat);

        breakTimeRadioGroup = (RadioGroup) findViewById(R.id.breakTimeRadioGroup);

        screenCalendar = (CalendarPickerView) findViewById(R.id.calendar_view);
    }

    //este metodo configura os adapters de todos spinners
    public void initSpinnersAdapters(){
       //incluindo mascara nos arrays
        List<String> hoursArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hours)));
        for (int i =0; i<hoursArray.size(); i++){
            hoursArray.set(i, hoursArray.get(i) +" :");
        }
        List<String> minutesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.minutes)));
        for (int i =0; i<minutesArray.size(); i++){
            minutesArray.set(i, " " + minutesArray.get(i));
        }
        //configurando adapters e spinners
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_hour, hoursArray);
        hourAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown_hour);
        expedientStartHourSP.setAdapter(hourAdapter);
        expedientStartHourSP.setSelection(8);
        expedientEndHourSP.setAdapter(hourAdapter);
        expedientEndHourSP.setSelection(17);
        breakStartHourSP.setAdapter(hourAdapter);
        breakStartHourSP.setSelection(12);
        breakEndHourSP.setAdapter(hourAdapter);
        breakEndHourSP.setSelection(13);
        intervalBetweenHour.setAdapter(hourAdapter);
        intervalBetweenHour.setSelection(0);

        //adapter de array de minutos
        ArrayAdapter<String> minutesAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_minutes, minutesArray);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expedientStartMinutesSP.setAdapter(minutesAdapter);
        expedientEndMinutesSP.setAdapter(minutesAdapter);
        breakStartMinutesSP.setAdapter(minutesAdapter);
        breakEndMinutesSP.setAdapter(minutesAdapter);
        intervalBeteewMinutes.setAdapter(minutesAdapter);
        intervalBeteewMinutes.setSelection(4);



    }

    private void initCalendar(){
        //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        initDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 3);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

    }

    public void initBreakTimeRadioListeners(){
        breakTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.breakTimeRadioYes:
                        breakStartHourSP.setEnabled(true);
                        breakStartHourSP.setClickable(true);
                        breakStartMinutesSP.setEnabled(true);
                        breakStartMinutesSP.setClickable(true);
                        breakEndHourSP.setEnabled(true);
                        breakEndHourSP.setClickable(true);
                        breakEndMinutesSP.setEnabled(true);
                        breakEndMinutesSP.setClickable(true);

                        break;
                    case R.id.breakTimeRadioNo:
                        breakStartHourSP.setEnabled(false);
                        breakStartHourSP.setClickable(false);
                        breakStartMinutesSP.setEnabled(false);
                        breakStartMinutesSP.setClickable(false);
                        breakEndHourSP.setEnabled(false);
                        breakEndHourSP.setClickable(false);
                        breakEndMinutesSP.setEnabled(false);
                        breakEndMinutesSP.setClickable(false);
                        break;
                }

            }
        });
    }

    public void initCheckBoxListeners(){
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView==sunCB)
                    selectWeekDays(isChecked, 1);
                if(buttonView==monCB)
                    selectWeekDays(isChecked, 2);
                if(buttonView==tueCB)
                    selectWeekDays(isChecked, 3);
                if(buttonView==wedCB)
                    selectWeekDays(isChecked, 4);
                if(buttonView==thuCB)
                    selectWeekDays(isChecked, 5);
                if(buttonView==friCB)
                    selectWeekDays(isChecked, 6);
                if(buttonView==satCB)
                    selectWeekDays(isChecked, 7);

            }
        };
        sunCB.setOnCheckedChangeListener(checkBoxListener);
        monCB.setOnCheckedChangeListener(checkBoxListener);
        tueCB.setOnCheckedChangeListener(checkBoxListener);
        wedCB.setOnCheckedChangeListener(checkBoxListener);
        thuCB.setOnCheckedChangeListener(checkBoxListener);
        friCB.setOnCheckedChangeListener(checkBoxListener);
        satCB.setOnCheckedChangeListener(checkBoxListener);
    }

    public void selectWeekDays(boolean isChecked, int dayOfWeek){
        //recebe qual semana deve ser adicionada/removida do calendario
        ArrayList<Date> repeatDates = repeatDaysOfWeek(initDate.getTime(), endDate.getTime(), dayOfWeek);
        if(isChecked){
            //Toast.makeText(this, "Clicado = " + dayOfWeek + " isChecked = 1", Toast.LENGTH_SHORT).show();
            for (Date toCheckDate : repeatDates){
                if(!screenCalendar.isDateSelected(toCheckDate)){
                    screenCalendar.selectDate(toCheckDate);
                }
            }
            //screenCalendar.selectDate(initDate.getTime());
        } if (!isChecked){
            for (Date toUncheckDate : repeatDates){
                screenCalendar.unselectDate(toUncheckDate);
            }
            //Toast.makeText(this, "Clicado = " + dayOfWeek + " isChecked = 0", Toast.LENGTH_SHORT).show();
            //screenCalendar.unselectDate(initDate.getTime());
        }
    }

    public ArrayList<Date> repeatDaysOfWeek (Date initDate, Date endDate, int targetDayOfWeek){
        //este metodo gera uma array de repeticao de dias de semana, dentro de um limite minimo e maximo de data.

        ArrayList<Date> selectedDaysOfWeeK = new ArrayList<>();

        //inicializando calendarios
        Calendar dateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();
        dateCal.setTime(initDate);
        endDateCal.setTime(endDate);
        //zerando horario dos calendarios
        CalendarPickerView.setMidnight(dateCal);
        CalendarPickerView.setMidnight(endDateCal);

        int initDayOfWeek = dateCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < 7; i++){
            //laco para encontrar o dia da semana requisitado como argumento do metodo
            if (initDayOfWeek == targetDayOfWeek)
                break;
            dateCal.add(Calendar.DAY_OF_WEEK, 1);
            initDayOfWeek = dateCal.get(Calendar.DAY_OF_WEEK);
        }

        while(dateCal.getTime().getTime() < endDateCal.getTime().getTime()){
            //adiciona todos os dias escolhidos dentro da array que sera retornada
            selectedDaysOfWeeK.add(dateCal.getTime());
            dateCal.add(Calendar.DAY_OF_WEEK, 7);
        }

        //Toast.makeText(this, "Igualou no dia " + initDayOfWeek + ". Date = " + initDateCal, Toast.LENGTH_SHORT).show();

        return selectedDaysOfWeeK;
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

                Toast.makeText(this, "Botao confirm clicado.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
