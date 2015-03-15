package com.example.henrique.list.Beans;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleItem {
    Date scheduleDate;
    Time scheduleInicialTime;
    Time scheduleFinalTime;
    Time scheduleDuration;
    Time scheduleLeftTime;
    String nameProfessional;

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleInicialTime() {
        return contertTime(scheduleInicialTime);
    }

    public void setScheduleInicialTime(Time scheduleInicialTime) {
        this.scheduleInicialTime = scheduleInicialTime;
    }

    public String getScheduleFinalTime() {
        return contertTime(scheduleFinalTime);
    }

    public void setScheduleFinalTime(Time scheduleFinalTime) {
        this.scheduleFinalTime = scheduleFinalTime;
    }

    public String getScheduleDuration() {
        SimpleDateFormat df = new SimpleDateFormat("HH'h e ':mm'min'");
        return df.format(scheduleDuration);
    }

    public void setScheduleDuration(Time scheduleDuration) {
        this.scheduleDuration = scheduleDuration;
    }

    public String getScheduleLeftTime() {
        return contertTime(scheduleLeftTime);
    }

    public void setScheduleLeftTime(Time scheduleLeftTime) {
        this.scheduleLeftTime = scheduleLeftTime;
    }

    public String getNameProfessional() {
        return nameProfessional;
    }

    public void setNameProfessional(String nameProfessional) {
        this.nameProfessional = nameProfessional;
    }
    private String contertTime(Time time){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(time);
    }
}