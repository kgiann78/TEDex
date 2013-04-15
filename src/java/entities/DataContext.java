/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionPool;

/**
 *
 * @author Constantine
 */
public class DataContext {
    private Connection con;
    
    public List<User> getUsers(String whereClause) {
        try {
            ArrayList<User> users = new ArrayList<User>();
            
            con = ConnectionPool.INSTANCE.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "SELECT * FROM user";
            if (whereClause != null && !whereClause.isEmpty()) {
                strSQL = strSQL + " " + whereClause;
            }
            ResultSet rs = stmt.executeQuery(strSQL);

            while( rs.next() ) 
            {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
            }
            con.close();
            return users;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void addUser(User user) {
        try {
            con = ConnectionPool.INSTANCE.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "INSERT INTO user (username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')";
            
            stmt.executeUpdate(strSQL);
            
            con.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
