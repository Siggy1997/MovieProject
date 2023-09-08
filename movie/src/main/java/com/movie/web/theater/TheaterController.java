package com.movie.web.theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheaterController {
	@Autowired 
	private TheaterService theaterService;
	
	@GetMapping("/theater")
	public String theater() {
		
		return "/theater";
	}
}
