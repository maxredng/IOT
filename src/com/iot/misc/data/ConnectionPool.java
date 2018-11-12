
package com.iot.misc.data;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author bsati
 */
public class ConnectionPool implements Serializable{
    
    transient BasicDataSource source;
  
    public String database;
    final String name;
    final String password;
    
    public ConnectionPool(String db,String nm,String ps)
    {
        database = db;
        name = nm;
        password = ps;
    }
    
    public Connection getConnection()
    {
        Connection result = null;
        BasicDataSource dataSource = getDataSource();
          
        try {
            

            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public BasicDataSource getDataSource()
    {
   
            BasicDataSource ds = null;
         
        try {
            ds = new BasicDataSource();
            //         ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://127.0.0.1:3306/" + database);
            ds.setUsername(name);
            ds.setPassword(password);
//            ds.setMaxIdle(5);
//            ds.setMaxIdle(10);
//            ds.setMaxOpenPreparedStatements(100);
            
            source = ds;
        } catch (Throwable e) {
            e.printStackTrace();
        }
                  
      
    
    return source;
    
    }
    
    
    
}
