/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web;
import com.iot.pellet.*;
import com.iot.pellet.IOT;

/**
 *
 * @author bsati
 */
public class IOTModel extends IOT{
    
    public ModelStore store;
    
     
    @Override
    public void set(Magazine ms)
    {
        magazine = new ModelStore();
        magazine = ms;
    }
    
    @Override
    public Magazine get()
    {
        Magazine result = null;
    
        return result;
    }
    
    @Override
    public Tester getTest()
    {
        Tester result = null;
    
        return result;
    }
    
    
    @Override
    public void populate()
    {
         
        
        
    
    }
    
}
