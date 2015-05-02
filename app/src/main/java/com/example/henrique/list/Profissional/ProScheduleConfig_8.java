package com.example.henrique.list.Profissional;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;
import com.example.henrique.list.R;

import java.util.Calendar;

/**
 * Created by Cristor on 14/04/2015.
 */
public class ProScheduleConfig_8 extends ActionBarActivity {

    Spinner expedientStartHourSP, expedientStartMinutesSP, expedientEndHourSP, expedientEndMinutesSP;
    Spinner breakStartHourSP, breakStartMinutesSP, breakEndHourSP, breakEndMinutesSP;
    RadioGroup breakTimeRadioGroup;
    CalendarPickerView screenCalendar;

    Calendar expedientStartCalendar = Calendar.getInstance();
    Calendar expedientEndCalendar = Calendar.getInstance();
    Calendar breakStartCalendar = Calendar.getInstance();
    Calendar breakEndCalendar = Calendar.getInstance();

    TimePickerDialog.OnTimeSetListener startExpedientPickerListener;
    TimePickerDialog.OnTimeSetListener endExpedientPickerListener;
    TimePickerDialog.OnTimeSetListener startBreakPickerListener;
    TimePickerDialog.OnTimeSetListener endBreakPickerListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_config_8);

        //configurando views do layout
        expedientStartHourSP = (Spinner) findViewById(R.id.expedientStartHour);
        expedientStartMinutesSP = (Spinner) findViewById(R.id.expedientStartMinutes);
        expedientEndHourSP = (Spinner) findViewById(R.id.expedientEndHour);
        expedientEndMinutesSP = (Spinner) findViewById(R.id.expedientEndMinutes);
        breakStartHourSP = (Spinner) findViewById(R.id.breakTimeStartHour);
        breakStartMinutesSP = (Spinner) findViewById(R.id.breakTimeStartMinutes);
        breakEndHourSP = (Spinner) findViewById(R.id.breakTimeEndHour);
        breakEndMinutesSP = (Spinner) findViewById(R.id.breakTimeEndMinutes);
        breakTimeRadioGroup = (RadioGroup) findViewById(R.id.breakTimeRadioGroup);
        screenCalendar = (CalendarPickerView) findViewById(R.id.calendar_view);

        //configurando adapters dos spinners
        setSpinnersAdapters();
        //configurando listeners
        setBreakTimeRadioListeners();
        //configurando calendario
        setCalendar();

    }

    public void setSpinnersAdapters(){
        //este metodo configura os adapters de todos spinners

        //adapter de array de horas
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expedientStartHourSP.setAdapter(hourAdapter);
        expedientEndHourSP.setAdapter(hourAdapter);
        breakStartHourSP.setAdapter(hourAdapter);
        breakEndHourSP.setAdapter(hourAdapter);

        //adaprter de array de minutos
        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expedientStartMinutesSP.setAdapter(minutesAdapter);
        expedientEndMinutesSP.setAdapter(minutesAdapter);
        breakStartMinutesSP.setAdapter(minutesAdapter);
        breakEndMinutesSP.setAdapter(minutesAdapter);
    }

    public void setCalendar(){
        //metodo q inicializa calendario

        //configura duas datas para limites, inicial e final
        Calendar initDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 1);

        //inicializa calendario apontando datas finais, iniciais e modo de selecao
        screenCalendar.init(initDate.getTime(), endDate.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

    }

    public void setBreakTimeRadioListeners(){
        breakTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.breakTimeRadioYes:
                        Toast.makeText(getBaseContext(), "Sim", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getBaseContext(), "Não", Toast.LENGTH_SHORT).show();
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
