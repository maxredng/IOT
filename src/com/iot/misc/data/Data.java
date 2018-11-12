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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsati
 */
public class Data extends IOT implements Serializable{
    
    String user;
    String password;
    private List<ConnectionPool> pool;
    
    int getColumnsNum()
    {
        int result = 0;
        
        
        return result;
    
    }
    

    
    ConnectionPool getPool(String query)
    {
            ConnectionPool result = null;
            ConnectionPool connectionPool = null;
            Query q = new Query(query);
            String schema = q.schemas[0];
            if(pool == null)
            {
                pool = new ArrayList<>();
                connectionPool = new ConnectionPool(schema,user,password);
                pool.add(connectionPool);
                
            }else
            {
                for(ConnectionPool p:pool)
                {
                    if(p.database.equals(schema))
                    {
                        connectionPool = p;
                        break;
                    }
                }
                
                if(connectionPool == null)
                {
                    connectionPool = new ConnectionPool(schema,user,password);
                    pool.add(connectionPool);
                }
            }  
        result = connectionPool;
        return result;
    }
    
        public List<Object[]> pullObjectTable(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object[]> result = new ArrayList<>();
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            rs = ps.executeQuery();
    
    
    List<Object> l = new ArrayList<>();
    
    while(rs.next())
    {
    String[] temp= null;
    
    int i = 0;
    while(true)
    {
        
        try {
            
            l.add(rs.getObject(i));
            i++;
        } catch (SQLException sQLException) {
            
            break;
        }
    
    }
    temp = l.toArray(new String[l.size()]);
    result.add(temp);
    }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }    
        
        
     public List<String> pullList(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Query qr = new Query(q);
   //     qr.set(new Feed());
        
        List<String> result = new ArrayList<>();
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            try {
                ps = connection.prepareStatement(q);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            
            rs = ps.executeQuery();
    
    
    List<String> l = new ArrayList<>();
    
    while(rs.next())
    {
        result.add(rs.getString(qr.columns[0]).replace("'", ""));

    }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }
        
        
        
        public List<String[]> pullTable(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String[]> result = new ArrayList<>();
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            rs = ps.executeQuery();
    
    
    List<String> l = new ArrayList<>();
    
    while(rs.next())
    {
    String[] temp= null;
    
    int i = 1;
    while(true)
    {
        
        try {
            
            l.add(rs.getString(i));
            i++;
        } catch (SQLException sQLException) {
            i=1;
            break;
        }
    
    }
    temp = l.toArray(new String[l.size()]);
    l.clear();
    result.add(temp);
    }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }
   
        
                    public Object pullObject(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object result = null;
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                if(result == null)
                {
                result = rs.getObject(1);
                break;
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                if(ps!=null)
                ps.close();
                if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }
    
        
        
        
     public byte[] pullByteArray(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        
        Query query = new Query(q);
        
        ResultSet rs = null;
        byte[] result = null;
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                if(result == null)
                {
                result = rs.getBytes(query.columns[0]);
               break;
                }
                
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Throwable ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                if(ps!=null)
                ps.close();
                 if(rs!=null)
                 rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }
    
    public String pullString(String q)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                if(result == null)
                {
                result = rs.getString(1);
                break;
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                if(connection != null)
                {
                connection.close();
                }
                if(ps!=null)
                ps.close();
                if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return result;
    }
    
    
    public boolean ExecuteBytes(String q, Column[] cl)
    {
       boolean result = false;

       Query query = new Query(q);
       String schema = query.schemas[0];
       
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
      
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
             int i = 1;
            for(Column col:cl)
            {
               switch(col.type)
               {
                   case varchar:
                   {
                       ps.setString(i, (String)col.value);
                   
                   break;
                   }
                     case integer:
                   {
                   ps.setInt(i, (int)col.value);
                   
                   break;
                   }             
                   case object:
                   {
                   ps.setObject(i, col.value);
                   
                   break;
                   }   
                   case blob:
                   {
                       ps.setBytes(i, (byte[])col.value);
                       break;
                   }
               }
                
                
                
                
               i++; 
            }
            
            ps.executeUpdate();
         }catch(Exception x)
        {
            result = false;
        }finally
        {
            try {
                if(connection!=null)
                connection.close();
                if(ps!=null)
                {
                ps.close();
                }
                if(rs!=null)
                rs.close();
            } catch (SQLException sQLException) {
            }
        }
       return result; 
    }
    
    
    public boolean Execute(String q)
    {
       boolean result = false;

       Query query = new Query(q);
       String schema = query.schemas[0];
       
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
      
        try {
            
            ConnectionPool connectionPool = getPool(q);         
            connection = connectionPool.getConnection();
            
            ps = connection.prepareStatement(q);
            
            ps.executeUpdate();
         }catch(Exception x)
        {
            result = false;
        }finally
        {
            try {
                if(connection!=null)
                connection.close();
                if(ps!=null)
                {
                ps.close();
                }
                if(rs!=null)
                rs.close();
            } catch (SQLException sQLException) {
            }
        }
       return result; 
    }
    
    @Override
    public Magazine get()
    {
    return magazine;
    
    }
    
    @Override
    public void populate()
    {
        user = (String)magazine.getObject("user");
        password = (String)magazine.getObject("password");
    
    }
    @Override
    public void set(Magazine ms)
    {
        magazine = ms;
        populate();
    }
            
    @Override
    public Tester getTest()
    {
        Tester result = null;
        String query = "select title from config.list";
        Feed f = new Feed();
        f.init();
        List<String> l = f.pullList(query);
        
        test.addTest("test that the query gets some values", l.get(0), "name");
        
        return test;
    }
}
