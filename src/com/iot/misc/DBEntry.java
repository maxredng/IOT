/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import com.iot.pellet.IOT;
import com.iot.pellet.Magazine;
import com.iot.pellet.Tester;
import com.iot.web.tag.Element;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bsati
 */
public class DBEntry extends IOT{
    
    public Element element;
    public String value;
    private String rawvalue;
    Feed feed;
    
    public DBEntry(String v)
    {
        rawvalue = v;
    }
    
   @Override
   public void set(Magazine m)
   {
       magazine = m;
       populate();
   }
   
   @Override
   public Magazine get()
   {
       return magazine;
   }
   
   @Override
   public void populate()
   {
       feed = (Feed)magazine;
       element = (Element)feed.get("element");
       value = getValue(rawvalue);
       
   
   }
   
   @Override
   public Tester getTest()
   {
       Tester result = null;
   
       return result;
   }
   

   
   public String getValue(String val)
   {
       String result = val;
       boolean quoted = false;
       String paregex = "par~(.*)";
       String elegex = "ele~(.*)";
       result = (String)feed.get(getFromRegex(paregex,val));
       if(result!=null)
       if(result.contains("#"))
       {
           result = result.split("#")[0];
           quoted = true;
       }
       if(result==null)
       {
           
       String r = getFromRegex(elegex,val);
        if(r!=null)
       if(r.contains("#"))
       {
           r = r.split("#")[0];
           quoted = true;
       }      
           try {
               Field f = element.getClass().getDeclaredField(r);
               f.setAccessible(true);
              
               try {
              
                   Object vlu = f.get(element);
                   result = (String)vlu;
               } catch (IllegalAccessException illegalAccessException) {
               } catch (IllegalArgumentException illegalArgumentException) {
               }
           } catch (NoSuchFieldException noSuchFieldException) {
           } catch (SecurityException securityException) {
           }catch(Throwable bl)
           {
               
           }
       
       }
       
       if(result == null)
           result = val;
       
    if(quoted)
        result = "'" + result + "'";
       return result;
   }
   
   String getFromRegex(String pat,String body)
   {
       String result = "";
        
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(body);
        
        while(matcher.find())
        {
            result = matcher.group(1);
        }
       
       return result;
   }
    
}
