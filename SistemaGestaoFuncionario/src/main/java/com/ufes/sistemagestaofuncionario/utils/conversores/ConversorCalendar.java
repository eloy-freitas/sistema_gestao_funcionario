package com.ufes.sistemagestaofuncionario.utils.conversores;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ConversorCalendar {
    
    public static LocalDate calendarToLocalDate(Calendar calendar){
        Date dateCalendar = calendar.getTime();
        return dateCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
}
