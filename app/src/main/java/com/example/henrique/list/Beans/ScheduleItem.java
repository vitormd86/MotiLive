package com.example.henrique.list.Beans;


import com.example.henrique.list.Utilidade_Publica.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
        setScheduleLeftTime(scheduleInicialTime);
        return DateUtil.getBigHoursStringFromDate(scheduleLeftTime);
    }

    private void setScheduleLeftTime(Date scheduleInicialTime) {
        //todo esta com bug de dias meses e ano.
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
            System.out.println("Não é possível calcular o tempo faltante de um agendamento q já passou");
            System.out.println("Hora do agendamento: " + DateUtil.getSmallHoursStringFromDate(scheduleInicialTime) +
                    " Hora atual: " + DateUtil.getBigHoursStringFromDate(todayCal.getTime()));
            scheduleLeftTime.setTime(scheduleLeftCal.getTimeInMillis());
        }
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