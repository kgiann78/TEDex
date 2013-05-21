/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;


/**
 *
 * @author Constantine
 */
public class User {
    private int id;
    private String username;
    private String password;
    private ArrayList<Role> roles;
    
    public User() {
        this.id = -1;
        this.username = "";
        this.password = "";
        this.roles = new ArrayList<Role>();
    }
    
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<Role>();
    }
    
    //getters
    public int getId() {
        return this.id;
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
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }
}
