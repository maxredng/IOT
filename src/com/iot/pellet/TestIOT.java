/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.pellet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bsati
 */
public class TestIOT implements Tester{
    
    private List<Object> list;
    private int count;
    public TestIOT()
    {
        list = new ArrayList<>();
        count = 0;
    }
    
    @Override
    public void addTest(Object t)
    {
        list.add(t);
    
    }
    
    @Override
    public List<Object> getTests()
    {
        return list;
    
    }
    
    public Object next()
    {
        Object result = null;
        if(count<list.size())
        {
            result = list.get(count);
        }
        count ++;
        return result;
        
    }
    
    public boolean hasNext()
    {
        if(count<list.size())
        {
            return true;
        }else
        {
            return false;
        }
    }
    
}
