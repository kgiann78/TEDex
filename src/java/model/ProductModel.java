/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Product;
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
public class ProductModel {

    private Connection con;

    public List<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String productQuery = "SELECT * FROM `products`;";

        ResultSet productResultSet = stmt.executeQuery(productQuery);

        while (productResultSet.next()) {
            Product product = new Product(productResultSet.getInt("id"), productResultSet.getString("name"), productResultSet.getInt("quantity"));
            product.setCode(productResultSet.getString("code"));
            product.setDescription(productResultSet.getString("description"));
            product.setCost(productResultSet.getDouble("cost"));
            product.setPrice(productResultSet.getDouble("price"));
            product.setStorageId(productResultSet.getInt("storageId"));
            product.setSupplierId(productResultSet.getInt("supplierId"));
            products.add(product);
        }
        con.close();
        return products;
    }

    public List<Product> getProductsByStorage(int storageId) throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String productQuery = "SELECT * FROM `products` WHERE `products`.`storageId` = " + storageId + ";";

        ResultSet productResultSet = stmt.executeQuery(productQuery);

        while (productResultSet.next()) {
            Product product = new Product(productResultSet.getInt("id"), productResultSet.getString("name"), productResultSet.getInt("quantity"));
            product.setCode(productResultSet.getString("code"));
            product.setDescription(productResultSet.getString("description"));
            product.setCost(productResultSet.getDouble("cost"));
            product.setPrice(productResultSet.getDouble("price"));
            product.setStorageId(productResultSet.getInt("storageId"));
            product.setSupplierId(productResultSet.getInt("supplierId"));

            products.add(product);
        }
        con.close();
        return products;
    }
    
    public List<Product> getProductsBySupplier(int supplierId) throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String productQuery = "SELECT * FROM `products` WHERE `products`.`supplierId` = " + supplierId + ";";

        ResultSet productResultSet = stmt.executeQuery(productQuery);

        while (productResultSet.next()) {
            Product product = new Product(productResultSet.getInt("id"), productResultSet.getString("name"), productResultSet.getInt("quantity"));
            product.setCode(productResultSet.getString("code"));
            product.setDescription(productResultSet.getString("description"));
            product.setCost(productResultSet.getDouble("cost"));
            product.setPrice(productResultSet.getDouble("price"));
            product.setStorageId(productResultSet.getInt("storageId"));
            product.setSupplierId(productResultSet.getInt("supplierId"));
            products.add(product);
        }
        con.close();
        return products;
    }

    public Product getProductById(int id) throws SQLException {
        Product product = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT * FROM `products` WHERE `products`.`id` = " + id + ";";
        ResultSet rs = stmt.executeQuery(strSQL);

        while (rs.next()) {
            product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"));
            product.setCode(rs.getString("code"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setCost(rs.getDouble("cost"));
            product.setStorageId(rs.getInt("storageId"));
            product.setSupplierId(rs.getInt("supplierId"));
        }
        con.close();
        return product;
    }
    
    public boolean exists(String name, String code) throws SQLException {
        Product product = null;

        con = DataContext.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "SELECT count(*) FROM `products` WHERE `products`.`name` = '" + name 
                + "' AND `products`.`code` = '" + code + "';";
        int count = 0;

        ResultSet resultSet = stmt.executeQuery(strSQL);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        con.close();
        
        if (count > 0) return true;
        else return false;
    }

    public void addProduct(Product product) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "INSERT INTO `products` (`name`, `code`, `quantity`, `description`, `cost`, `price`, `storageId`, `supplierId`) VALUES ('"
                + product.getName() + "', '"
                + product.getCode() + "', '"
                + product.getQuantity() + "', '"
                + product.getDescription() + "', '"
                + product.getCost() + "', '"
                + product.getPrice() + "', '"
                + product.getStorageId() + "', '"
                + product.getSupplierId() + "')";

        stmt.executeUpdate(strSQL);
        con.close();
    }

    public void deleteProduct(int id) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "DELETE FROM `products` WHERE `id` = " + id;
        stmt.executeUpdate(strSQL);

        con.close();
    }

    public void updateProduct(Product product) throws SQLException {
        con = ConnectionPool.INSTANCE.getConnection();
        Statement stmt = con.createStatement();
        String strSQL = "UPDATE `products` "
                + "SET `name` = '" + product.getName()
                + "', `code` = '" + product.getCode()
                + "', `description` = '" + product.getDescription()
                + "', `quantity` = '" + product.getQuantity()
                + "', `price` = '" + product.getPrice()
                + "', `cost` = '" + product.getCost()
                + "', `storageId` = '" + product.getStorageId()
                + "', `supplierId` = '" + product.getSupplierId() + "' "
                + "WHERE `products`.`id` = " + product.getId() + ";";
        stmt.executeUpdate(strSQL);
        con.close();
    }
}
