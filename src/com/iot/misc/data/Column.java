/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.pellet.Tester;
import java.io.Serializable;

/**
 *
 * @author bsati
 */
public class Column extends DataElement implements Serializable{
    
    public enum Type
    {
        varchar,integer,blob,object,timestamp,text,longtype
    }
    public String name;
    public Type type;
    public int length;
    private String code;
    public Object value;
    
    
    public Column(String nm,Type tp, int sz)
    {
        name = nm;
        type = tp;
        length = sz;
    }
    
    
    @Override
    public void populate()
    {
        String col = name + " " + type.toString();
        
        if(length!=0)
        {
            col = col + "(" + Integer.toString(length) + ")";
        }
        
    code = col;    
    magazine.add("col", col);
    }
    
    @Override
    public Tester getTest()
    {
        return null;
    }
    
    public String getColum()
    {
        return code;
    
    }
    
    public void add(Object ob)
    {
        value = ob;
    
    }
    
    public String getQueryValue()
    {
        String result = "";
        String qu = "'";
        switch(type)
        {
            case varchar:
            {
            result = qu + value + qu;
            break;
            }
            case integer:
            {
            result = Integer.toString((int)value);
            break;
            }       
            case blob:
            {
            result = qu + value + qu;
            break;
            }        
            case object:
            {
            result = qu + value + qu;
            break;
            }            
            case timestamp:
            {
            result = qu + value + qu;
            break;
            }
            case text:
            {
            result = qu + value + qu;
            break;
            }  
            case longtype:
            {
            result = Long.toString((long)value);
            break;
            }    
        }
        
        
        return result;
    
    }
    
}
