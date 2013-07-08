/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Constantine
 */
public class DataContext {
    public static Connection getConnection() throws SQLException {
        return ConnectionPool.INSTANCE.getConnection();
    }
}
