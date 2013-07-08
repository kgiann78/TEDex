/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Supplier;
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
public class SupplierModel {
    private Connection con;
    
    public List<Supplier> getSuppliers() throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String supplyQuery = "SELECT * FROM `suppliers`";

        ResultSet supplyResultSet = stmt.executeQuery(supplyQuery);

        while (supplyResultSet.next()) {
            Supplier supplier = new Supplier(supplyResultSet.getInt("id"), 
                    supplyResultSet.getString("name"), 
                    supplyResultSet.getString("surname"));
            supplier.setCompany(supplyResultSet.getString("company"));
            supplier.setAddress(supplyResultSet.getString("address"));
            supplier.setPhone(supplyResultSet.getString("phone"));
            supplier.setEmail(supplyResultSet.getString("email"));
            suppliers.add(supplier);
        }
        con.close();
        return suppliers;
    }
    
    public Supplier getSupplierById(int id) throws SQLException {
        Supplier supplier = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `suppliers` WHERE `suppliers`.`id` = " + id + ";";
        ResultSet supplyResultSet = stmt.executeQuery(strSQL);

        while (supplyResultSet.next()) {
            supplier = new Supplier(supplyResultSet.getInt("id"), 
                    supplyResultSet.getString("name"), 
                    supplyResultSet.getString("surname"));
            supplier.setCompany(supplyResultSet.getString("company"));
            supplier.setAddress(supplyResultSet.getString("address"));
            supplier.setPhone(supplyResultSet.getString("phone"));
            supplier.setEmail(supplyResultSet.getString("email"));
        }
        con.close();
        return supplier;
    }
    
    public void addSupplier(Supplier supplier) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `suppliers` (`name`, `surname`, `company`, `address`, `phone`, `email`) VALUES ('"
                + supplier.getName() + "', '"
                + supplier.getSurname() + "', '"
                + supplier.getCompany() + "', '"
                + supplier.getAddress() + "', '"
                + supplier.getPhone() + "', '"
                + supplier.getEmail() + "')";

        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void deleteSupplier(int id) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "DELETE FROM `suppliers` WHERE `id` = " + id;
        stmt.executeUpdate(strSQL);
        con.close();
    }
    
    public void updateSupplier(Supplier supplier) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `suppliers` "
                + "SET `name` = '" + supplier.getName()
                + "', `surname` = '" + supplier.getSurname()
                + "', `company` = '" + supplier.getCompany()
                + "', `address` = '" + supplier.getAddress()
                + "', `phone` = '" + supplier.getPhone()
                + "', `email` = '" + supplier.getEmail() + "' "
                + "WHERE `supply`.`id` = " + supplier.getId();

        stmt.executeUpdate(strSQL);
        con.close();
    }
}
