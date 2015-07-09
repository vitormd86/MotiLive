package com.example.henrique.list.Profissional;

import android.content.Intent;
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

import com.example.henrique.list.R;
import com.example.henrique.list.Service.DailyScheduleService;
import com.example.henrique.list.Service.ProfessionalService;
import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;
import com.example.henrique.list.Utilidade_Publica.ServiceException;
import com.example.henrique.list.Utilidade_Publica.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import br.com.motiserver.dto.BreakDTO;
import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.ProfessionalDTO;
import br.com.motiserver.util.constants.Status;

/** Tela de configuracao de agenda do profissional */

public class ProScheduleConfig_8 extends ActionBarActivity {

    Bundle extras;

    Spinner expedientStartHourSP, expedientStartMinutesSP, expedientEndHourSP, expedientEndMinutesSP;
    Spinner breakStartHourSP, breakStartMinutesSP, breakEndHourSP, breakEndMinutesSP;
    Spinner intervalBetweenHourSP, intervalBetweenMinutesSP;
    RadioGroup breakTimeRadioGroup;
    CheckBox sunCB, monCB, tueCB, wedCB, thuCB, friCB, satCB;
    ArrayAdapter<String> hourAdapter, minutesAdapter;

    CalendarPickerView screenCalendar;
    Calendar initDate, endDate;

    Calendar expedientStartCal, expedientEndCal, breakTimeStartCal, breakTimeEndCal;
    Calendar timeBetweenSession;

    List<Date> selectedDates;

    ProfessionalDTO professionalDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_config_8);

        //Habilitando BackNavigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configurando views do layout
        retrievingAttributes();
        Toast.makeText(this, "" + professionalDTO.getName(), Toast.LENGTH_SHORT).show();
        initViews();
        //configurando calendario
        initCalendar();
        //configurando adapters dos spinners
        initSpinnersAdapters();
        //configurando listeners
        initBreakTimeRadioListeners();
        initCheckBoxListeners();
        fillViews();
    }

    private void retrievingAttributes(){
        extras = getIntent().getExtras();

        professionalDTO = (ProfessionalDTO) extras.getSerializable(SessionAttributes.PROFESSIONAL);
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
        intervalBetweenHourSP = (Spinner) findViewById(R.id.intervalBetweenHour);
        intervalBetweenMinutesSP = (Spinner) findViewById(R.id.intervalBetweenMinutes);

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

    public void fillViews(){
        expedientStartHourSP.setSelection(8);
        expedientEndHourSP.setSelection(17);
        breakStartHourSP.setSelection(12);
        breakEndHourSP.setSelection(13);
        intervalBetweenHourSP.setSelection(0);
        intervalBetweenMinutesSP.setSelection(4);

        if(isEditing()){
            //se estiver em modo de edicao, buscara dados do servicos para alimentar campos
            DailyScheduleService dailyScheduleService = new DailyScheduleService();
            DailyScheduleDTO dailyScheduleDTO;
            try {
                dailyScheduleDTO = dailyScheduleService.findAllByProfessionalId(professionalDTO.getId()).get(0);
            } catch (ServiceException ex) {
                dailyScheduleDTO = new DailyScheduleDTO();
                ex.printStackTrace();
                System.out.println("Erro ao buscar dailySchedule.");
            }
            Calendar expedientStartCal = Calendar.getInstance(TimeZone.getDefault());
            Calendar expedientEndCal = Calendar.getInstance(TimeZone.getDefault());
            Calendar breakStartCal = Calendar.getInstance(TimeZone.getDefault());
            Calendar breakEndCal = Calendar.getInstance(TimeZone.getDefault());
            Calendar intervalBetweenCal = Calendar.getInstance(TimeZone.getDefault());

            expedientStartCal.setTime(dailyScheduleDTO.getStartTime().getTime());
            expedientEndCal.setTime(dailyScheduleDTO.getEndTime().getTime());
            //breakStartCal.setTime();
            //breakEndCal.setTime();
            //intervalBetweenCal.setTime(professionalDTO.getSessionInterval().getTime());

            System.out.println(String.format("%02d", expedientStartCal.get(Calendar.HOUR_OF_DAY)));
            expedientStartHourSP.setSelection(hourAdapter.getPosition(String.format("%02d", expedientStartCal.get(Calendar.HOUR_OF_DAY))));
            expedientStartMinutesSP.setSelection(minutesAdapter.getPosition(String.format("%02d", expedientStartCal.get(Calendar.MINUTE))));
            expedientEndHourSP.setSelection(hourAdapter.getPosition(String.format("%02d", expedientEndCal.get(Calendar.HOUR_OF_DAY))));
            expedientEndMinutesSP.setSelection(minutesAdapter.getPosition(String.format("%02d", expedientEndCal.get(Calendar.MINUTE))));
            //breakStartHourSP.setSelection(hourAdapter.getPosition(String.format("%02d", breakStartCal.get(Calendar.HOUR_OF_DAY))));
            //breakStartMinutesSP.setSelection(minutesAdapter.getPosition(String.format("%02d", breakStartCal.get(Calendar.MINUTE))));
            //breakEndHourSP.setSelection(hourAdapter.getPosition(String.format("%02d", breakEndCal.get(Calendar.HOUR_OF_DAY))));
            //breakEndMinutesSP.setSelection(minutesAdapter.getPosition(String.format("%02d", breakEndCal.get(Calendar.MINUTE))));
            //intervalBetweenHourSP.setSelection(hourAdapter.getPosition(String.format("%02d", intervalBetweenCal.get(Calendar.HOUR_OF_DAY))));
            //intervalBetweenMinutesSP.setSelection(minutesAdapter.getPosition(String.format("%02d", intervalBetweenCal.get(Calendar.MINUTE))));
        }

    }

    //este metodo configura os adapters de todos spinners
    public void initSpinnersAdapters(){
       //incluindo mascara nos arrays
//        List<String> hoursArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hours)));
//        for (int i =0; i<hoursArray.size(); i++){
//            hoursArray.set(i, hoursArray.get(i) +" :");
//        }
//        List<String> minutesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.minutes)));
//        for (int i =0; i<minutesArray.size(); i++){
//            minutesArray.set(i, " " + minutesArray.get(i));
//        }
        //configurando adapters e spinners
        hourAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_hour, getResources().getStringArray(R.array.hours));
        hourAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown_hour);
        expedientStartHourSP.setAdapter(hourAdapter);
        expedientEndHourSP.setAdapter(hourAdapter);
        breakStartHourSP.setAdapter(hourAdapter);
        breakEndHourSP.setAdapter(hourAdapter);
        intervalBetweenHourSP.setAdapter(hourAdapter);

        //adapter de array de minutos
        minutesAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_text_minutes, getResources().getStringArray(R.array.minutes));
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expedientStartMinutesSP.setAdapter(minutesAdapter);
        expedientEndMinutesSP.setAdapter(minutesAdapter);
        breakStartMinutesSP.setAdapter(minutesAdapter);
        breakEndMinutesSP.setAdapter(minutesAdapter);
        intervalBetweenMinutesSP.setAdapter(minutesAdapter);



    }

    private void initCalendar(){
        //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        initDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

    }

    public void initBreakTimeRadioListeners(){
        breakTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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
                convertCalendars();
                if(isValidFields()){
                    executeJSON();
                    Intent intentToDrawer = new Intent(ProScheduleConfig_8.this, ProDrawerMenu_15.class);
                    intentToDrawer.putExtra(SessionAttributes.PROFESSIONAL, professionalDTO);
                    startActivity(intentToDrawer);
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void convertCalendars(){
        //convertendo valores de spinners em Calendars
        expedientStartCal = convertToCalendar(expedientStartHourSP.getSelectedItem().toString(), expedientStartMinutesSP.getSelectedItem().toString());
        expedientEndCal = convertToCalendar(expedientEndHourSP.getSelectedItem().toString(), expedientEndMinutesSP.getSelectedItem().toString());
        breakTimeStartCal = convertToCalendar(breakStartHourSP.getSelectedItem().toString(), breakStartMinutesSP.getSelectedItem().toString());
        breakTimeEndCal = convertToCalendar(breakEndHourSP.getSelectedItem().toString(), breakEndMinutesSP.getSelectedItem().toString());
        timeBetweenSession = convertToCalendar(intervalBetweenHourSP.getSelectedItem().toString(), intervalBetweenMinutesSP.getSelectedItem().toString());
    }

    private boolean isValidFields(){
        boolean isAllValid = true;


        selectedDates = screenCalendar.getSelectedDates();
        if(selectedDates == null | selectedDates.isEmpty()){
            isAllValid = false;
            Toast.makeText(this, "Escolha os dias de atendimento.", Toast.LENGTH_LONG).show();
        }
        if(expedientStartCal.after(expedientEndCal)){
            isAllValid = false;
            Toast.makeText(this, "Início de expediente deve ser menor que o final do expediente.", Toast.LENGTH_LONG).show();
        }
        if(breakTimeStartCal.after(breakTimeEndCal) && breakTimeRadioGroup.getCheckedRadioButtonId() == R.id.breakTimeRadioYes){
            isAllValid = false;
            Toast.makeText(this, "Início de intervalo deve ser menor que o final do intervalo.", Toast.LENGTH_LONG).show();
        }
        return isAllValid;
    }

    private void executeJSON(){
        ProfessionalService professionalService = new ProfessionalService();
        DailyScheduleService dailyScheduleService = new DailyScheduleService();
        DailyScheduleDTO dailyScheduleDTO = new DailyScheduleDTO();

        Calendar selectedDayCal = Calendar.getInstance();

        //iniciando Set de BreakTimes
        Set<BreakDTO> breakDTOs = new LinkedHashSet<>();

        //incluindo breaktime de intervalo
        if(breakTimeRadioGroup.getCheckedRadioButtonId() == R.id.breakTimeRadioYes){
            //verifica se o radio esta selecionado como SIM para armazenar o BreakTime
            System.out.println("Intervalo selecionado como sim");
            BreakDTO breakDTO = new BreakDTO();
            breakDTO.setStartTime(breakTimeStartCal);
            breakDTO.setEndTime(breakTimeEndCal);
            System.out.println(breakTimeStartCal.get(Calendar.HOUR_OF_DAY) + ":" + breakTimeStartCal.get(Calendar.MINUTE));
            System.out.println(breakTimeEndCal.get(Calendar.HOUR_OF_DAY) + ":" + breakTimeEndCal.get(Calendar.MINUTE));
            breakDTOs.add(breakDTO);
        } else {
            System.out.println("Intervalo selecionado como não");
        }



        for(int i = 0; i < selectedDates.size(); i++){
            //adiciona um dailySchedule para cada dia selecionado
            selectedDayCal.setTime(selectedDates.get(i));
            dailyScheduleDTO.setProfessional(professionalDTO);
            dailyScheduleDTO.setDate(selectedDayCal);
            dailyScheduleDTO.setStartTime(expedientStartCal);
            dailyScheduleDTO.setEndTime(expedientEndCal);
            dailyScheduleDTO.setBreaks(breakDTOs);
            dailyScheduleDTO.setWorkDay(Status.TRUE);
            try {
                dailyScheduleService.save(dailyScheduleDTO);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Erro ao gravar dados em dailySchedule");
            }
        }
        //incluindo timeBetweenSession no profissional
        professionalDTO.setSessionInterval(timeBetweenSession);
        try {
            professionalService.save(professionalDTO);
        } catch (ServiceException ex){
            ex.printStackTrace();
            System.out.println("Erro ao gravar intervalo entre seções no usuario de id " + professionalDTO.getId());
        }

    }

    private Calendar convertToCalendar(String hour, String minutes){

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        Calendar convertedCal = Calendar.getInstance();

        try{
            convertedCal.setTime(sdf.parse(hour + minutes));

        } catch (Exception e){
            e.printStackTrace();
        }
        return convertedCal;
    }

    private boolean isEditing() {
        return extras.getBoolean("isEditing", true);
    }
}
