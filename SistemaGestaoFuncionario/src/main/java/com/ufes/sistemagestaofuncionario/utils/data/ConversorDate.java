package com.ufes.sistemagestaofuncionario.utils.data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConversorDate {

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
}
