package com.kudori.FileIndexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

        @Autowired
        IndexingEngine ie;
    
	@GetMapping("/")
	public int index() {
                return ie.test();
	}

	@GetMapping("/error")
	public String indexError() {
                return "Error entrypoint, nothing here.";
	}        
        
        
}
        
