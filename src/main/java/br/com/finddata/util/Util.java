/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finddata.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tiago
 */
public class Util {
    public String GET_CURRENT_DATE_STRING() {
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        return dateFormat.format(date); 
    }
    
    public boolean STR_TO_BOOLEAN(String value) {
        value = value.toLowerCase().trim();
        return (value!=null) && (value.equals("true") || value.equals("sim") || value.equals("1"));
    }
}
