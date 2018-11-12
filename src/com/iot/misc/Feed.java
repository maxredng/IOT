/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import com.iot.misc.data.Column;
import com.iot.misc.data.Data;
import com.iot.misc.data.Schema;
import com.iot.misc.data.Table;
import com.iot.pellet.Magazine;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bsati
 */
public class Feed extends HashMap<String,Object> implements Magazine{
    
     Data data;
    
    public Feed()
    {

    }
    
    public void init()
    {
        add("user","root");
        add("password","topor234");
        Data data = new Data();
        data.set(this);
        add("data",data);
        Table eletab = new Table("config","element");
        eletab.set(this);
        Column id = new Column("id",Column.Type.varchar,100);
        Column parent = new Column("parent",Column.Type.varchar,100);
        Column signature = new Column("signature",Column.Type.varchar,1000);
        Column body = new Column("body",Column.Type.blob,0);
        Column element = new Column("scope",Column.Type.varchar,45);
        Column project = new Column("project",Column.Type.varchar,45);       
        Column isel = new Column("isel",Column.Type.varchar,60);
        id.set(this);
        eletab.addColumn(id);
        parent.set(this);
        eletab.addColumn(parent);
        signature.set(this);
        eletab.addColumn(signature);
        body.set(this);
        eletab.addColumn(body);
        element.set(this);
        eletab.addColumn(element);
        project.set(this);
        eletab.addColumn(project);
        isel.set(this);
        eletab.addColumn(isel);
        Schema conschema = new Schema("config");
        conschema.addTable("element", eletab);
        
        Table atab = new Table("config","attribute");
        atab.set(this);
        atab.addColumn("name",Column.Type.varchar,45);
        atab.addColumn("value",Column.Type.varchar,1000);
        atab.addColumn("parent",Column.Type.varchar,60);
        atab.addColumn("ord",Column.Type.integer,10);
        atab.addColumn("isel",Column.Type.varchar,60);
        conschema.addTable("attribute", atab);
        
        Table htab = new Table("config","html");
        htab.set(this);
        htab.addColumn("eid", Column.Type.varchar, 60);
        htab.addColumn("id", Column.Type.varchar, 60);
        htab.addColumn("name", Column.Type.varchar, 60);
        htab.addColumn("value", Column.Type.varchar, 1000);
        htab.addColumn("element", Column.Type.varchar, 60);
        htab.addColumn("parent", Column.Type.varchar, 60);
        htab.addColumn("scope", Column.Type.varchar, 60);
        htab.addColumn("project", Column.Type.varchar, 60);
        htab.addColumn("type", Column.Type.varchar, 60);
        htab.addColumn("row", Column.Type.integer, 10);
        htab.addColumn("col", Column.Type.integer, 10);
        htab.addColumn("isel", Column.Type.varchar, 60);
        
  
        conschema.addTable("html", htab);
        
        Table javascript = new Table("config","javascript");
        javascript.set(this);
        
       javascript.addColumn("bid", Column.Type.varchar, 60);
       javascript.addColumn("parent",Column.Type.varchar,100);
       javascript.addColumn("project",Column.Type.varchar,60);    
       javascript.addColumn("body",Column.Type.varchar,1000);
       javascript.addColumn("type",Column.Type.varchar,60);
       javascript.addColumn("ord",Column.Type.integer,10);
       javascript.setHeader(new String[]{"bid","parent","project","body","type","ord"});
       conschema.addTable("javascript", javascript);
       
        add("schema_config",conschema);
    }

    
    
     public byte[] pullByteArray(String q)
    {
        return data.pullByteArray(q);
    }
    
        public Object pullObject(String q)
    {
        return data.pullObject(q);
    }
    
    public String pullString(String q)
    {
        return data.pullString(q);
    }
 
        public List<Object[]> pullObjectTable(String q)
    {
        return data.pullObjectTable(q);
    }
    
    public List<String[]> pullTable(String q)
    {
        return data.pullTable(q);
    }
    
        public List<String> pullList(String q)
    {
        return data.pullList(q);
    }
    
    @Override
    public void add(String key, Object ob)
    {
        put(key,ob);
        
        if(key.equals("data"))
        {
            if(data==null)
            {
                data = (Data)ob;
            }
        }
        
    }
    
    @Override
    public Object getObject(String key)
    {
        return this.get(key);
    }
    
    
    
}
