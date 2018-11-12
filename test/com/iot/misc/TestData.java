/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import com.iot.misc.data.Data;
import com.iot.misc.data.Query;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author bsati
 */
public class TestData {
    
   @Test
    public void testData()
    {
        Data data = new Data();
        Feed f = new Feed();
        f.add("user", "root");
        f.add("password", "topor234");
        data.set(f);
        String q = "select * from config.list";
        List<String[]> l = data.pullTable(q);
        assert(l.size()>0): "l is empty";
    
    }
    
    @Test
    public void testGetTables()
    {
//        Data data = new Data();
//        NewFeed f = new NewFeed();
//        f.add("user", "root");
//        f.add("password", "topor234");
//        data.set(f);
//        
//        String q = "select one,two,three from config where one='1'";
//        
//        String[] temp = Query.getTables(q);
//        assert(temp[0].equals("one")): temp[0];
    
    }

    /**
     * Test of pullString method, of class Data.
     */



    
}
