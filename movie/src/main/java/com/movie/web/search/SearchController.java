package com.movie.web.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search")
	public String search() {
		
		
		return "/search";
	}
	
}
