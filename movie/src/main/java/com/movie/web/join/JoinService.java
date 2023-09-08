package com.movie.web.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
	@Autowired
	private JoinDAO joinDAO;
}
