package com.movie.web.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
	@Autowired
	private JoinService joinService;
	
	@GetMapping("/join")
	public String join() {
		
		return "/join";
	}
}
