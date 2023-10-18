/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 *
 * @author migi
 */
@Component
public class IndexingEngine {
  
        public String test() {
                
                ObjectMapper objectMapper = new ObjectMapper();
            
                try {
                List<FileInfo> test = listFilesUsingFilesList("/data/ME_Static/MediaME/");  
                return objectMapper.writeValueAsString(test);
                } catch (Exception ex)
                { return ex.getMessage(); }    
                
        }
    
    
        public List<FileInfo> listFilesUsingFilesList(String dir) throws IOException {
            
            List<FileInfo> result = new ArrayList<>();
            
            Files.find(Paths.get(dir), Integer.MAX_VALUE,
           (filePath, fileAttr) -> {
               if (!fileAttr.isSymbolicLink()) {
                   result.add(new FileInfo(filePath.getFileName().toString(), 
                                            fileAttr.size(), 
                                                    fileAttr.isDirectory()));
                   return true;
               }
               else return false;
               }
            ).forEach(file -> {});
            
            return result;

    
}
        
}
