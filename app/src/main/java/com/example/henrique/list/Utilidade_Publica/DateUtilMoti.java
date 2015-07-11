package com.example.henrique.list.Utilidade_Publica;

import com.example.henrique.list.Utilidade_Publica.Calendar.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Cristor on 18/06/2015.
 */
public class DateUtilMoti {

    static SimpleDateFormat sdf;

    public static int getMinutesFromCalendar(Calendar cal){
        //todo-vitor por enquanto ele so converte corretamente qd calendario < que 1 dia
        int mins;
        Date date = cal.getTime();
        mins = getMinutesFromDate(date);

        return mins;
    }

    public static int getMinutesFromDate(Date date){
        int mins;
        mins = date.getHours()*60 + date.getMinutes();

        return mins;
    }

    public static Calendar getCalendarFromString(String sDate, SimpleDateFormat sdf){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        try{
            date = sdf.parse(sDate);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao tranformar String em Date");
        }
        cal.setTime(date);
        return cal;
    }
    public static Date getDateFromString(String sDate, SimpleDateFormat sdf){
        Date date = new Date();
        try{
            date = sdf.parse(sDate);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao tranformar String em Date");
        }
        return date;
    }

    public static String getDateStringFromCalendar(Calendar cal){
        Date date = cal.getTime();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    public static String getSmallHoursStringFromCalendar(Calendar cal){
        Date date = cal.getTime();
        sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String getSmallHoursStringFromDate(Date date){
        sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String getBigHoursStringFromDate(Date date){
        sdf = new SimpleDateFormat("'HH' horas e 'mm' minutos'");
        return sdf.format(date);
    }

    public static String getDayOfWeekString(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "domingo";
                break;
            case 2:
                day = "segunda-feira";
                break;
            case 3:
                day = "terça-feira";
                break;
            case 4:
                day = "quarta-feira";
                break;
            case 5:
                day = "quinta-feira";
                break;
            case 6:
                day = "sexta-feira";
                break;
            case 7:
                day = "sábado";
                break;
        }
        return day;
    }
    public static String getMonthString(int value) {
        String month = "";
        switch (value) {
            case 1:
                month = "Janeiro";
                break;
            case 2:
                month = "Fevereiro";
                break;
            case 3:
                month = "Março";
                break;
            case 4:
                month = "Abril";
                break;
            case 5:
                month = "Maio";
                break;
            case 6:
                month = "Junho";
                break;
            case 7:
                month = "Julho";
                break;
            case 8:
                month = "Agosto";
                break;
            case 9:
                month = "Setembro";
                break;
            case 10:
                month = "Outubro";
                break;
            case 11:
                month = "Novembro";
                break;
            case 12:
                month = "Dezembro";
                break;
        }
        return month;
    }
    public static Calendar truncateCal(Calendar cal){
        Calendar toTruncateCal = Calendar.getInstance(TimeZone.getDefault());
        int minutesToAdd;
        int overMinutes;

        toTruncateCal.setTime(cal.getTime());

        overMinutes = toTruncateCal.get(Calendar.MINUTE) % 5;
        minutesToAdd = 5 - overMinutes;

        toTruncateCal.add(Calendar.MINUTE, minutesToAdd);
        return toTruncateCal;
    }

    public static ArrayList<Date> repeatDaysOfWeek (Date initDate, Date endDate, int targetDayOfWeek){
        //este metodo gera uma array de repeticao de dias de semana, dentro de um limite minimo e maximo de data.

        ArrayList<Date> selectedDaysOfWeeK = new ArrayList<>();

        //inicializando calendarios
        Calendar dateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();
        dateCal.setTime(initDate);
        endDateCal.setTime(endDate);
        //zerando horario dos calendarios
        CalendarPickerView.setMidnight(dateCal);
        CalendarPickerView.setMidnight(endDateCal);

        int initDayOfWeek = dateCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < 7; i++){
            //laco para encontrar o dia da semana requisitado como argumento do metodo
            if (initDayOfWeek == targetDayOfWeek)
                break;
            dateCal.add(Calendar.DAY_OF_WEEK, 1);
            initDayOfWeek = dateCal.get(Calendar.DAY_OF_WEEK);
        }

        while(dateCal.getTime().getTime() < endDateCal.getTime().getTime()){
            //adiciona todos os dias escolhidos dentro da array que sera retornada
            selectedDaysOfWeeK.add(dateCal.getTime());
            dateCal.add(Calendar.DAY_OF_WEEK, 7);
        }

        //Toast.makeText(this, "Igualou no dia " + initDayOfWeek + ". Date = " + initDateCal, Toast.LENGTH_SHORT).show();

        return selectedDaysOfWeeK;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2){
        if(cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)){
            return true;
        } else {
            return false;
        }
    }
}
