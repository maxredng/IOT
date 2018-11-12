/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web.tag;

import com.iot.pellet.Testo;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class ElementTest {
    
    public ElementTest() {
    }
    

    /**
     * Test of getTest method, of class Element.
     */
    @Test
    public void testGetTest() {
         
       Element el = new Element();
       Testo t = (Testo)el.getTest();
       t.test();
       
    }

    /**
     * Test of addAttribute method, of class Element.
     */

}
