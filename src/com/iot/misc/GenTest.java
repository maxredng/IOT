/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import com.iot.pellet.Tester;

/**
 *
 * @author bsati
 */
public class GenTest{
    String description;
    
    public Object value;
    public Object criteria;
    
    public GenTest(String ds)
    {
        description = ds;
    }
    
    public void setArg(Object ob)
    {
        value = ob;
    }
    public void setCriteria(Object ob)
    {
        criteria = ob;
    }
    
    public void test()
    {
        if(criteria == null)
        {
            assert(value == null): value + " is not null";
        }else
        {
            String tp = value.getClass().getTypeName();
            
            if(value.getClass().getTypeName().equals("java.lang.String"))
            {
                assert(value.equals((String)criteria)): value + " not equal " + criteria;
            }else
            {
                assert(value == criteria): value + " not equal " + criteria;
            }
        
        }
    
    }
    
     public boolean testIt(String ar1,String ar2)
     {
         boolean result = false;
         if(ar1!=null)
         {
             if(ar1.equals(ar2))
             {
                 result = true;
             }
         }else
         {
         if(ar2 == null)
         {
             result = true;
         }
         
         }
         
         return result;
     }
    
}
