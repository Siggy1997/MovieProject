<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV::ticket</title>
<script src="./js/jquery-3.7.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="./bootstrapt/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/ticket.css" />
</head>
<body>
	<%@ include file="menu.jsp"%>
	<h1>TICKET</h1>
	
	모바일 티켓
	<br><br>
	예매번호
	[랜덤?]
	<br><br>
	영화제목
	<br>
<span id="movieName">영화제목</span>
	<br><br>
	포맷
	[url]
	<br>
	관람등급
	<br>
	상영관
	<br>
	<span id="theaterName">상영관</span><span id="theaterNum"></span>관
	<br>
	상영일
	<br>
	상영시간
	<br>
	좌석
	[url]
	<br>
	일반, 청소년, 우대
	[url]
	
	
	
	<script>
	
	
	$(document).ready(function() {
	  
		let msNum = 10;
		
        $.ajax({
            url: "/ticket", 
            type: "POST",
            data: {"msNum": msNum},
            dataType:"json",
            success: function(result) {
            
            	$("#movieName").text(result.mvName);
            	$("#theaterName").text(result.thCity);
           	$("#theaterNum").text(result.thKind);
             
            },
            error: function() {
                alert("에러. 다시 시도하세요");
            }
        });	        
    });
	
	
	</script>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>