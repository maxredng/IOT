/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web.tag;

import com.iot.pellet.Magazine;
import com.iot.pellet.Pellet;
import com.iot.pellet.Tester;
import com.iot.misc.*;
import com.iot.misc.data.Data;
import com.iot.misc.data.Query;
import com.iot.misc.data.Schema;
import com.iot.misc.data.Table;
import com.iot.pellet.TestIOT;
import java.io.Serializable;

/**
 * 
 * @author bsati
 */

// dependencies: Query,Data,Config


public class Attribute implements Pellet,Serializable{
    
    public String key;
    public String value;
    public String code;
    public Magazine magazine;
    String boss;
    public int ord;
    Element element;
    public Feed feed;
    private String isel;
    private String query;
    private Config config;
    final String qu = "'";
    final String delimiter = "$&@";
    
    public Attribute(String[] st,Element el)
    {
        element = el;
        populate(st);
    
    }
    
    public Attribute(String k, String v)
    {
        
        key = k;
        value = v;
        
    }
    
    public Attribute()
    {
        
    }
    
    @Override
    public void set(Magazine m)
    {    
        magazine = m;
        feed = (Feed)magazine;
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
        
        value = getValue();
        code = key + "=" + qu + value + qu;
    }
    
    @Override
    public Tester getTest()
    {
        Tester result = new TestIOT();
        
            feed = new Feed();
            feed.init();
            Attribute at = new Attribute("kluch","isel~acdi");
            feed.add("boss", "some");
            Element el = new Element();
            el.boss = "some";
            feed.add("element", el);
            at.set(feed);
            
            GenTest gt = new GenTest("test attribute with value derived from isel");
            gt.setArg(at.code);
            gt.setCriteria("kluch='valsome'");
            result.addTest(gt);
            
            
        
        return result;
    }
    
    public void updateValue()
    {}
    
    public String getIsel(String s)
    {
        String reg = "^isel~(.*)";
        
        
        String result = Helper.getFromPattern(reg, s);
    
        return result;
    }
    
    public String getNewValue()
    {
        String result = null;
       
       
        if(isel!=null)
        {
            Query quer = null;
            if(config!=null)
            {
            quer = config.get(isel);
            }
            if(quer == null)
            {
                quer = new Query(isel,element);
                quer.set(feed);
            }
        
            if(feed == null)
            {
                feed = new Feed();
                feed.init();
            }
            result = feed.pullString(quer.query);
            
        }
        
        return result;   
    
    
    }
    
    public String getValue()
    {
        String result = value;
        
        
        isel = getIsel(value);
       
        if(Helper.isThing(isel))
        {
            
            Query quer = null;
            if(config!=null)
            {
            quer = config.get(isel);
            }
            if(quer == null)
            {
                quer = new Query(isel,element);
                quer.set(feed);
            }
        
            Data data = (Data)magazine.getObject("data");
            result = data.pullString(quer.query);
            
        }
        
        return result;
    
    }

    private void populate(String[] st) {

        feed = element.feed;
        boss = element.boss;
        Schema sc = (Schema)feed.getObject("schema_config");
        Table tb = sc.getTable("attribute");
        tb.populateColumns(st);
        
        key = (String)tb.getValue("name");
        isel = (String)tb.getValue("isel");
        ord = Integer.parseInt((String)tb.getValue("ord"));
        if(!Helper.isThing(isel))
        {
            value = (String)tb.getValue("value");
        }else
        {
            value = getNewValue();
        }
        
         
    }
    
}
