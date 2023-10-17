/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                try {
                Set<String> test = listFilesUsingFilesList("/");  
                return test.toString();
                } catch (Exception ex)
                {}    
                return "I have nothing for you";
        }
    
    
        public Set<String> listFilesUsingFilesList(String dir) throws IOException {
            try (Stream<Path> stream = Files.list(Paths.get(dir))) {
                return stream
                  .filter(file -> !Files.isDirectory(file))
                  .map(Path::getFileName)
                  .map(Path::toString)
                  .collect(Collectors.toSet());
        }    
    
}
        
}
