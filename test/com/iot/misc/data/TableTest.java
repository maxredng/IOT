/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
import com.iot.misc.GenTest;
import com.iot.pellet.TestIOT;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class TableTest {
    
    public TableTest() {
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
     * Test of populate method, of class Table.
     */
    @Test
    public void testPopulate() {
 
    }

    /**
     * Test of addColumn method, of class Table.
     */
    @Test
    public void testAddColumn() {

    }

    /**
     * Test of createTable method, of class Table.
     */
    @Test
    public void testCreateTable() {
  
        Table table = new Table("config","test");
        Feed f = new Feed();
        Data d = new Data();
        f.init();
        d.set(f);
        f.add("data", d);
        
        table.set(f);
        
        TestIOT tests = (TestIOT)table.getTest();


        
        GenTest gin = (GenTest)tests.next();
        
        gin.test();
        
        
    }

    /**
     * Test of getTest method, of class Table.
     */
    @Test
    public void testGetTest() {

    }
    
}
