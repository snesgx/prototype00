/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author migi
 */
@Repository
public class FileIndexerRepositoryImpl implements FileIndexerRepository {
 
    //@Autowired
    //JdbcTemplate jdbcTemplate;
    
    @Override
    public void insertFileList(List<FileInfo> fileList){
            int i = 0;
    };
    
}
