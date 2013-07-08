/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

/**
 *
 * @author Constantine
 */
public enum Service {

    USER(1, "user", "Users' Management"),
    ROLE(2, "role", "Roles' Management"),
    STORAGE(3, "storage", "Storages' Management"),
    PRODUCT(4, "product", "Products' Management"),
    SUPPLIER(5, "supplier", "Suppliers' Management");
    private int id;
    private String name;
    private String description;

    private Service(int code, String name, String description) {
        this.id = code;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
