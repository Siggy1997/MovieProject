package com.movie.web.reseat;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import netscape.javascript.JSObject;

@Controller
public class ReseatController {
	@Autowired
	private ReseatService reseatService;
	
	
	
	@GetMapping("/reseat")
	public String reseat(Model model) {
		List<Map<String, Object>> slist = reseatService.seatnum();
		Map<String, Object> movieschedule = reseatService.movieschedule();
		
		System.out.println(movieschedule);
		model.addAttribute("slist", slist);
		model.addAttribute("movieschedule", movieschedule);
		return "/reseat";
		
	}
	@PostMapping("/reseat")
	public String reseat(@RequestParam("svallist") List<String> list) {
		reseatService.finreservation(list);
		
		return "redirect:/reseat";
	}
	
	@GetMapping("/reseat2")
	public String reseat2(Model model) {
		List<Map<String, Object>> slist = reseatService.seatnum();
		Map<String, Object> movieschedule = reseatService.movieschedule();
		
		System.out.println(movieschedule);
		model.addAttribute("slist", slist);
		model.addAttribute("movieschedule", movieschedule);
		return "/reseat2";
		
	}
	@PostMapping("/reseat2")
	public String reseat2(@RequestParam("svallist") List<String> list) {
		reseatService.finreservation(list);
		
		return "redirect:/reseat2";
	}
	
	@ResponseBody
	@GetMapping("/occupied")
	public String seatreservation(@RequestParam("sval") String sval, @RequestParam("svaltwo") String svaltwo, Model model) {
		int reserved = reseatService.reserve(sval);
		int reservedtwo = reseatService.reservetwo(svaltwo);
		model.addAttribute("reserved", reserved);
		
		
		JSONObject json = new JSONObject();
		json.put("reserved", reserved);
		json.put("reservedtwo", reservedtwo);
		
		return json.toString();
	}
	
}
