package com.movie.web.mhome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MhomeController {
	@Autowired
	private MhomeService mhomeService;
		
	@GetMapping(value= {"/", "/index"})
	public String index() {
		
		return "mhome";
	}
	
}
