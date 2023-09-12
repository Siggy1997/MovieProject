package com.movie.web.pay;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	@GetMapping("/ticket")
	public String ticket() {
		
		return "/ticket";
	}
	
	
	@ResponseBody
	@PostMapping("/ticket")
	public String ticket(@RequestParam ("msNum") int msNum) {
		
		
		Map<String, Object> tcInfo = ticketService.ticketInfo(msNum);
		
		//model.addAttribute("tcInfo", tcInfo);
		
		String mvName = (String) tcInfo.get("mv_name");
		String thCity = (String) tcInfo.get("th_city");
		int thKind = 	(int) tcInfo.get("th_kind");
	
		JSONObject json = new JSONObject();
		json.put("mvName", mvName);
		json.put("thCity", thCity);
		json.put("thKind", thKind);
	
		
		return json.toString();
	}
}
