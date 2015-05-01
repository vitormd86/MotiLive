package com.example.henrique.list.Login;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.henrique.list.R;

import java.util.Calendar;

/**
 * Created by Cristor on 14/04/2015.
 */
public class ProScheduleConfig_8 extends ActionBarActivity {

    EditText expedientStartET, expedientEndET, breakStartET, breakEndET;
    RadioGroup breakTimeRadioGroup;
    RadioButton breakTimeYes, breakTimeNo;

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
        expedientStartET = (EditText) findViewById(R.id.expedientStart);
        expedientEndET = (EditText) findViewById(R.id.expedientEnd);
        breakStartET = (EditText) findViewById(R.id.breakStart);
        breakEndET = (EditText) findViewById(R.id.breakEnd);
        breakTimeRadioGroup = (RadioGroup) findViewById(R.id.breakTimeRadioGroup);

        //configurando listeners
        setTimePickerListeners();
        setExpedientHourETListeners();
        setBreakTimeETListeners();
        setBreakTimeRadioListeners();
    }

    public void setTimePickerListeners(){
        //esse metodo define os listeners de dentro dos 4 TimePickerDialogs
        startExpedientPickerListener = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                expedientStartCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                expedientStartCalendar.set(Calendar.MINUTE, minute);
                expedientStartET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };
        endExpedientPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                expedientEndCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                expedientEndCalendar.set(Calendar.MINUTE, minute);
                expedientEndET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };
        startBreakPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                breakStartCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                breakStartCalendar.set(Calendar.MINUTE, minute);
                breakStartET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };
        endBreakPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                breakEndCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                breakEndCalendar.set(Calendar.MINUTE, minute);
                breakEndET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };

    }

    public void setExpedientHourETListeners(){
        //este metodo define os listeners dos EditTexts de horario de expediente.
        expedientStartET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo para implementar outros layouts de TimePcker, verificar eu uso com DIALOGFRAGMENT em "android API guides"
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        ProScheduleConfig_8.this,
                        startExpedientPickerListener,
                        expedientStartCalendar.get(Calendar.HOUR_OF_DAY),
                        expedientStartCalendar.get(Calendar.MINUTE), true).show();
            }
        });

        expedientEndET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        ProScheduleConfig_8.this,
                        endExpedientPickerListener,
                        expedientEndCalendar.get(Calendar.HOUR_OF_DAY),
                        expedientEndCalendar.get(Calendar.MINUTE), true).show();
            }
        });
    }

    public void setBreakTimeETListeners(){
        //este metodo define os listeners dos EditTexts de tempo de pausa.
        breakStartET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo para implementar outros layouts de TimePcker, verificar eu uso com DIALOGFRAGMENT em "android API guides"
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        ProScheduleConfig_8.this,
                        startBreakPickerListener,
                        breakStartCalendar.get(Calendar.HOUR_OF_DAY),
                        breakStartCalendar.get(Calendar.MINUTE), true).show();
            }
        });

        breakEndET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        ProScheduleConfig_8.this,
                        endBreakPickerListener,
                        breakEndCalendar.get(Calendar.HOUR_OF_DAY),
                        breakEndCalendar.get(Calendar.MINUTE), true).show();
            }
        });
    }

    public void setBreakTimeRadioListeners(){
        breakTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.breakTimeRadioYes:
                        Toast.makeText(getBaseContext(), "Sim", Toast.LENGTH_SHORT).show();
                        breakStartET.setEnabled(true);
                        breakStartET.setClickable(true);
                        breakEndET.setEnabled(true);
                        breakEndET.setClickable(true);

                        break;
                    case R.id.breakTimeRadioNo:
                        Toast.makeText(getBaseContext(), "Não", Toast.LENGTH_SHORT).show();
                        breakStartET.setEnabled(false);
                        breakStartET.setClickable(false);
                        breakEndET.setEnabled(false);
                        breakEndET.setClickable(false);
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
