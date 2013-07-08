/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Storage;
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
public class StorageModel {
    private Connection con;
    
    public List<Storage> getStorages() throws SQLException {
        ArrayList<Storage> storages = new ArrayList<Storage>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String storageQuery = "SELECT * FROM `storages`";

        ResultSet storageResultSet = stmt.executeQuery(storageQuery);

        while (storageResultSet.next()) {
            Storage storage = new Storage(storageResultSet.getInt("id"), storageResultSet.getString("name"));
            storage.setLocation(storageResultSet.getString("location"));
            storage.setCapacity(storageResultSet.getInt("capacity"));
            storage.setQuantity(calculateQuantity(storageResultSet.getInt("id")));
            storage.setStandby(storageResultSet.getBoolean("standby"));

            storages.add(storage);
        }
        con.close();
        return storages;
    }
    
    public Storage getStorageById(int id) throws SQLException {
        Storage storage = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `storages` WHERE `storages`.`id` = " + id + ";";
        ResultSet storageResultSet = stmt.executeQuery(strSQL);

        while (storageResultSet.next()) {
            storage = new Storage(storageResultSet.getInt("id"), storageResultSet.getString("name"));
            storage.setLocation(storageResultSet.getString("location"));
            storage.setCapacity(storageResultSet.getInt("capacity"));
            storage.setQuantity(calculateQuantity(storageResultSet.getInt("id")));
            storage.setStandby(storageResultSet.getBoolean("standby"));

        }
        con.close();
        return storage;
    }
    
    public boolean exists(String name) throws SQLException {
        Storage storage = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT count(*) FROM `storages` WHERE `storages`.`name` = '" + name + "'";
        int count = 0;

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        con.close();
        
        if (count > 0) return true;
        else return false;
    }
    
    public int calculateQuantity(int storageId) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT SUM(quantity) from `products` WHERE `products`.`storageId` = " + storageId;
        int count = 0;

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        con.close();
        return count;
    }
    
    public void addStorage(Storage storage) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `storages` (`name`, `location`, `capacity`, `standby`) VALUES ('"
                + storage.getName() + "', '"
                + storage.getLocation() + "', '"
                + storage.getCapacity() + "', '"
                + storage.getStandby() + "')";

        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void deleteStorage(int id) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "DELETE FROM `storages` WHERE `id` = " + id;
        stmt.executeUpdate(strSQL);

        con.close();
    }
    
    public void updateStorage(Storage storage) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `storages` "
                + "SET `name` = '" + storage.getName()
                + "', `location` = '" + storage.getLocation()
                + "', `capacity` = '" + storage.getCapacity()
                + "', `standby` = '" + storage.getStandby()+ "' "
                + "WHERE `storages`.`id` = " + storage.getId();

        stmt.executeUpdate(strSQL);
        con.close();
    }
}
