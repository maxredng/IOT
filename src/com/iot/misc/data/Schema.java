/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import java.util.HashMap;

/**
 *
 * @author bsati
 */
public class Schema extends HashMap{
    
   public String name;
   public Schema(String n)
   {
       name = n;
   
   }
    
   public void addTable(String tabname, Table table)
   {
       put(tabname,table);
   }
   
   public Table getTable(String tabname)
   {
       return (Table)get(tabname);
   
   }
    
}
