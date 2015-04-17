package com.example.henrique.list.Login;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.henrique.list.Adapters.BreakTimeAdapter;
import com.example.henrique.list.R;

import java.util.Calendar;

/**
 * Created by Cristor on 14/04/2015.
 */
public class ProScheduleConfig_8 extends ActionBarActivity {

    EditText expedientStartET;
    EditText expedientEndET;
    ListView breakTimeList;
    Button addBreakTimeButton;
    ArrayAdapter breakTimeAdapter;
    Calendar expedientStartCalendar = Calendar.getInstance();
    Calendar expedientEndCalendar = Calendar.getInstance();

    TimePickerDialog.OnTimeSetListener startHourPickerListener;
    TimePickerDialog.OnTimeSetListener endHourPickerListener;

    int breakTimeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_schedule_config_8);

        expedientStartET = (EditText) findViewById(R.id.expedientStart);
        expedientEndET = (EditText) findViewById(R.id.expedientEnd);
        breakTimeList = (ListView) findViewById(R.id.breakTimeList);
        addBreakTimeButton = (Button) findViewById(R.id.addBreakTimeButton);

        breakTimeAdapter = new BreakTimeAdapter(getBaseContext());
        breakTimeList.setAdapter(breakTimeAdapter);

        setTimePickerListeners();
        setExpedientHourETListeners();
        setAddBreakTimeButtonListener();
        breakTimeId = 100;
    }

    public void setTimePickerListeners(){
        //esse metodo define os listeners de dentro dos 2 TimePickerDialogs
        startHourPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                expedientStartCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                expedientStartCalendar.set(Calendar.MINUTE, minute);
                expedientStartET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };

        endHourPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                expedientEndCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                expedientEndCalendar.set(Calendar.MINUTE, minute);
                expedientEndET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
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
                        startHourPickerListener,
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
                        endHourPickerListener,
                        expedientEndCalendar.get(Calendar.HOUR_OF_DAY),
                        expedientEndCalendar.get(Calendar.MINUTE), true).show();
            }
        });
    }

    public void setAddBreakTimeButtonListener(){
        addBreakTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakTimeAdapter.add(breakTimeId);
                breakTimeAdapter.notifyDataSetChanged();
                breakTimeId = breakTimeId + 1;

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
