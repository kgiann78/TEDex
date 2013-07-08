/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Constantine
 */
public class Supplier {
    
    private int id;
    private String name;
    private String surname;
    private String company;
    private String address;
    private String phone;
    private String email;
    
    public Supplier() {
        this.id = -1;
        this.name = "";
        this.surname = "";
        this.company = "";
        this.address = "";
        this.phone = "";
        this.email = "";
    }
    
    public Supplier(int id, String name, String surname) {
        this.id = id;
        this.name = (name != null)?name:"";
        this.surname = (surname != null)?surname:"";
        this.company = "";        
        this.address = "";
        this.phone = "";
        this.email = "";
    }
    
    public Supplier(int id, String company) {
        this.id = id;
        this.name = "";
        this.surname = "";
        this.company = (company != null)?company:"";
        this.address = "";
        this.phone = "";
        this.email = "";
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
    
    public String getCompany() {
        return this.company;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    //setters
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = (name != null)?name:"";
    }
    
    public void setSurname(String surname) {
        this.surname = (surname != null)?surname:"";
    }
    
    public void setCompany(String company) {
        this.company = (company != null)?company:"";
    }
    
    public void setAddress(String address) {
        this.address = (address != null)?address:"";
    }
    
    public void setPhone(String phone) {
        this.phone = (phone != null)?phone:"";
    }
    
    public void setEmail(String email) {
        this.email = (email != null)?email:"";
    }    
}
