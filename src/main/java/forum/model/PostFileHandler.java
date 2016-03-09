/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sessions.model.PasswordDataHandler;
import sessions.model.User;

/**
 *
 * @author Theriault
 */
public class PostFileHandler implements PostDataHandler {

     private String fileName;

     String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

     public PostFileHandler(String fileName) { 
          this.fileName = dataDirectory + fileName;
     }

     public String getFileName() {
          return fileName;
     }

     public void setFileName(String fileName) {
          this.fileName = fileName;
     }
 
     public void addPost(Post post) {
          try {
              try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true))) {
                  writer.write(post.toFileString() + "\n");
              } 

          } catch (IOException e) { 
          }
     }

     @Override
     public List<Post> getAllPosts() {
          List<Post> list = new ArrayList<>();

          try {
               BufferedReader reader = new BufferedReader(new FileReader(getFileName()));

               String line;

               while ((line = reader.readLine()) != null) {
                    Post post = new Post(null, null, 0, null, 0, 0, 0, null, null);
                    post.loadFromFileString(line);
                    list.add(post);

               }

          } catch (IOException e) { 
          }

          return list;
     }
}
    

