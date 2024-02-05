package com.kudori.FileIndexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

        @Autowired
        IndexingEngine ie;
    
	@GetMapping("/")
	public int StartIndexing(@RequestParam String path) {
                return ie.StartIndexing(path);
	}

	@GetMapping("/error")
	public String indexError() {
                return "Error entrypoint, nothing here.";
	}        
        
        
}
        
