package com.movie.web.reseat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseatService {
	@Autowired
	private ReseatDAO reseatDAO;

	public List<Map<String, Object>> seatnum() {
		return reseatDAO.seatnum();
	}

	public int reserve(String sval) {
		return reseatDAO.reserve(sval);
	}

	public int reservetwo(String svaltwo) {
		return reseatDAO.reservetwo(svaltwo);
	}

	public void finreservation(List<String> list) {
		reseatDAO.finreservation(list);
	}

	public Map<String, Object> movieschedule() {
		return reseatDAO.movieschedule();
	}




	

 
}
