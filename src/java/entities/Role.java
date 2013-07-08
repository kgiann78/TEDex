/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import extensions.Permission;
import java.util.ArrayList;


/**
 *
 * @author Constantine
 */
public class Role {
    private int id;
    private String name;
    private String description;
    private ArrayList<Permission> permissions;

    public Role() {
        this.id = -1;
        this.name = "";
        this.description = "";
        this.permissions = new ArrayList<Permission>();
    }

    public Role(String name) {
        this.id = -1;
        this.name = name;
        this.description = "";
        this.permissions = new ArrayList<Permission>();
    }

    public Role(String name, String description) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.permissions = new ArrayList<Permission>();
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
    
    public ArrayList<Permission> getPermissions() {
        return this.permissions;
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
    
    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }
    
    public void setPermission(Permission permission) {
        this.permissions.add(permission);
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean equal = false;

        if (object != null && object instanceof Role)
        {
            equal = this.id == ((Role) object).id;
        }

        return equal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }
}