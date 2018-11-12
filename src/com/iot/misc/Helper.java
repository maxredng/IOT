/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bsati
 */
public class Helper {
    
    public static boolean isThing(String s)
    {
        boolean result = false;
        if(s!=null)
        {
            if(!s.isEmpty())
            {
                 result = true;   
            }
        }
        return result;
    }
    
    
    public static String concat(String[] t,String del)
    {
        String result = "";
        
        for(String s:t)
        {
            result = result + s + del;
        }
        
        result = result.substring(0,result.length()-del.length());
    
        return result;
    }
    
    public static String concatEqual(String[] t1,String[] t2,String del)
    {
        String result = "";
        String q = "'";
        try {
            String eq = "=";
            
            for (int i = 0; i < t1.length; i++) {
                result = result + t1[i] + eq + q +t2[i] +q+ del;
                
            }
            result = result.substring(0, result.length() - del.length());
        } catch (Exception e) {
            result = null;
        }
        return result;
    
    }
    
    public static String getFromPattern(String reg,String body)
    {
        String result = "";
    
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(body);
        
        try {
            while (matcher.find()) {
                int n = matcher.groupCount();
                result = matcher.group(1);
            }
        } catch (Exception e) {
        }
        
        return result;
    }
    
}
