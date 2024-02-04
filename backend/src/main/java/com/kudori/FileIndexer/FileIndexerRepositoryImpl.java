/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
    public void saveAll(int DeviceID, List<FileInfo> fileList) { //BATCH
        jdbcTemplate.batchUpdate("INSERT INTO fileindex (id, parent_id, device_id, file_name, file_size, is_directory, modification_date_time) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?)",
                fileList,
                1000,
            (PreparedStatement ps, FileInfo file) -> {
                        ps.setBytes(1, file.Id());
                        ps.setBytes(2, file.ParentID());
                        ps.setInt(3, DeviceID);
                        ps.setString(4, file.filePath());
                        ps.setLong(5,file.fileSize());
                        ps.setBoolean(6,file.isDirectory());
                        ps.setTimestamp(7,Timestamp.from(file.modificationDateTime()));
                    });
    }
    
    @Override
    public int getDeviceID(String hostname, String separator){
        return jdbcTemplate.queryForObject("select get_deviceid(?, ?)", int.class, hostname, separator);
    }
    /*
    @Override
    @Transactional
       public void saveAll(List<FileInfo> fileList) { //INDIVIDUAL
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
    */
    
}
