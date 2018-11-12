/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot.web.tag;
import com.iot.misc.Feed;
import com.iot.misc.Helper;
import com.iot.misc.data.Query;
import com.iot.misc.data.Schema;
import com.iot.misc.data.Table;
import com.iot.pellet.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author bsati
 */
public class Element extends IOT implements Serializable{
    
    
    public Feed feed;
    private String parent;
    private String element;
    public String name;
    public String scope;
    public String order;
    public String column;
    public String signature;
    public String id;
    public String skinId;
    public String boss;
    public String project;
    public List<String> spine;
    public List<Object> tune;
    public List<Element> item;
    public String htmlType;
    public List<Attribute> attributes;
    public Element skin;
    public String code;
    public String type;
    public String isel;
    public String value;
    public int row,col;
    
    public Element(String d,String bss,Feed f)
    {
        skinId = d;
        boss = bss;
        popElement(f);
        
    }
    
    public Element()
    {
        attributes = new ArrayList<>();
        item = new ArrayList<>();
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
   
   @Override
   public void populate()
   {
       
       boolean signed = true;
       tune = new ArrayList<>();
       item = new ArrayList<>();
       feed = (Feed)magazine;
       id = (String)feed.getObject("id");
       boss = (String)feed.getObject("boss");  
       isel = (String)feed.getObject("isel"); 
       project = (String)feed.getObject("project");
       skinId = (String)feed.getObject("skinid"); 
       signature = (String)feed.get("signature");
       String qr = "select body from config.element where id='" + skinId + "' and signature = '" + signature + "'";

       byte[] egg = feed.pullByteArray(qr);
       
       if(egg == null)
       {
           
           signed = false;
           try {
               String qry = "select body from config.element where id='" + skinId + "'";
               egg = feed.pullByteArray(qry);

           } catch (Exception ex) {
               Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
                try {
                      ObjectInputStream ois = null;
                      ByteArrayInputStream bis = new ByteArrayInputStream(egg);
                        ois = new ObjectInputStream(bis);
                        
                        skin = (Element)ois.readObject();
               } catch (Exception ex) {
                   Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
               }      
       
       
       
      



            this.parent = skin.parent;
            this.element = skin.element;
            this.order = skin.order;
            this.column = skin.column;
            this.htmlType = skin.htmlType;
            this.attributes = skin.attributes;
        //    this.feed = skin.feed;
            this.signature = skin.signature;
        //    this.id = skin.id;
            this.skinId = skin.skinId;
         //   this.boss = skin.boss;
            this.tune = skin.tune;    
            this.item = skin.item;
            this.code = skin.code;
            this.type = skin.type;
            this.isel = skin.isel;

       if(!signed)
       {
       initWithSignature();
       }
       
   }
   
  
   @Override
   public Tester getTest()
   {
      String sign = "test";
      
      Feed d = new Feed();
      d.init();
      d.add("boss", "");
      d.add("id", id);
      d.add("skinid", "0");
      d.add("isel", "mytest");
      d.add("project", "myproject");
      d.add("signature", sign);
          

    Element el = new Element();

      el.set(d);
      
     String cd = el.getCode();
      
      
     String str = el.item.get(0).id = "1";
      
      
      
      
      test.addTest("verify element loaded", el.item.size(), 18);
      
      
      
       return test;
   }
   //populate element with boss structure
    
   public void pull()
   {
       Byte[] skin = null;
       
       String qr = "select body from config.element where id = '" + skinId + "' ";
       String y = "and signature = '" + signature + "'";
       String query = qr+y;
       
       List<Object[]> ot = feed.pullObjectTable(query);
       if(ot.size()<1)
       {
           ot = feed.pullObjectTable(qr);
       }           
       
        skin = (Byte[])ot.get(0)[3];
   
   }
   
   
   void addAttribute(String a,String b)
   {
       Attribute at = new Attribute(a,b);
       at.set(feed);
       attributes.add(at);
   
   }
   
    /**
     * populates element with content based on id,boss or parameters (signature)
     */
    public void revive()
   {
        feed.add("element", this);
        attributes.forEach(at -> addAttribute(at.key,at.value));
        
        if(type.equals("string"))
        {
            addValue();
        }
        
        item.forEach(el -> el.revive());
        
   }
   
   public void save()
   {
         ByteArrayOutputStream baos;
         ObjectOutputStream out;
         baos = new ByteArrayOutputStream();
         try {
         out = new ObjectOutputStream(baos);
         out.writeObject(this);
         out.close();
         } catch (IOException e) {
         e.printStackTrace();
         }
          
         byte[] byteObject = baos.toByteArray();
         
         Schema db = (Schema)feed.get("schema_config");
         Table tab = db.getTable("element");
         tab.setColumn("id", id);
         tab.setColumn("parent", parent);
         tab.setColumn("signature", signature);
         tab.setColumn("body", byteObject);
         tab.setColumn("scope", scope);
         tab.setColumn("project", project);
         tab.setColumn("isel", isel);
         
         tab.insertBytes();
   }
   
   public String getCodeHead()
   {
       String result = "<" + type + " ";
       
       for(Attribute at:attributes)
       {
           at.set(feed);
         
           result = result + at.code + " ";
       }
       result = result.substring(0,result.length()-1) + ">";
       return result;
   }
   
   public String getCode()
   {
       String result = "";
       result = result + getCodeHead();
       if(type.equals("string"))
       {
           result = value;
       }else
       {
           for(Element el:item)
           {
 
               result = result + el.getCodeHead();
               result = result + el.getCode();
               result = result + el.getCodeTail();
           
           }
       }
       result = result + getCodeTail();
       return result;
   }
   
   
public String getCodeTail()
{
    return "</" + type + ">";
}
   
   void getSignature()
   {
        
   }

    private void addValue() 
    {
        Query q = new Query(isel,this);
        q.set(feed);
        value = feed.pullString(q.query);
        
        
    }

    private void popElement(Feed f) 
    {
        item = new ArrayList<>();
       if(feed == null)
       {
           feed = f;
           feed.init();   
       }
       
   
        
       String qr = "select * from config.html where eid = '" + skinId + "'";
       String[] html = feed.pullTable(qr).get(0);
       
       Schema con = (Schema)feed.get("schema_config");
       Table tab = con.getTable("html");
       
       tab.populateColumns(html);
            this.signature = (String)feed.get("signature");
            this.parent = (String)tab.getValue("parent");
            this.type = (String)tab.getValue("element");
            this.element = (String)tab.getValue("element");
            this.id = (String)tab.getValue("id");
            this.name = (String)tab.getValue("name");
            this.scope = (String)tab.getValue("scope");
            this.project = (String)tab.getValue("project");
            this.htmlType = (String)tab.getValue("type");  // TODO: Continue here
            this.row = Integer.parseInt((String)tab.getValue("row"));
           
            this.col = Integer.parseInt((String)tab.getValue("col"));
            
  
            this.isel = (String)tab.getValue("isel");
            
            if(!Helper.isThing(value))
            {
                String chiq = "select eid from config.html where parent = '" + skinId + "' order by row,col";
                
                Query q = new Query(isel,this);
                q.set(feed);
                if(element.equals("drum"))
                {
                    
                    
                    
                    List<String> drum = feed.pullList(q.query);
                    
                    String chid = feed.pullString(chiq);
                                        
                    drum.forEach(s -> item.add(new Element(chid,s,feed)));
                     
                }else if(element.equals("normal"))
                 {
                     List<String> cont = feed.pullList(chiq);
                     
                     cont.forEach(s -> item.add(new Element(s,boss,feed)));
                 
                 }else if(element.equals("string"))
                 {
                     value = feed.pullString(q.query);

                 }
                     
            }
                addAttributes();

    }
   
        void addAttributes()
        {
            attributes = new ArrayList<>();
            String aq = "select * from config.attribute where parent = '" + skinId + "'";
            List<String[]> l = feed.pullTable(aq);
            l.forEach(s -> attributes.add(new Attribute(s,this)));
            
        }


    private void initWithSignature() {

        switch(element)
        {
            case "drum":
            {
                if(item.size()>0)
                {
                    Element el = item.get(0);
                    el.feed = feed;
                    Query qr = new Query(isel,this);
                    qr.set(feed);
                    
                    List<String> bosses = feed.pullList(qr.query);
                    
                    for(String s:bosses)
                    {
                        Element elem = el;
                        elem.boss = s;
                        elem.initWithSignature();
                        el.item.add(el);
                    }
                }
            
                break;
            }
            case "string":
            {
            
                Query qr = new Query(isel,this);
                qr.set(feed);
                value = feed.pullString(qr.query);
                break;
            }        

        }
        
        addAttributes();
    }
   
        public void setAttribute(String k,String v)
    {
         
            if(!k.equals("isel"))
            {
                attributes.add(new Attribute(k,v));
            }else if (!k.equals("vtype"))
            {
                isel = v;
                
            }else
            {
                type = v;
            }
       
    }
}
