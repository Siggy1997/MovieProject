package com.movie.web.reseat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReseatDAO {

	List<Map<String, Object>> seatnum();

	int reserve(String sval);

	int reservetwo(String svaltwo);

	void finreservation(List<String> list);

	Map<String, Object> movieschedule();







}
