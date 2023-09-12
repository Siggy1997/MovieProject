package com.movie.web.pay;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketDAO {

	Map<String, Object> ticketInfo(int msNum);

}
