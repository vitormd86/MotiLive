package com.example.henrique.list.Adapters;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.henrique.list.Login.ProScheduleConfig_8;
import com.example.henrique.list.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Cristor on 15/04/2015.
 */
public class BreakTimeAdapter extends ArrayAdapter<Integer> {

    Context mContext;
    Button breakTimeRemoveButton;
    TimePickerDialog.OnTimeSetListener startHourPickerListener;
    TimePickerDialog.OnTimeSetListener endHourPickerListener;
    Calendar breakTimeStartCalendar = Calendar.getInstance();
    Calendar breakTimeEndCalendar = Calendar.getInstance();

    public BreakTimeAdapter(Context context) {
        super(context, R.layout.view_break_time);
        mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View view = theInflator.inflate(R.layout.view_break_time, parent, false);

        breakTimeRemoveButton = (Button) view.findViewById(R.id.breakTimeRemoveButton);

        EditText breakTimeStartET = (EditText) view.findViewById(R.id.breakStart);
        EditText breakTimeEndET = (EditText) view.findViewById(R.id.breakEnd);

        setBreakTimeRemoveListener(position);
        setTimePickerListeners(breakTimeStartET, breakTimeEndET);
        setBreakTimeETListeners(breakTimeStartET, breakTimeEndET);

        return view;
    }

    private void setBreakTimeRemoveListener(final int position){
        //esta funcao configura o listener do botao de remover intervalo
        breakTimeRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(position));
                Toast.makeText(getContext(), "Remover item da posicao " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setTimePickerListeners(final EditText startET, final EditText endET){
        //esse metodo define os listeners de dentro dos 2 TimePickerDialogs
        startHourPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                breakTimeStartCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                breakTimeStartCalendar.set(Calendar.MINUTE, minute);
                startET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };

        endHourPickerListener  = new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                //quando selecionado o horario, aliemnta o calendar e o editText com o horario selecionado
                breakTimeEndCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                breakTimeEndCalendar.set(Calendar.MINUTE, minute);
                endET.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            }
        };
    }
    public void setBreakTimeETListeners(final EditText startET, final EditText endET){
        //este metodo define os listeners dos EditTexts de horario de intervalo.

        startET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo para implementar outros layouts de TimePcker, verificar eu uso com DIALOGFRAGMENT em "android API guides"
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        mContext,
                        startHourPickerListener,
                        breakTimeStartCalendar.get(Calendar.HOUR_OF_DAY),
                        breakTimeStartCalendar.get(Calendar.MINUTE), true).show();
            }
        });

        endET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(
                        //inicia o timePicker escolhendo seu listener, e com qual horario deve iniciar (que vem da instancia do Calendar)
                        mContext,
                        endHourPickerListener,
                        breakTimeEndCalendar.get(Calendar.HOUR_OF_DAY),
                        breakTimeEndCalendar.get(Calendar.MINUTE), true).show();
            }
        });
    }
}
