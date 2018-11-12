/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
import com.iot.misc.GenTest;
import com.iot.misc.Helper;
import com.iot.pellet.TestIOT;
import com.iot.pellet.Tester;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bsati
 */
public class Table extends DataElement implements Serializable{
    
    public String schema;
    public String table;
    public Map<String,Column> columns;
    List<String> order;
    private String[] row;
    
    public Table(String sc,String nm)
    {
        schema = sc;
        table = nm;
        columns = new HashMap<>();
        order = new ArrayList<>();
    }
    
    @Override
    public void populate()
    {
        
    
    }
    
    public void populateColumns(Object[] str)
    {
        int i = 0;
        for(String s:order)
        {
            setColumn(s,str[i]);
            i++;
        }
        
        
    }
    
   public Object get(String s)
   {
       return columns.get(s);
   
   }
   
      public Object getValue(String s)
   {
       Column result = columns.get(s);
       return result.value;
   
   }
    
    public void insert()
    {
        String qr = "insert into " + schema + "." + table + " values(";
        Iterator it = columns.entrySet().iterator();
        for(String n:order)
        {
            
            Column col = columns.get(n);
            qr = qr + col.getQueryValue() + ",";
        }
    
        qr = qr.substring(0,qr.length() -1);
        
        qr = qr + ")";
        
        data.Execute(qr);
    }
    
        public void insertBytes()
    {
        String qr = "insert into " + schema + "." + table + " (";
        Column[] cls = new Column[columns.size()];
        int i = 0;
        for(String n:order)
        {
            
            Column col = columns.get(n);
            cls[i] = col;
            qr = qr  + n + ",";
            i++;
        }
    
        
        qr = qr.substring(0,qr.length() -1);
        
        qr = qr + ") values (";
        
        for(int j = 0; j<i;j++)
        {
            qr = qr + "?,";
        }
        
        qr = qr.substring(0,qr.length() -1);
        
        qr = qr + ")";
        data.ExecuteBytes(qr,cls);
    }
    
    public void addColumn(String n,Column.Type t,int ln)
    {
        Column col = new Column(n,t,ln);
        col.set(new Feed());
        columns.put(n, col);
        order.add(n);
    
    }
    
    public void setColumn(String name,Object value)
    {
        columns.get(name).add(value);
        
    
    }
    
    public void addColumn(Column col)
    {
        columns.put(col.name,col);
        order.add(col.name);
    }
    
    public void createTable()
    {
        String query = "create table if not exists tablename ?";
        String cols = "";
        String[] temp = new String[columns.size()];
        int i = 0;
        
        for(String n:order)
        {
            Column col = columns.get(n);
            temp[i] = col.getColum();
            i++;
        
        }
        
        
        String bra = "(" + Helper.concat(temp, ",") + ")";
        query = query.replace("?", bra).replace("tablename", schema + "." + table);
        
        data.Execute(query);
        
        
    
    }
    
    public void setHeader(String[] l)
    {
        order = Arrays.asList(l);
    }
    
    
    
    public void deleteTable()
    {
         String query = "drop table tablename";

        query = query.replace("tablename", schema + "." + table);
        
        data.Execute(query);   
    
    }
    
    @Override
    public Tester getTest()
    {
       
        Feed f = new Feed();
        f.init();
        Data d = new Data();
        d.set(f);
        
        
       TestIOT test = new TestIOT();

       
       GenTest gins = new GenTest("test inserting into table");
       
        Table tab = new Table("config","test");
        f.add("data", d);
        
        tab.set(f);
        Column col = new Column("id",Column.Type.varchar,45);
        col.set(f);
        col.add("myid");
        tab.addColumn(col);
        
        Column val = new Column("value",Column.Type.varchar,100);
        val.set(f);
        val.add("myvalue");
        tab.addColumn(val);
        
        Column ord = new Column("ord",Column.Type.integer,10);
        ord.set(f);
        ord.add(10);
       
        tab.addColumn(ord);
        
        tab.createTable();
        tab.insert();
        
        String q = "select id from config.test";
        gins.setArg(data.pullString(q));
        gins.setCriteria("myid");
        test.addTest(gins);
        return test;
    }
    
}
