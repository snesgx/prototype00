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
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
    
        private final AtomicBoolean isIndexing = new AtomicBoolean(false);
        
        String hostname = "";
        
        //Initializing variables, such as hostname
        public IndexingEngine(){
            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                Logger.getLogger(IndexingEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public int StartIndexing(String path) {
            
                isIndexing.set(true);
            
                try {
                    
                    System.out.println("Hostname: " + hostname);
                    
                    long startTime = System.currentTimeMillis();                    
                    int test = listAndInsertFilesUsingFilesList(path);  
                    System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " milliseconds");
                    
                    isIndexing.set(false);
                    return test;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                { 
                    isIndexing.set(false);
                    return -1; }
                
        }
    
    
        public int listAndInsertFilesUsingFilesList(String dir) throws IOException {
            
            AtomicInteger itemsCount = new AtomicInteger(0);
            List<FileInfo> result = new ArrayList<>();
            
            Path targetPath = Paths.get(dir);

            FileSystem fs = targetPath.getFileSystem();

            short DeviceID = repository.getDeviceID(hostname, fs.getSeparator());

            //Deleting existing path and children
            try {
                repository.deletePath(DeviceID,GetMD5HashAsBytes(targetPath.toString()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(IndexingEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                Files.find(Paths.get(dir), Integer.MAX_VALUE,
                (filePath, fileAttr) -> {
                    
                   if (!fileAttr.isSymbolicLink()) {

                       try {
                           result.add(new FileInfo( GetMD5HashAsBytes(filePath.toString()),
                                   filePath.getParent() != null ? GetMD5HashAsBytes(filePath.getParent().toString()) : null,
                                   filePath.getFileName() != null ? filePath.getFileName().toString() : "",
                                   fileAttr.size(),
                                   fileAttr.isDirectory(),
                                   fileAttr.lastModifiedTime().toInstant()));

                           itemsCount.incrementAndGet();

                           if (result.size() >= 1000) { //Partial insertion of results, this avoid overusing RAM
                                  repository.saveAll(DeviceID, result);
                                  result.clear();
                                  Logger.getLogger("general").log(Level.ALL, itemsCount.toString());
                           }
                           
                       } catch (NoSuchAlgorithmException ex) {
                           Logger.getLogger(IndexingEngine.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       return true;
                   }
                   else return false;
                }
                ).forEach(file -> {});
                
            if (!result.isEmpty()) { //This is needed because we are using blocks of 1000
                   repository.saveAll(DeviceID, result);
            }            
            
            return itemsCount.get();

    
}
        
        //UUID has the same size as MD5 sum, so we use as an ID, 
        //  it also makes easier to reconstruct parts of the file system tree, because the ID of the parent folder is always the same
        private byte[] GetMD5HashAsBytes(String string) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            return md.digest();
        }        
        
}
