/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.misc.data;

import com.iot.misc.Feed;
import com.iot.pellet.IOT;
import com.iot.pellet.Magazine;
import com.iot.pellet.Tester;

/**
 *
 * @author bsati
 */
public abstract class DataElement extends IOT{
    
    public Feed feed;
    public Data data;
    
    @Override
    public void set(Magazine m)
    {
        magazine = m;
        feed = (Feed)magazine;
        data = (Data)feed.get("data");
        populate();
    }
    
    @Override
    public Magazine get()
    {
        return magazine;
    }
    
    @Override
    public abstract void populate();
    
    
    @Override
    public abstract Tester getTest();
}
