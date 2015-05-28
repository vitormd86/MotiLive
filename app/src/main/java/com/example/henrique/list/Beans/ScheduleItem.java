package com.example.henrique.list.Beans;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleItem {
    int listPosition;
    Date scheduleDate;
    Date scheduleInicialTime;
    Date scheduleFinalTime;
    Date scheduleLeftTime;
    String personName;
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
        return contertTime(scheduleInicialTime);
    }

    public void setScheduleInicialTime(Date scheduleInicialTime) {
        this.scheduleInicialTime = scheduleInicialTime;
    }

    public String getScheduleFinalTime() {
        return contertTime(scheduleFinalTime);
    }

    public void setScheduleFinalTime(Date scheduleFinalTime) {
        this.scheduleFinalTime = scheduleFinalTime;
    }

    public String getScheduleLeftTime() {
        return contertTime(scheduleLeftTime);
    }

    public void setScheduleLeftTime(Time scheduleLeftTime) {
        this.scheduleLeftTime = scheduleLeftTime;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
    private String contertTime(Date dateTime){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(dateTime);
    }

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean isSection) {
        this.isSection = isSection;
    }
}