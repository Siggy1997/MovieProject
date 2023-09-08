package com.movie.web.mselect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MselectController {
	@Autowired
	private MselectService mselectService;

	@GetMapping("mselect")
	public String mselect() {
		
		return "mselect";
	}


}
