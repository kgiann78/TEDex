/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Constantine
 */
public class UserModel {
    private Connection con;
    
    public List<User> getUsers() {
        try {
            ArrayList<User> users = new ArrayList<User>();

            con = DataContext.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "SELECT * FROM user";

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

    public User getUserByName(String username) {
        try {
            User user = null;

            con = DataContext.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "SELECT * FROM user WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(strSQL);

            while( rs.next() )
            {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }

            con.close();
            return user;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            User user = null;

            con = DataContext.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "SELECT * FROM user WHERE id = " + id + ";";
            ResultSet rs = stmt.executeQuery(strSQL);

            while( rs.next() )
            {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            con.close();
            return user;
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
    
    public void deleteUser(int id) {
        try {
            User tmp = this.getUserById(id);
            con = ConnectionPool.INSTANCE.getConnection();
            Statement stmt = con.createStatement();
            String strSQL = "DELETE FROM user WHERE id = '" + tmp.getId() + "'";
            
            stmt.executeUpdate(strSQL);
            
            con.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
