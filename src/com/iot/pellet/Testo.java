/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.pellet;

import com.iot.misc.GenTest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bsati
 */
public class Testo implements Tester{
    
    private List<Object> tests;
    private int count;
    
    public Testo()
    {
        count = 0;
        tests = new ArrayList<>();
    }
    
    
    @Override
    public List<Object> getTests()
    {
        return tests;
    
    }
    
    @Override
    public void addTest(Object ob)
    {
        if(tests == null)
            tests = new ArrayList<>();
        tests.add(ob);
    }
    
    public void addTest(String name,Object arg,Object cret)
    {
        GenTest gt = new GenTest(name);
        gt.setArg(arg);
        gt.setCriteria(cret);
        if(tests == null)
        {
            tests = new ArrayList<>();
        }
        
        tests.add(gt);
    }
    
    public void test()
    {
        GenTest test = (GenTest)tests.get(count);
        test.test();
        count ++;
    }
    
    public GenTest next()
    {
        GenTest result = (GenTest)tests.get(count);
        count ++;
        return result;
    }
    
}
