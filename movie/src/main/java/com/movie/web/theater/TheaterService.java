package com.movie.web.theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
	@Autowired
	private TheaterDAO theaterDAO;
}
