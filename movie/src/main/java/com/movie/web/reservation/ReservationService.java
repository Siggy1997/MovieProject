package com.movie.web.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	@Autowired
	private ReservationDAO reservationDAO;
}
