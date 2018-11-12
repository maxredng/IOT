/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.DBEntry;
import com.iot.misc.Helper;
import com.iot.misc.Feed;
import com.iot.pellet.IOT;
import com.iot.pellet.Magazine;
import com.iot.pellet.Tester;
import com.iot.web.tag.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author bsati
 */
public class Query extends IOT{
    boolean isconcat = false;
    String type;
    String isel;
    String id;
    String boss;
    Map<String,String> parameters;
    Feed feed;
    String[] schemas;
    String[] tables;
    Element element;
    String[] columns;
    String[] fields;
    String[] values;
    String[] orders;
    public String query;
    
    private String qr = "select columns from schemas.tables where conditions order by numbers";
    
    public Query()
    {
        parameters = new HashMap<>();
    }
    
    public Query(String q,Element el)
    {

        feed = el.feed;
        feed.add("element", el);
        element = el;
        isel = q;
    
    }
    
    void handleConcat(String col)
    {
        String regex = "^concat\\((.*)\\)";
        String cl = Helper.getFromPattern(regex, col);
        if(Helper.isThing(cl))
        {
            columns = cl.split(",");
            isconcat = true;
        }
    
    }
    
    public Query(String q)
    {
    query = q;
    populateQuery();
    }
    
    @Override
    public void set(Magazine m)
    {
    magazine = m;
    populate();
    }
    
    @Override
    public Magazine get()
    {
       return magazine;
    
    }
    //TODO: add order by, getParam etc
    @Override
    public void populate()
    {
        if(feed == null)
        feed = (Feed)magazine;
            if(isel == null)
            {
                isel = (String)feed.get("isel");
            }
        id = (String)feed.get("id");
        boss = (String)feed.get("boss");        
        parameters = (Map<String,String>)feed.get("parameters");
        Data data = (Data)feed.get("data");
        
        String q = "select * from config.query where isel='" + isel + "'";
        List<String[]> l = data.pullTable(q);
        if(l.size()>0)
        {
            String[] temp = l.get(0);
            schemas = temp[2].split(",");
            tables = temp[3].split(",");
            columns = getValues(temp[1].split(","));
            if(temp[1].startsWith("concat"))
            {
                handleConcat(temp[1]);
            }
            fields = getValues(temp[4].split(","));
            values = getValues(temp[5].split(","));
            orders = temp[6].split(",");
        }
        
        query = qr.replace("columns", getColumns())
                .replace("schemas", Helper.concat(schemas, ","))
                .replace("tables", Helper.concat(tables, ","))
                .replace("conditions", Helper.concatEqual(fields, values, " and "))
                .replace("numbers", Helper.concat(orders, ","));
                        
        
        
    }
    

    private String[] getValues(String[] s)
    {
        String[] result = new String[s.length];
    
        int i = 0;
        for(String st:s)
        {
            DBEntry deb = new DBEntry(st);
            deb.set(feed);
            result[i] = deb.value;
            i++;
        }
        
        return result;
    }
    
        public static String[] getTables(String q)
    {
        String[] result = null;
    
        String reg = "select\\s+(.*?)\\s+from";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(q);
        
        while(matcher.find())
        {
             
                result = matcher.group(1).split(",");
           
        
        }
    
        return result;
    }
        
            public static String getSchema(String q)
    {
        String result = null;
        
        try
        {
             String pat = "\\s(.*?)\\s+from\\s(.*?)\\.";
            Pattern pattern = Pattern.compile(pat);
            Matcher matcher = pattern.matcher(q);
            
            while(matcher.find())
            {
                result = matcher.group(1);
            }       
        
        
        }catch(Exception x)
        {

        }

        if(result == null)
        {
            String creq = "(\\s)(\\S*)\\.";
            result = Helper.getFromPattern(creq, q);
        }
        
        return result;
    
    }

    private String getColumns() {

        
        String[] temp = new String[columns.length];
        
        int i = 0;
        for(String col:columns)
        {
             QueryColumn column = new QueryColumn(col);
             column.set(feed);
             temp[i] = column.value;
             i++;
        
        }
        
        String result = Helper.concat(temp, ",");
        if(isconcat)
        {
            result = "concat(" + result + ")";
        }
        return result;

    }
      
    @Override
    public Tester getTest()
    {
//        
//        
//        
//        
        Tester result = null;
    
        return result;
    }

    private void populateQuery() 
    {
        type = Helper.getFromPattern("(^\\S*)\\s", query);
        
        if(!type.equals("show"))
        {
        schemas = Helper.getFromPattern("\\s(\\S*)\\.", query).split(",");
        tables = Helper.getFromPattern("\\.(\\S*)\\s", query).split(",");
        columns = Helper.getFromPattern("select\\s(.*?)\\sfrom", query).split(",");
        String reg = "\\s(\\S*)='(\\S*)'";
        Pattern pat = Pattern.compile(reg);
        Matcher keymatcher = pat.matcher(query);

        List<String> fs = new ArrayList<>();
        List<String> vs = new ArrayList<>();
        while(keymatcher.find())
        {
            fs.add(keymatcher.group(1));
        }
        fields = new String[fs.size()];
        fields = fs.toArray(new String[0]);
        
        Matcher valmatcher = pat.matcher(query);
        while(valmatcher.find())
        {
            vs.add(valmatcher.group(2));
        }
        values = new String[vs.size()];
        values = vs.toArray(new String[0]);  
        
        orders = Helper.getFromPattern("order by\\s(\\S*)$", query).split(",");
    }else
        {
            String re = "show\\s(\\S*)\\sfrom\\s(\\S*)";
            
            Pattern showpat = Pattern.compile(re);
            Matcher tabmatcher = showpat.matcher(query);
            
            while(tabmatcher.find())
            {
                schemas = tabmatcher.group(2).split(",");
            }
            
       
        }

    }
    
    
    
}
