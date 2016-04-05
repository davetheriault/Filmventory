/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

/**
 *
 * @author Theriault
 */
class User {
    
    String fbId;
    
    String lastName;
    
    String firstName;
    
    String email;

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbid) {
        this.fbId = fbid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
