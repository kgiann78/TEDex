/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

/**
 *
 * @author Constantine
 */
public enum Permission {
    administrator(1, "admnistrator"),
    storage_write(2, "storage write permission"),
    product_write(3, "product write permission"),
    supplier_write(4, "supplier write permission"),
    storage_read(5, "storage read permission"),
    product_read(6, "product read permission"),
    supplier_read(7, "supplier read permission");
    
    private int id;
    private String description;
    
    private Permission(int id, String description) {
        this.id = id;
        this.description = description;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    @Override public String toString() {
        return String.format("%s", this.id);
    }
}
