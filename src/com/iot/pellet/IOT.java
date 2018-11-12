/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.pellet;

/**
 *
 * @author bsati
 */
public abstract class IOT implements Pellet{
    
    public Magazine magazine;
    public String error;
    
    public Testo test = new Testo();
    
    @Override
    public abstract void set(Magazine m);
 

    @Override
    public abstract Magazine get();
    
    
    @Override
    public abstract Tester getTest();
    
    @Override
    public abstract void populate();
    
}
