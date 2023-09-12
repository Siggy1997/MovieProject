package com.movie.web.pay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	TicketDAO ticketDAO;

	public Map<String, Object> ticketInfo(int msNum) {
		return ticketDAO.ticketInfo(msNum);
	}
	
	
}
