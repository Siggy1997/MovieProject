package com.movie.web.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	@Autowired 
	private ReservationService reservationService;
	
	@GetMapping("/reservation")
	public String reservation() {
		
		return "/reservation";
	}
}
