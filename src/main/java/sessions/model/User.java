/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.model;

/**
 *
 * @author Theriault
 */
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toFileString() {
        return username + "," + password;
    }

    public void loadFromFileString(String str) {
        // TODO: Validation should be done here
        String[] parts = str.split(",");

        username = parts[0];
        password = parts[1];
    }
}
