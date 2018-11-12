/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web;

import com.iot.pellet.Testo;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class HTMLTest {
    
    public HTMLTest() {
    }
    

    @Test
    public void testSomeMethod() {
      
        HTML html = new HTML("");
        Testo t = (Testo)html.getTest();
        t.test();
        
    }
    
}
