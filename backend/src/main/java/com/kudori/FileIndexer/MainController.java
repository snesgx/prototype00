package com.kudori.FileIndexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MainController {

        @Autowired
        IndexingEngine ie;
    
	@GetMapping("/startindexing")
	public int StartIndexing(@RequestParam String path) {
                return ie.StartIndexing(path);
	}

        @GetMapping("/")
        public RedirectView redirectToHTML() {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/index.html");
            return redirectView;
        }
  
        
}
        
