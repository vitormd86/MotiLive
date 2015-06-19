package com.example.henrique.list.Utilidade_Publica;

import java.util.Calendar;

/**
 * Created by Cristor on 18/06/2015.
 */
public class DateUtil {
    public static int calendarToMinutes(Calendar cal){
        //todo-vitor por enquanto ele so converte corretamente qd calendario < que 1 dia

        int mins;
        mins = cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE);

        return mins;
    }
}
