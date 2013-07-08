/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Role;
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

    public List<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        RoleModel roleModel = new RoleModel();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String userQuery = "SELECT * FROM `users`";

        ResultSet userResultSet = stmt.executeQuery(userQuery);

        while (userResultSet.next()) {
            User user = new User(userResultSet.getInt("id"), userResultSet.getString("username"), userResultSet.getString("password"));
            user.setName(userResultSet.getString("name"));
            user.setSurname(userResultSet.getString("surname"));
            user.setEmail(userResultSet.getString("email"));
            user.setRoles(roleModel.getRolesByUser(userResultSet.getInt("id")));
            users.add(user);
        }
        con.close();
        return users;
    }

    public User getUserByName(String username) throws SQLException {
        User user = null;
        RoleModel roleModel = new RoleModel();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `users` WHERE `username` = '" + username + "';";
        ResultSet userResultSet = stmt.executeQuery(strSQL);

        while (userResultSet.next()) {
            user = new User(userResultSet.getInt("id"), userResultSet.getString("username"), userResultSet.getString("password"));
            user.setName(userResultSet.getString("name"));
            user.setSurname(userResultSet.getString("surname"));
            user.setEmail(userResultSet.getString("email"));
            user.setRoles(roleModel.getRolesByUser(userResultSet.getInt("id")));
        }

        con.close();
        return user;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;
        RoleModel roleModel = new RoleModel();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `users` WHERE `users`.`id` = " + id + ";";
        ResultSet userResultSet = stmt.executeQuery(strSQL);

        while (userResultSet.next()) {
            user = new User(userResultSet.getInt("id"), userResultSet.getString("username"), userResultSet.getString("password"));
            user.setName(userResultSet.getString("name"));
            user.setSurname(userResultSet.getString("surname"));
            user.setEmail(userResultSet.getString("email"));
            user.setRoles(roleModel.getRolesByUser(userResultSet.getInt("id")));
        }
        con.close();
        return user;
    }

    public void addUser(User user) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `users` (`name`, `surname`, `email`, `username`, `password`) VALUES ('"
                + user.getName() + "', '"
                + user.getSurname() + "', '"
                + user.getEmail() + "', '"
                + user.getUsername() + "', '"
                + user.getPassword() + "')";

        stmt.executeUpdate(strSQL);
        con.close();
    }

    public void updateUser(User user) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `users` "
                + "SET `name` = '" + user.getName()
                + "', `surname` = '" + user.getSurname()
                + "', `email` = '" + user.getEmail()
                + "', `username` = '" + user.getUsername()
                + "', `password` = '" + user.getPassword() + "' "
                + "WHERE `users`.`id` = " + user.getId();
        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void deleteUser(int userId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        RoleModel rModel = new RoleModel();
        User user = getUserById(userId);
        
        for (Role role : user.getRoles()) {
            rModel.removeRole(user.getId(), role.getId());
        }
        
        String strSQL = "DELETE FROM `users` WHERE `users`.`id` = " + userId;
        
        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void activateUser(int userId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `users` "
                + "SET `activated` = '1' "
                + "WHERE `users`.`id` = " + userId;
        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void deactivateUser(int userId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `users` "
                + "SET `activated` = '0' "
                + "WHERE `users`.`id` = " + userId;
        stmt.executeUpdate(strSQL);
        con.close();
    }

    public int countUsers() throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT count(*) from `users`";
        int count = 0;

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        con.close();
        return count;
    }
}
