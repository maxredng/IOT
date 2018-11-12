/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
import com.iot.pellet.Magazine;
import com.iot.pellet.Tester;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bsati
 */
public class QueryTest {
    
    public QueryTest() {
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
     * Test of set method, of class Query.
     */
    @Test
    public void testSet() {

        
    }

    /**
     * Test of get method, of class Query.
     */
    @Test
    public void testGet() {
 
    }

    /**
     * Test of populate method, of class Query.
     */
    @Test
    public void testPopulate() {
 
    }

    /**
     * Test of getTest method, of class Query.
     */
    @Test
    public void testGetTest() {
 
    }

    /**
     * Test of getTables method, of class Query.
     */
    @Test
    public void testGetTables() {
 
    }

    /**
     * Test of getSchema method, of class Query.
     */
    @Test
    public void testGetSchema() {
        
        String q = "select row,col from config.col where name='name' and element='c' order by row,col";
        
        Query query = new Query(q);
        assert (query.orders.length>1);
        
    }
    
}
