/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Constantine
 */
public class Storage {
    
    private int id;
    private String name;
    private String location;
    private int capacity;
    private int quantity;
    private boolean standby;
    
    public Storage() {
        this.id = -1;
        this.name = "";
        this.location = "";
        this.capacity = 0;
        this.quantity = 0;
        this.standby = false;
    }
    
    public Storage(int id, String name) {
        this.id = id;
        this.name = name;
        this.location = "";
        this.capacity = 0;
        this.quantity = 0;
        this.standby = false;
    }
    
    //getters
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public int getStandby() {
        if (this.standby)
            return 1;
        else
            return 0;
    }
    
    //setters
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setStandby(boolean standby) {
        this.standby = standby;
    }
}
