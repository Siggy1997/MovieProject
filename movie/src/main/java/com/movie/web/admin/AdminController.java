package com.movie.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@Autowired 
	private AdminService adminService;
	
	@GetMapping("/admin")
	public String admin() {
		
		return "/admin";
	}
}
