/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import extensions.Permission;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Constantine
 */
public class User {
    private int id;
    
    private String name;    
    private String surname;
    private String email;
    
    private String username;
    private String password;
    
    private ArrayList<Role> roles;
    
    /**
     * A default user of the system
     */
    public User() {
        this.id = -1;
        this.name = "";
        this.surname = "";
        this.email = "";
        
        this.username = "";
        this.password = "";
        this.roles = new ArrayList<Role>();
    }
    
    /**
     * A default user of the system
     * @param id 
     * @param username
     * @param password 
     */
    public User(int id, String username, String password) {
        this.id = id;
        
        this.name = "";
        this.surname = "";
        this.email = "";
        
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<Role>();
    }
    
    //getters
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }

    public ArrayList<Role> getRoles() {
        return this.roles;
    }
    
    //setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
