package com.example.henrique.list.Beans;

import com.example.henrique.list.Utilidade_Publica.DateUtil;

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
        return DateUtil.getSmallHoursStringFromDate(scheduleInicialTime);
    }

    public void setScheduleInicialTime(Date scheduleInicialTime) {
        this.scheduleInicialTime = scheduleInicialTime;
    }

    public String getScheduleFinalTime() {
        return DateUtil.getSmallHoursStringFromDate(scheduleFinalTime);
    }

    public void setScheduleFinalTime(Date scheduleFinalTime) {
        this.scheduleFinalTime = scheduleFinalTime;
    }

    public String getScheduleLeftTime() {
        return DateUtil.getSmallHoursStringFromDate(scheduleLeftTime);
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

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean isSection) {
        this.isSection = isSection;
    }
}