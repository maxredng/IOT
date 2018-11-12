/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web;

import com.iot.pellet.*;
import java.util.Map;
import com.iot.misc.*;
/**
 *
 * @author bsati
 */
public class ModelStore implements Magazine{
    
    public enum type
    {
        top,footer,tree,store,window,caption
    }
    
    
    public Config config;
    
    private Map<String,Object> map;
    
    @Override
    public void add(String st,Object b)
    {
    
        map.put(st, b);
    }
    
    @Override
    public Object getObject(String st)
    {
        return map.get(st);
    
    }
    
}
