/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kudori.FileIndexer;

import java.util.List;

/**
 *
 * @author migi
 */
interface FileIndexerRepository {

    /*
    This will help to insert the list of files in the DB
     */
    void insertFileList(List<FileInfo> fileList);
    
}
