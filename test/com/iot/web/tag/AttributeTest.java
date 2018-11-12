/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web.tag;

import com.iot.misc.Feed;
import com.iot.misc.GenTest;
import com.iot.pellet.TestIOT;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class AttributeTest {
    
    public AttributeTest() {
    }
    

    @Test
    public void testGetValue() {

        Attribute at = new Attribute();

        TestIOT gt = (TestIOT)at.getTest();
        GenTest ge = (GenTest)gt.next();
        ge.test();
    }
    
}
