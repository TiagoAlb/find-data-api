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
import java.util.regex.Pattern;

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
    
    public String NULL_TO_EMPTY(String value) {
        if(value!=null)
            return value;
        
        return "";
    }
    
    public String NULL_TO_ZERO(String value) {
        if(value!=null)
            return value;
        
        return "0";
    }
    
    public String CLEAN_NUMERIC(String value) {
        if(value!=null)
            return value.trim().replace(".", "").replace("-", "");
        
        return "";
    }
    
    public boolean IS_NUMERIC(String value) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (value == null) 
            return false; 
  
        return pattern.matcher(value).matches();
    }
}
