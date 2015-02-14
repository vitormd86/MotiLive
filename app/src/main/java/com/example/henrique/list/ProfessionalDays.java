package com.example.henrique.list;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.content.Intent;


public class ProfessionalDays extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_days);

        String professionalName = "Leandro Massaro Kubota";
        String occupation = "Massagista";
        TextView myTextView1 = (TextView) findViewById(R.id.textView1);
        TextView myTextView2 = (TextView) findViewById(R.id.textView2);
        CalendarView myCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //Configura as variaveis do cabecalho
        myTextView1.setText(professionalName);
        myTextView2.setText(occupation);

        //Configura botoes (listener) do calendario
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Intent nextCalendarActivity = new Intent(ProfessionalDays.this,ProfessionalHours.class);

                //Envia dados para proxima Activity (ProfessionalHours)
                nextCalendarActivity.putExtra("dayOfMonth", Integer.toString(dayOfMonth));
                nextCalendarActivity.putExtra("month", Integer.toString(month));
                nextCalendarActivity.putExtra("year", Integer.toString(year));
                startActivity(nextCalendarActivity);
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
}
