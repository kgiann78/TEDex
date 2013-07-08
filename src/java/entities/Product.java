/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Constantine
 */
public class Product {
    
    private int id;
    private String name;
    private int quantity;
    private String description;
    private String code;
    private double cost;
    private double price;
    private int storageId;
    private int supplierId;
    
    public Product() {
        this.id = -1;
        this.name = "";
        this.quantity = 0;
        this.description = "";
        this.code = "";
        this.cost = 0.0;
        this.price = 0.0;
        this.storageId = 0;
        this.supplierId = 0;
    }
    
    public Product(int id, String name, int quantity) {
        this.id = id;
        
        this.name = name;
        this.quantity = quantity;
        this.description = "";
        this.code = "";
        this.cost = 0.0;
        this.price = 0.0;
        this.storageId = 0;
        this.supplierId = 0;
    }        

    
    //getters
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public double getCost() {
        return this.cost;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public int getStorageId() {
        return this.storageId;
    }
     
    public int getSupplierId() {
        return this.supplierId;
    }
    
    //setters
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }
    
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
