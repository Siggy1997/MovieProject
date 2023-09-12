package com.movie.web.pay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
   @Autowired
   private PayDAO payDAO;

   public List<Map<String, Object>> couponList() {
      return payDAO.couponList();
   }

   public Map<Integer, Object> havePoint() {
      return payDAO.havePoint();
   }

   public void updatePoint(Map<String, Object> map) {
      payDAO.updatePoint(map);
   }

   //public Map<Integer, Object> ticketPrice() {
   //   return payDAO.ticketPrice();
   //}

   public int couponChk(String cCode) {
      return payDAO.couponChk(cCode);
   }

   //public List<String> couDiscount(List<String> selectCoupon) {
//      return payDAO.couDiscount(selectCoupon);
//   }

   public void couponUpdate(Map<String, Object> map) {
      payDAO.couponUpdate(map);
   }


   public Map<String, Object> charge(String cardSelect) {
      return payDAO.charge(cardSelect);
   }

    public int cardChk() {
         return payDAO.cardChk();
      }

      public List<Map<String, Object>> cardList() {
            return payDAO.cardList();
         }

   public void cardUpdate(String cardNum) {
      payDAO.cardUpdate(cardNum);
   }

   public int couDiscount(String selectCoupon) {
      return payDAO.couDiscount(selectCoupon);
   }

   public Map<String, Object> ticketInfo(Map<String, Object> info) {
      return payDAO.ticketInfo(info);
   }

   

}