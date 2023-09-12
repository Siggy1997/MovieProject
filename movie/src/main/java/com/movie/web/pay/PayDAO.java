package com.movie.web.pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDAO {


   List<Map<String, Object>> couponList();

   Map<Integer, Object> havePoint();

   void updatePoint(Map<String, Object> map);

   //Map<Integer, Object> ticketPrice();

   int couponChk(String cCode);

   // int couDiscount(List<String> selectCoupon);

   void couponUpdate(Map<String, Object> map);

   Map<String, Object> charge(String cardSelect);

     int cardChk();

     List<Map<String, Object>> cardList();

   void cardUpdate(String cardNum);

   int couDiscount(String selectCoupon);

   Map<String, Object> ticketInfo(Map<String, Object> info);
   


     
}