/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Theriault
 */
public class HardCodedPasswordHandler implements PasswordDataHandler {
    
  @Override
  public List getAllPasswords() {
    List<User> pws = new ArrayList();

    pws.add(new User("david", "password"));
    pws.add(new User("jordan", "password"));
    pws.add(new User("dave", "password"));

    return pws;
  }
}

