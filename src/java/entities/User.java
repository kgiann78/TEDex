/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Constantine
 */
public class User {
    private int id;
    private String username;
    private String password;
    
    public User() {
        this.id = -1;
        this.username = "";
        this.password = "";
    }
    
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
}
