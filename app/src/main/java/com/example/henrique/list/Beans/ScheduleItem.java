package com.example.henrique.list.Beans;


import android.graphics.drawable.Drawable;

import com.example.henrique.list.Utilidade_Publica.DateUtilMoti;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ScheduleItem {
    private int listPosition;
    private Date scheduleDate;
    private Date scheduleInicialTime;
    private Date scheduleFinalTime;
    private Date scheduleLeftTime;
    private Drawable mainImage;
    private String personName;

    boolean isSection; //0 para ITEM e 1 para SECTION

    public int getListPosition(){return listPosition;}

    public void setListPosition(int listPosition){
        this.listPosition = listPosition;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleInicialTime() {
        return DateUtilMoti.getSmallHoursStringFromDate(scheduleInicialTime);
    }

    public void setScheduleInicialTime(Date scheduleInicialTime) {
        this.scheduleInicialTime = scheduleInicialTime;
    }

    public String getScheduleFinalTime() {
        return DateUtilMoti.getSmallHoursStringFromDate(scheduleFinalTime);
    }

    public void setScheduleFinalTime(Date scheduleFinalTime) {
        this.scheduleFinalTime = scheduleFinalTime;
    }

    public String getScheduleLeftTime() {
        setScheduleLeftTime(scheduleInicialTime);
        return DateUtilMoti.getBigHoursStringFromDate(scheduleLeftTime);
    }

    private void setScheduleLeftTime(Date scheduleInicialTime) {
        scheduleLeftTime = new Date();
        Calendar todayCal = Calendar.getInstance(TimeZone.getDefault());
        Calendar scheduleInicialCal = Calendar.getInstance();
        Calendar scheduleLeftCal = Calendar.getInstance(TimeZone.getDefault());
        scheduleInicialCal.setTime(scheduleInicialTime);
        scheduleInicialCal.setTimeZone(TimeZone.getDefault());

        scheduleLeftCal.setTimeInMillis(scheduleInicialCal.getTimeInMillis() - todayCal.getTimeInMillis()
                - TimeZone.getDefault().getOffset(scheduleLeftCal.getTimeInMillis()));

        System.out.println(scheduleLeftCal.get(Calendar.HOUR_OF_DAY) + ":" + scheduleLeftCal.get(Calendar.MINUTE)
                + " = " + scheduleInicialCal.get(Calendar.HOUR_OF_DAY) + ":" + scheduleInicialCal.get(Calendar.MINUTE) + " - "
                + todayCal.get(Calendar.HOUR_OF_DAY) + ":" + todayCal.get(Calendar.MINUTE)
                + " Offset: " + TimeZone.getDefault().getOffset(scheduleLeftCal.getTimeInMillis()));

        if (scheduleLeftCal.getTimeInMillis() < 0) {
            //verifica se a diferenca eh maior q 0
            scheduleLeftTime.setTime(scheduleLeftCal.getTimeInMillis());
        } else {
            System.out.println("Nao eh possivel calcular o tempo faltante de um agendamento q ja passou");
            System.out.println("Hora do agendamento: " + DateUtilMoti.getSmallHoursStringFromDate(scheduleInicialTime) +
                    " Hora atual: " + DateUtilMoti.getBigHoursStringFromDate(todayCal.getTime()));
            scheduleLeftTime.setTime(scheduleLeftCal.getTimeInMillis());
        }
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Drawable getMainImage() {
        return mainImage;
    }

    public void setMainImage(Drawable mainImage) {
        this.mainImage = mainImage;
    }

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean isSection) {
        this.isSection = isSection;
    }
}