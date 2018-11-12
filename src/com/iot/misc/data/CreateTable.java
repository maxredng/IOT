/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.pellet.Tester;

/**
 *
 * @author bsati
 */
public class CreateTable extends DataElement{
    
    String schema;
    String table;
    String query;
    
    public CreateTable(String db,String sc)
    {
        schema = db;
        table = sc;
        query = "create table if not exists ?";
    }

    @Override
    public void populate()
    {
        
    }
    
    @Override
    public Tester getTest()
    {
        return null;
    }
}
