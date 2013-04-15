/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alkis
 */

//TROPOS KLHSHS ConnectPool.INSTANCE.getConnection();
public enum ConnectionPool 
{
    INSTANCE;
    private DataSource conPool;
    
    private ConnectionPool() 
    {
        try 
        {
            InitialContext ic = new InitialContext();
            conPool = ( DataSource ) ic.lookup("jdbc/tedex");
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() throws SQLException 
    {
        return conPool.getConnection();
    }
}
