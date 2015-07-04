package com.example.henrique.list.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract class GenericService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    protected String convertCalendarToString(Calendar calendar) {
        String dateString = null;
        if (calendar != null) {
            try {
                dateString = sdf.format(calendar.getTime());
            } catch(Exception e) {}
        }
        return dateString;
    }
}
