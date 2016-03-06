/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import scriptures.model.Scripture;
import scriptures.model.ScriptureDataHandler;

/**
 *
 * @author Theriault
 */
public class PasswordHandler implements PasswordDataHandler {

     private String fileName;

     String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

     public PasswordHandler(String fileName) { 
          this.fileName = dataDirectory + fileName;
     }

     public String getFileName() {
          return fileName;
     }

     public void setFileName(String fileName) {
          this.fileName = fileName;
     }
 
     public void addUser(User user) {
          try {
              try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true))) {
                  writer.write(user.toFileString() + "\n");
              } 

          } catch (IOException e) { 
          }
     }

     @Override
     public List<User> getAllPasswords() {
          List<User> list = new ArrayList<>();

          try {
               BufferedReader reader = new BufferedReader(new FileReader(getFileName()));

               String line;

               while ((line = reader.readLine()) != null) {
                    User user = new User(null, null);
                    user.loadFromFileString(line);
                    list.add(user);
               }

          } catch (IOException e) { 
          }

          return list;
     }
}
    

