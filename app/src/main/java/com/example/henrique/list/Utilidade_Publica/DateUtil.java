package com.example.henrique.list.Utilidade_Publica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cristor on 18/06/2015.
 */
public class DateUtil {

    static SimpleDateFormat sdf;

    public static int getMinutesFromCalendar(Calendar cal){
        //todo-vitor por enquanto ele so converte corretamente qd calendario < que 1 dia

        int mins;
        mins = cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE);

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
        sdf = new SimpleDateFormat("HH' horas e 'mm' minutos'");
        return sdf.format(date);
    }
}
