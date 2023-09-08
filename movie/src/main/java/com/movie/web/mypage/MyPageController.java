package com.movie.web.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
	@Autowired
	private MyPageService mypageService;
	
	@GetMapping("/mypage")
	public String mypage() {
		
		return "/mypage";
	}
}
