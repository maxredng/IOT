/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc;

import com.iot.misc.data.Query;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bsati
 */
public class Config {
    
    Map<String,Query> queries;
    
    public Config()
    {
        queries = new HashMap<>();
    }
    
    public void set(String k, Query q)
    {
        queries.put(k, q);
    }
    
    public Query get(String k)
    {
        Query result = null;
        
        queries.get(k);
        
        return result;
    
    }
    
}
