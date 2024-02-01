/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author migi
 */
@Repository
public class FileIndexerRepositoryImpl implements FileIndexerRepository {
 
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    @Transactional
    public void saveAll(List<FileInfo> fileList) {
      for (FileInfo entry : fileList) {
        jdbcTemplate.update("INSERT INTO fileindex (id, parent_id, file_path, file_size, is_directory, creation_date_time) " +
          "VALUES (?, ?, ?, ?, ?, ?)",
          entry.Id(),
          entry.ParentID(),
          entry.filePath(),
          entry.fileSize(),
          entry.isDirectory(),
          entry.creationDateTime()
        );
      }
    }    

    
}
