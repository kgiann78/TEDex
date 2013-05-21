/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Constantine
 */
public class Role {
    private int id;
    private String name;
    private String description;

    public Role() {
        this.id = -1;
        this.name = "";
        this.description = "";
    }

    public Role(String name) {
        this.id = -1;
        this.name = name;
        this.description = "";
    }

    public Role(String name, String description) {
        this.id = -1;
        this.name = name;
        this.description = description;
    }

    //getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}