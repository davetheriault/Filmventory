package scriptures.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileScriptureHandler implements ScriptureDataHandler {

     private String fileName;

     String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

     public FileScriptureHandler(String fileName) { 
          this.fileName = dataDirectory + fileName;
     }

     public String getFileName() {
          return fileName;
     }

     public void setFileName(String fileName) {
          this.fileName = fileName;
     }
 
     public void addScripture(Scripture scripture) {
          try {
              try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true))) {
                  writer.write(scripture.toFileString() + "\n");
              } 

          } catch (IOException e) { 
          }
     }

     @Override
     public List<Scripture> getFavoriteScriptures() {
          List<Scripture> list = new ArrayList<>();

          try {
               BufferedReader reader = new BufferedReader(new FileReader(getFileName()));

               String line;

               while ((line = reader.readLine()) != null) {
                    Scripture scripture = new Scripture(null, 0, 0);
                    scripture.loadFromFileString(line);
                    list.add(scripture);
               }

          } catch (IOException e) { 
          }

          return list;
     }
}