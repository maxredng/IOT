/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
import com.iot.pellet.Testo;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class DataTest {
    
    public DataTest() {
    }
    


    /**
     * Test of getTest method, of class Data.
     */
    @Test
    public void testGetTest() {
   
        Data d = new Data();
        Feed f = new Feed();
        f.init();
        d.set(f);
        Testo t = (Testo)d.getTest();
        t.test();
    }
    
}
