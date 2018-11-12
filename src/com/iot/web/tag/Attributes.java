/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web.tag;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author bsati
 */
public class Attributes extends HashMap{
    
    public String getCode()
    {
        String result = "";
        
        Iterator it = this.entrySet().iterator();
        
        while(it.hasNext())
        {
        Attribute attribute = (Attribute)it.next();
        attribute.populate();
        result = result + attribute.code + " "; // TODO: add logic to populate with isel
        
        }
        
        return result;
    
    }
    
}
