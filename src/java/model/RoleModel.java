/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Role;
import extensions.Permission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Constantine
 */
public class RoleModel {

    private Connection con;

    public ArrayList<Role> getRoles() throws SQLException {
        ArrayList<Role> roles = new ArrayList<Role>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String roleQuery = "SELECT * FROM `roles`";

        ResultSet roleResultSet = stmt.executeQuery(roleQuery);

        while (roleResultSet.next()) {
            Role role = new Role(roleResultSet.getString("name"), roleResultSet.getString("description"));
            role.setId(roleResultSet.getInt("id"));
            addPermission(role);
            roles.add(role);
        }
        
        roleResultSet.close();
        stmt.close();
        con.close();
        return roles;
    }

    public Role getRoleById(int id) throws SQLException {
        Role role = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String roleQuery = "SELECT * FROM `roles` WHERE `roles`.`id` = " + id;
        ResultSet roleResultSet = stmt.executeQuery(roleQuery);

        while (roleResultSet.next()) {
            role = new Role(roleResultSet.getString("name"), roleResultSet.getString("description"));
            role.setId(roleResultSet.getInt("id"));
            addPermission(role);
        }
        
        stmt.close();
        con.close();
        return role;
    }
    
    public ArrayList<Permission> getPermissions() throws SQLException {
        ArrayList<Permission> permissions = new ArrayList<Permission>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `permissions`";

        ResultSet resultSet = stmt.executeQuery(strSQL);

        while (resultSet.next()) {
            Permission permission = Permission.valueOf(resultSet.getString("name"));
            permissions.add(permission);
        }
        
        resultSet.close();
        stmt.close();
        con.close();
        return permissions;
    }
    
    /**
     * Includes a permission from database into a role instance
     * @param role
     * @throws SQLException 
     */
    private void addPermission(Role role) throws SQLException {
        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String permissionQuery = "SELECT `permissions`.`name` FROM `permissions`,"
                + "`roleHasPermissions` WHERE `roleHasPermissions`.`permissionId` = `permissions`.`id` "
                + "AND `roleHasPermissions`.`roleId` = " + role.getId();
        ResultSet permissionResultSet = stmt.executeQuery(permissionQuery);

        while (permissionResultSet.next()) {
            role.setPermission(Permission.valueOf(permissionResultSet.getString("name")));
        }
        
        permissionResultSet.close();
        stmt.close();
        con.close();
    }
    
    public int getRoleId(String name) throws SQLException{
        int roleId = -1;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String roleQuery = "SELECT * FROM `roles` WHERE `roles`.`name` = '" + name + "'";
        ResultSet roleResultSet = stmt.executeQuery(roleQuery);

        while (roleResultSet.next()) {
            roleId = roleResultSet.getInt("id");
        }
        
        roleResultSet.close();
        stmt.close();
        con.close();
        return roleId;
    }

    public ArrayList<Role> getRolesByUser(int userId) throws SQLException {
        ArrayList<Role> roles = new ArrayList<Role>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT `userHasRoles`.`roleId` "
                + "FROM `userHasRoles` "
                + "WHERE `userHasRoles`.`userId` = " + userId;

        ResultSet roleResultSet = stmt.executeQuery(strSQL);

        while (roleResultSet.next()) {
            roles.add(getRoleById(roleResultSet.getInt("roleId")));
        }
        
        roleResultSet.close();
        stmt.close();
        con.close();
        return roles;
    }
    
    /**
     * adds a new role to the roles table
     *
     * @param role
     * @throws SQLException
     */
    public void addRole(Role role) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        ResultSet generatedKeys = null;
        
        String strSQL = "INSERT INTO `roles` (`name`, `description`) VALUES ('"
                + role.getName() + "', '"
                + role.getDescription() + "')";
        
        int affectedRows = stmt.executeUpdate(strSQL);
        if (affectedRows == 0) {
            throw new SQLException("Creating role failed, no rows affected.");
        }
        
        stmt.close();
        con.close();
    }

    /**
     * update a role in the roles table only
     *
     * @param role
     * @throws SQLException
     */
    public void updateRole(Role role) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `roles` "
                + "SET `name` = '" + role.getName()
                + "', `description` = '" + role.getDescription() + "' "
                + "WHERE `roles`.`id` = " + role.getId();

        stmt.executeUpdate(strSQL);
        
        stmt.close();
        con.close();
    }

    /**
     * deletes the specified role completely from the database
     *
     * @param id the id of the role instance
     * @throws SQLException
     */
    public void deleteRole(int id) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String permissionSQL = "DELETE FROM `roleHasPermissions` WHERE `roleHasPermissions`.`roleId` = " + id;
        String strSQL = "DELETE FROM `roles` WHERE `roles`.`id` = " + id;
        stmt.executeUpdate(permissionSQL);
        stmt.executeUpdate(strSQL);
        stmt.close();
        con.close();
    }
    
    public boolean checkRole(int userId, int roleId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        int count = 0;
        String strSQL = "SELECT count(*) FROM `userHasRoles` WHERE `userId` = " + userId
                + " AND `roleId` = " + roleId;

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        
        resultSet.close();
        stmt.close();
        con.close();
        if (count > 0)
            return true;
        else
            return false;
    }

    /**
     * sets a role for a user
     *
     * @param userId the id of the user instance
     * @param roleId the id of the role to be added
     * @throws SQLException
     */
    public void setRole(int userId, int roleId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `userHasRoles` (`userId`, `roleId`) VALUES ('"
                + userId + "', '"
                + roleId + "')";
        if (!checkRole(userId, roleId)) {
            stmt.executeUpdate(strSQL);
        }
        stmt.close();
        con.close();
    }

    /**
     * removes a specified role from user
     *
     * @param userId the id of the user instance
     * @param roleId the id of the role to be removed
     * @throws SQLException
     */
    public void removeRole(int userId, int roleId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "DELETE FROM `userHasRoles` WHERE "
                + "`userHasRoles`.`userId` = " + userId + " AND "
                + "`userHasRoles`.`roleId` = " + roleId;
        stmt.executeUpdate(strSQL);
        
        stmt.close();
        con.close();
    }
    
    public boolean checkPermission(int roleId, Permission permission) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        int count = 0;
        String strSQL = "SELECT count(*) FROM `roleHasPermissions` WHERE `roleId` = " + roleId
                + " AND `permissionId` = " + permission.getId();

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        
        resultSet.close();
        stmt.close();
        con.close();
        if (count > 0)
            return true;
        else
            return false;
    }

    /**
     * sets a permission to a specified role
     *
     * @param roleId the id of the role instance
     * @param permission the permission to be added
     * @throws SQLException
     */
    public void setPermission(int roleId, Permission permission) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `roleHasPermissions` (`roleId`, `permissionId`) VALUES ('"
                + roleId + "', '"
                + permission.getId() + "')";
        
        if (!checkPermission(roleId, permission)) {
            stmt.executeUpdate(strSQL);
        }
        stmt.close();
        con.close();
    }

    /**
     * removes a permission from a specified role
     *
     * @param roleId the id of the role instance
     * @param permission the permission to be removed
     * @throws SQLException
     */
    public void removePermission(int roleId, Permission permission) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "DELETE FROM `roleHasPermissions` WHERE "
                + "`roleHasPermissions`.`roleId` = " + roleId + " AND "
                + "`roleHasPermissions`.`permissionId` = " + permission.getId();
        stmt.executeUpdate(strSQL);
        
        stmt.close();
        con.close();
    }
}
