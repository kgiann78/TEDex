/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
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
            Context ic = new InitialContext();
            Context envCtx = (Context) ic.lookup("java:comp/env");
            conPool = (DataSource) envCtx.lookup("jdbc/tedex");
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() throws SQLException 
    {
        return conPool.getConnection();
    }
}
