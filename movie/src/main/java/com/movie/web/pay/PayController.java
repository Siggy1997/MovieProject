package com.movie.web.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
	@Autowired
	private PayService payService;
	
	@GetMapping("/pay")
	public String pay() {
		
		return "/pay";
	}
	
}
