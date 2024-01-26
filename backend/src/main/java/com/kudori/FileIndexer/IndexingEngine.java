/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author migi
 */
@Component
public class IndexingEngine {
  
        public List<FileInfo> test() {
            
                try {
                List<FileInfo> test = listFilesUsingFilesList("/data/LO_Static/Music/");  
                return test;
                //return objectMapper.writeValueAsString(test);
                } catch (Exception ex)
                { return null; }    
                
        }
    
    
        public List<FileInfo> listFilesUsingFilesList(String dir) throws IOException {
            
            List<FileInfo> result = new ArrayList<>();
            
            Files.find(Paths.get(dir), Integer.MAX_VALUE,
           (filePath, fileAttr) -> {
               if (!fileAttr.isSymbolicLink()) {
                   result.add(new FileInfo(filePath.getFileName().toString(), 
                                            fileAttr.size(),
                                                    fileAttr.isDirectory(),
                                                fileAttr.creationTime().toInstant()));
                   return true;
               }
               else return false;
               }
            ).forEach(file -> {});
            
            return result;

    
}
        
}
