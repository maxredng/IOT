/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.DBEntry;
import com.iot.misc.GenTest;
import com.iot.pellet.TestIOT;
import com.iot.pellet.Tester;

/**
 *
 * @author bsati
 */
public class QueryColumn extends DBEntry{
    
    public QueryColumn(String val)
    {
        super(val);
    }
    
    @Override
    public Tester getTest()
    {
        TestIOT t = new TestIOT();
        GenTest test1 = new GenTest("test with columns derived from feed parameters");
        t.addTest(test1);
        GenTest test2 = new GenTest("test with columns derived from element parameter = id");
        t.addTest(test2);
        
        return t;
    }
    
}
