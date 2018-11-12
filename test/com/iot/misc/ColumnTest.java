/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;


import com.iot.misc.data.QueryColumn;
import com.iot.pellet.TestIOT;
import com.iot.web.tag.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class ColumnTest {
    
    public ColumnTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTest method, of class Column.
     */
    @Test
    public void testGetTest() {
        
        QueryColumn col = new QueryColumn("ele~id");
        
        Feed f = new Feed();
        f.add("test", "correct");
        
        Element el = new Element();
        el.id = "myelement";
        el.boss = "parent";
        el.isel = "coltest";
        
        f.add("isel", "partest");
        f.add("element", el);
        col.set(f);
        
        TestIOT t = (TestIOT)col.getTest();
        GenTest t1 = (GenTest)t.next();
        assert (t1.testIt(col.value, "myelement")): col.value;
       

        
        
        
        
        
    }
    
}
