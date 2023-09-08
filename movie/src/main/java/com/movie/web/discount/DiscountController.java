package com.movie.web.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscountController {
	@Autowired 
	private DiscountService discountService;
	
	@GetMapping("/discount")
	public String discount() {
		
		return "/discount";
	}
}
