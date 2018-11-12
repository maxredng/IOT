/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web;

import com.iot.misc.Feed;
import com.iot.pellet.IOT;
import com.iot.pellet.Magazine;
import com.iot.pellet.Tester;
import com.iot.pellet.Testo;
import com.iot.web.tag.Attribute;
import com.iot.web.tag.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author bsati
 */
public class HTML extends IOT{
    
    String html;
    public List<Element> item;
    String rootboss;

    public static String project;
    public static String scope;
    public static String scop;
    private Feed feed;
    public static Feed f;
    public HTML(String ht)
    {
    html = ht;
    
    item = new ArrayList<>();
    }
    
    @Override
    public void set(Magazine m)
    {
    magazine = m;
    feed = (Feed)m;
    f = feed;
    populate();
    }
    
    @Override
    public Magazine get()
    {
        return magazine;
    }
    
    @Override
    public void populate()
    {
        
        project = (String)feed.get("project");
     
        scope = (String)feed.get("scope");
       
        scop = scope;
        org.jsoup.nodes.Element doc = (org.jsoup.nodes.Element)Jsoup.parse(html);
        Elements elm = doc.getElementsByTag("body");
        org.jsoup.nodes.Element bod = elm.get(0);
        Elements el = bod.children();
        for(org.jsoup.nodes.Element element:el)
        {
            Element ele = getElement(element);
        
            ele.project = project;
            ele.scope =scope;
            ele.feed = feed;
            item.add(ele);
            
         //   element.attributes().forEach(a -> ele.attributes.add(new Attribute(a.getKey(),a.getValue())));
            
        }
    
       
        
    }
    
    @Override
    public Tester getTest()
    {
    File ht = new File("newhtml.html");
    String s = "";
    try
    {
    s = FileUtils.readFileToString(ht);
    }catch(Exception x)
    {
        error = x.getMessage();
    }
    Feed feed = new Feed();
    feed.init();
    feed.add("boss", "one");
    feed.add("id", "oneid");
    feed.add("project", "admin");
    feed.add("scope", "constructor");
    HTML h = new HTML(s);
    h.set(feed);
    h.item.forEach(e -> e.save());
    
    // load from html and save as element
    //
    //
    test = new Testo();
    test.addTest("test that html can be parsed into list of Elements", h.item.size(), 1);
        
    return test;
    }
    
    

    
    
    public static Element getElement(org.jsoup.nodes.Element element)
    {
        Element ele = new Element();
        ele.type = element.tagName();
        ele.id = element.id();
        ele.project = project;
        ele.scope = scope;
        ele.feed = f;
        element.attributes().forEach(a -> ele.setAttribute(a.getKey(), a.getValue()));
        
        Elements elements = element.children();
        
        elements.forEach(l -> ele.item.add(getElement(l)));
        if(ele.item.isEmpty())
        {
            ele.value = element.text();
        }
        return ele;
    }
    
    
}
