/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
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

    @Test
    public void testSomeMethod() {
         
        Column idcol = new Column("id",Column.Type.varchar,45);
        Feed f = new Feed();
        idcol.set(f);
        String val = (String)idcol.magazine.getObject("col");
        assert (val.equals("id varchar(45)")): val;
   
        Column img = new Column("img",Column.Type.blob,0);
        img.set(f);
        String va = (String)idcol.magazine.getObject("col");
        assert (va.equals("img blob")): va;
        
    }
    
}
