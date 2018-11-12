/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.pellet;

import java.util.List;

/**
 *
 * @author bsati
 */
public interface Tester{
    
    void addTest(Object test);
    List<Object> getTests();
    
}
