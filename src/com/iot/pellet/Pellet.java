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
public interface Pellet {
    
    void set (Magazine m);
    Magazine get();
    Tester getTest();
    void populate();
    
    
    
}
