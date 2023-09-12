package com.movie.web.pay;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {
   @Autowired
   private PayService payService;

//   
//   @ResponseBody
//   @PostMapping("/ticketInfo")
//   public String ticketInfo(@RequestParam ("msNum") int msNum, Model model) {
//      
//      
//      Map<String, Object> tcInfo = payService.ticketInfo(msNum);
//      
//      
//      String mvName = (String) tcInfo.get("mv_name");
//      String thCity = (String) tcInfo.get("th_city");
//      int thKind =    (int) tcInfo.get("th_kind");
//   
//      model.addAttribute("thKind", thKind);
//
//      
//      // ms_sdate(Date)를 문자열로 변환
//      Date sDate = (Date) tcInfo.get("ms_sdate");
//      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//      String msSdate = dateFormat.format(sDate);
//
//      // ms_stime(Time)를 문자열로 변환
//      Time sTime = (Time) tcInfo.get("ms_stime");
//      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//      String msStime = timeFormat.format(sTime);
//
//      // ms_etime(Time)를 문자열로 변환
//      Time eTime = (Time) tcInfo.get("ms_etime");
//      String msEtime = timeFormat.format(eTime);
//   
//      
//      JSONObject json = new JSONObject();
//      json.put("mvName", mvName);
//      json.put("msSdate", msSdate);
//      json.put("msStime", msStime);
//      json.put("msEtime", msEtime);
//      json.put("thCity", thCity);
//      json.put("thKind", thKind);
//   
//      
//      return json.toString();
//   }
//   
   
   @GetMapping("/pay")
   public String pay(Model model, @RequestParam Map<String, Object> info) {
	   
	  //영화 정보 가져오기
	   Map<String, Object> tcInfo = payService.ticketInfo(info);
	   System.out.println(tcInfo);
	   
      List<Map<String, Object>> couponList = payService.couponList();
      model.addAttribute("couponList", couponList);

      Map<Integer, Object> havePoint = payService.havePoint();
      model.addAttribute("havePoint", havePoint);

      //Map<Integer, Object> ticketPrice = payService.ticketPrice();
      //model.addAttribute("ticketPrice", ticketPrice);

      int cardChk = payService.cardChk();
      model.addAttribute("cardChk", cardChk);

      List<Map<String, Object>> cardList = payService.cardList();
      model.addAttribute("cardList", cardList);
      
      model.addAttribute("adult", info.get("adult"));
      model.addAttribute("youth", info.get("youth"));
      model.addAttribute("special", info.get("special"));
      model.addAttribute("list", info.get("list"));
      model.addAttribute("tcInfo", tcInfo);

      return "/pay";

   }

   
   @ResponseBody
   @PostMapping("/couponChk")
   public String couponChk(@RequestParam("cCode") String cCode, HttpSession session,
         @RequestParam Map<String, Object> map) {
      int result = payService.couponChk(cCode);
      JSONObject json = new JSONObject();
      json.put("result", result);

      if (result == 1) {
         String mno = (String) session.getAttribute("mno");
         map.put("mno", mno);
         map.put("cCode", cCode);
         payService.couponUpdate(map);
      }

      return json.toString();

   }

   @ResponseBody
   @PostMapping("/couDiscount")
   public int couDiscount(@RequestBody List<String> selectCouponList) {
       int totalDiscount = 0;

       
      
       for (String selectCoupon : selectCouponList) {

          
           int discount = payService.couDiscount(selectCoupon);

           
           totalDiscount += discount;
       }

       return totalDiscount;
   }

   /*
    * @GetMapping("/getCoupons") public SList<Map<String, Object>>() {
    * 
    * // 쿠폰 목록을 서비스 레이어에서 가져옵니다. List<Map<String, Object>> couponList =
    * PayService.getCoupons();
    * 
    * // 가져온 쿠폰 목록을 클라이언트에 반환합니다.
    * 
    * 
    * return "/couponList";
    * 
    * }
    */

   @ResponseBody
   @PostMapping("updatePoint")
   public void updatePoint(@RequestParam Map<String, Object> map) {
      payService.updatePoint(map);

   }

   @ResponseBody
   @PostMapping("/charge")
   public String charge(@RequestParam Map<String, Object> map) {

      // 입력한 카드 정보
      String cardSelect = (String) map.get("cardSelect");
      String cardNum = (String) map.get("cardNum");
      String cardPw = (String) map.get("cardPw");
      String cardExp = (String) map.get("cardExp");
      String cardBir = (String) map.get("cardBir");

      // db 카드정보 가져오기
      Map<String, Object> cardInfo = payService.charge(cardSelect);

      // 카드 정보 비교
      String dbCardNum = (String) cardInfo.get("rs_cnum");
      String dbCardPw = (String) cardInfo.get("rs_cpw");
      String dbCardExp = (String) cardInfo.get("rs_cdate");
      String dbCardBir = (String) cardInfo.get("rs_cbirth");

      JSONObject json2 = new JSONObject();

      if (cardNum.equals(dbCardNum) && cardPw.equals(dbCardPw) && cardExp.equals(dbCardExp)
            && cardBir.equals(dbCardBir)) {

         json2.put("result", "success");

      } else {
         if (!cardNum.equals(dbCardNum)) {
            json2.put("result", "카드 번호가 잘못되었습니다");

         } else {
            if (!cardExp.equals(dbCardExp)) {
               json2.put("result", "카드 유효기간이 잘못되었습니다");

            } else {
               if (!cardPw.equals(dbCardPw)) {
                  json2.put("result", "카드 비밀번호가 잘못되었습니다");

               } else {
                  if (!cardBir.equals(dbCardBir)) {
                     json2.put("result", "생년월일이 일치하지 않습니다");
                  } else {
                     json2.put("result", "카드사를 다시 확인해주세요");
                  }
               }
            }
         }

      }
      return json2.toString();
   }

   @ResponseBody
   @PostMapping("/cardAdd")
   public String cardAdd(@RequestParam Map<String, Object> map, @RequestParam Boolean agree) {

      // 입력한 카드 정보
      String cardSelect = (String) map.get("cardSelect");
      String cardNum = (String) map.get("cardNum");
      String cardPw = (String) map.get("cardPw");
      String cardExp = (String) map.get("cardExp");
      String cardBir = (String) map.get("cardBir");

      // db 카드정보 가져오기
      Map<String, Object> cardInfo = payService.charge(cardSelect);

      // 카드 정보 비교
      String dbCardNum = (String) cardInfo.get("rs_cnum");
      String dbCardPw = (String) cardInfo.get("rs_cpw");
      String dbCardExp = (String) cardInfo.get("rs_cdate");
      String dbCardBir = (String) cardInfo.get("rs_cbirth");
      Integer dbCardMno = (Integer) cardInfo.get("m_no");



      JSONObject json3 = new JSONObject();

      if (cardNum.equals(dbCardNum) && cardPw.equals(dbCardPw) && cardExp.equals(dbCardExp)
            && cardBir.equals(dbCardBir) && dbCardMno == null) {

         if (agree == false) {
            json3.put("result", "약관에 동의해주세요");
         } else {
            json3.put("result", "success");
            payService.cardUpdate(cardNum);

         }

      } else {
         if (!cardNum.equals(dbCardNum)) {
            json3.put("result", "카드 번호가 잘못되었습니다");

         } else {
            if (!cardExp.equals(dbCardExp)) {
               json3.put("result", "카드 유효기간이 잘못되었습니다");

            } else {
               if (!cardPw.equals(dbCardPw)) {
                  json3.put("result", "카드 비밀번호가 잘못되었습니다");

               } else {
                  if (!cardBir.equals(dbCardBir)) {
                     json3.put("result", "생년월일이 일치하지 않습니다");
                  } else {
                     if (dbCardMno == 1) {
                        json3.put("result", "이미 등록된 카드입니다");
                     } else {
                        if (dbCardMno != null) {
                           json3.put("result", "카드 명의자가 일치하지 않습니다");
                        } else {
                           json3.put("result", "카드사를 다시 확인해주세요");
                        }
                     }
                  }
               }
            }
         }
      }
      return json3.toString();
   }

   /*
    * 
    * 
    * @PostMapping("/pay")
   public String pay() {

      return "redirect:/pay";
   }

    * 
    * // 컨트롤러에서 cardUpdate 메소드 수정
    * 
    * @ResponseBody
    * 
    * @GetMapping("/cardUpdateList") public String cardUpdateList(@RequestParam
    * String cardNum, Model model) {
    * 
    * List<Map<String, Object>> cardUpdateList = payService.cardList();
    * model.addAttribute("cardList", cardUpdateList); return "cardList";
    * 
    * }
    * 
    * 
    * 
    * @ResponseBody
    * 
    * @GetMapping("/cardChk") public String cardChk() {
    * 
    * List<Map<String, Object>> cardList = payService.cardList();
    * 
    * JSONObject json3 = new JSONObject(); json3.put("cardList", cardList);
    * 
    * 
    * 
    * return json3.toString();
    * 
    * }
    * 
    * 
    * 
    * @ResponseBody
    * 
    * @PostMapping("/cardUpdate") public void cardUpdate(@RequestParam("cardNum")
    * String cardNum) {
    * 
    * payService.cardUpdate(cardNum); System.out.println(cardNum);
    * 
    * }
    */

}