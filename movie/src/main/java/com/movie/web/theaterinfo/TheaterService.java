package com.movie.web.theaterinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
	@Autowired
	private TheaterInfoDAO theaterInfoDAO;
}
