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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author migi
 */
@Component
public class IndexingEngine {
  
        @Autowired
        FileIndexerRepository repository;
    
        String hostname = "";
        
        public IndexingEngine(){
            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                Logger.getLogger(IndexingEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public List<FileInfo> test() {
            
                try {
                    
                    System.out.println("Hostname: " + hostname);
                    
                    List<FileInfo> test = listFilesUsingFilesList("/data/git_public/prototype00/");  
                    
                    long startTime = System.currentTimeMillis();
                    
                    repository.saveAll(test);
                    
                    System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " milliseconds");
                    
                    return test;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                { return null; }    
                
        }
    
    
        public List<FileInfo> listFilesUsingFilesList(String dir) throws IOException {
            
            List<FileInfo> result = new ArrayList<>();
            
            Files.find(Paths.get(dir), Integer.MAX_VALUE,
           (filePath, fileAttr) -> {
               if (!fileAttr.isSymbolicLink()) {
                   
                   try {
                       result.add(new FileInfo( GetMD5HashAsBytes(filePath.toString()),
                               GetMD5HashAsBytes(filePath.getParent().toString()),
                               filePath.getFileName().toString(),
                               fileAttr.size(),
                               fileAttr.isDirectory(),
                               fileAttr.lastModifiedTime().toInstant()));
                   } catch (NoSuchAlgorithmException ex) {
                       Logger.getLogger(IndexingEngine.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   return true;
               }
               else return false;
               }
            ).forEach(file -> {});
            
            return result;

    
}
        
        //UUID has the same size as MD5 sum, so we use as an ID, 
        //  it also makes easier to reconstruct parts of the file system tree, because the ID of the parent folder is always the same
        private byte[] GetMD5HashAsBytes(String string) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            return md.digest();
        }        
        
}
