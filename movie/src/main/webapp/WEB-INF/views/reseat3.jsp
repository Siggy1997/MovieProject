<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="./css/reseat3.css">
<head>
<meta charset="UTF-8">

<title>CGV::reseat</title>
<script src="./js/jquery-3.7.0.min.js"></script>



<script type="text/javascript">

	$(function() {
        let countnow;
        let svallist=[];
    	  		
		$(".N").parent().css("background-color", "red")

		$(".up").click(
				function() {
					let nownum = parseInt($(this).siblings(".now").text());
					let pplkind = $(this).parent().siblings(".txt").text(); 
					if (countnow <= 7) {

						if (nownum <= 7) {
							$(this).siblings(".now").text(nownum + 1)
						} else {
							alert("최대 인원")
						}
					} else {
						alert("최대 8명까지 가능합니다");
					}
					countnow = parseInt($(".now1").text())
							+ parseInt($(".now2").text())
							+ parseInt($(".now3").text());
					$(".selectednum").text("선택인원수 : " + countnow);
					if(pplkind=="성인"){


					$("."+pplkind).html(pplkind + " : &nbsp &nbsp &nbsp &nbsp   16,000원 X " + (nownum+1) + " = "+ ((nownum+1)*16000).toLocaleString()+"원");
						
					}else if(pplkind =="청소년"){
						$("."+pplkind).html(pplkind + " : &nbsp &nbsp   13,000원 X " + (nownum+1) + " = "+ ((nownum+1)*13000).toLocaleString()+"원");

					} else if (pplkind =="우대"){
						$("."+pplkind).html(pplkind + " : &nbsp &nbsp &nbsp &nbsp   10,000원 X " + (nownum+1) + " = "+ ((nownum+1)*10000).toLocaleString()+"원");

					}
				});

		$(".down").click(function() {
			let nownum = parseInt($(this).siblings(".now").text());
			let pplkind = $(this).parent().siblings(".txt").text();
			if (nownum >= 1) {
				$(this).siblings(".now").text(nownum - 1)
			} else {
				alert("최소 인원")
			}
			countnow = parseInt($(".now1").text())
			+ parseInt($(".now2").text())
			+ parseInt($(".now3").text());
			$(".selectednum").text("선택인원수 : " + countnow);
			if(pplkind=="성인"){
				$("."+pplkind).html(pplkind + " : &nbsp &nbsp &nbsp &nbsp   16,000원 X " + (nownum-1) + " = "+ ((nownum-1)*16000).toLocaleString()+"원");
					
				}else if(pplkind =="청소년"){
					$("."+pplkind).html(pplkind + " : &nbsp &nbsp   13,000원 X " + (nownum-1) + " = "+ ((nownum-1)*13000).toLocaleString()+"원");

				} else if (pplkind =="우대"){
					$("."+pplkind).html(pplkind + " : &nbsp &nbsp &nbsp &nbsp   10,000원 X " + (nownum-1) + " = "+ ((nownum-1)*10000).toLocaleString()+"원");

				}

		});
		

		

		function seatclick() {
		countnow = parseInt($(".now1").text()) + parseInt($(".now2").text()) + parseInt($(".now3").text());
			  $(".seat").click(function() {
				$(".totalmoney").html(($(".now1").text()*16000 +$(".now2").text()*13000 + $(".now3").text()*10000).toLocaleString() );
			    let sval = $(this).clone().find('input').remove().end().text().trim();
	            let svaltwo;
				
	            if (sval.substr(1) == 1 || sval.substr(1) == 3 || sval.substr(1) == 5 || sval.substr(1) == 7 || sval.substr(1) == 9 || sval.substr(1) == 11 || sval.substr(1) == 13) {
	                svaltwo = sval.substr(0, 1) + (parseInt(sval.substr(1)) + 1);
	            } else {
	                svaltwo = sval.substr(0, 1) + (parseInt(sval.substr(1)) - 1);
	            };



			    $.ajax({
			      url: "./occupied",
			      type: "get",
			      data: {
			        sval: sval, svaltwo:svaltwo, ms_idx:${ms_idx}
			      },
			      dataType: "json",
			      
			      success: function(data) {
			    	 
			    	 
				    
				    
						if (countnow == 1) {
			        	
							if (data.reserved == 1) {
			            	alert("이미 예약되어있는 좌석입니다.");
			          		} else {
					        	  if(!(svallist.includes(sval))){

			          			svallist.push(sval)
			            		$(".seat").addClass("gray");
			           			$(".N").parent().addClass("red");
			           			$("." + sval).addClass("reserved");			            		
			           			countnow= countnow-1;
			           			$(".seat").off("click");
			           			//return false;
					        	
					        	  }else{
									alert("!");
					        		  alert("이미 선택하신 좌석입니다.");
					        	  }
					        	  
			           	      	$(".reserved").click(function() {
				            		$(".seat").removeClass("gray");
				              		$(".reserved").removeClass("reserved");
				              		svallist.length = 0;
									countnow=0;
				             		seatclick();
				            });


			        	}
			        } // countnow = 1
					
			        if (countnow == 2) {
			            if (data.reserved == 1 || data.reservedtwo ==1) {
				            alert("이미 예약되어있는 좌석입니다.");
				          } else {
				        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
				        		  
				        	  svallist.push(sval);
				        	  svallist.push(svaltwo);
				        	  	$(".seat").addClass("gray");
				        	  	$(".N").parent().addClass("red");
				        	  	$("." + sval).addClass("reserved");
				        	  	$("." + svaltwo).addClass("reserved");
				            	$(".seat").off("click");
				            	countnow= countnow-2;
				        	  } else{

				        		  alert("이미 선택하신 좌석입니다.")
				        	  }

				            	$(".reserved").click(function() {
				            		$(".seat").removeClass("gray");
				              		$(".reserved").removeClass("reserved");
				             		svallist = [];
				             		seatclick();
				            });
				            
				           		$("." + svaltwo).click(function() {
				           			$(".seat").removeClass("gray");
				              		$(".reserved").removeClass("reserved");
				              		svallist=[];
				              		seatclick();
				            });
				          }
			        }
			        
			        
			        
			        if (countnow == 3) {
	
							if (data.reserved == 1 || data.reservedtwo ==1) {
						            alert("이미 예약되어있는 좌석입니다.");
							} else {
				        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
								 svallist.push(sval);
					        	  svallist.push(svaltwo);
								$("." + sval).addClass("reserved");
								$("." + svaltwo).addClass("reserved");
				           	 	countnow=countnow-2;
				        	  }else{
				        		  alert("이미 선택하신 좌석입니다.");
				        	  }
							}
			        		
			        };
			        
			        if (countnow == 4) {

			        	if (data.reserved == 1 || data.reservedtwo ==1) {
				            alert("이미 예약되어있는 좌석입니다.");
					} else {
		        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
						 svallist.push(sval);
			        	  svallist.push(svaltwo);
						$("." + sval).addClass("reserved");
						$("." + svaltwo).addClass("reserved");
		           	 	countnow=countnow-2;
		        	  }else{
		        		  alert("이미 선택하신 좌석입니다.")
		        	  }
					}
		        		
		       		}
					
			        if (countnow ==5) {

			        	if (data.reserved == 1 || data.reservedtwo ==1) {
				            alert("이미 예약되어있는 좌석입니다.");
					} else {
		        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
						 svallist.push(sval);
			        	  svallist.push(svaltwo);
						$("." + sval).addClass("reserved");
						$("." + svaltwo).addClass("reserved");
		           	 	countnow=countnow-2;
		        	  }else{
		        		  alert("이미 선택하신 좌석입니다.")
		        	  }
					}
		        		
		       		}
			       
			        if (countnow == 6) {

			        	if (data.reserved == 1 || data.reservedtwo ==1) {
				            alert("이미 예약되어있는 좌석입니다.");
					} else {
		        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
						 svallist.push(sval);
			        	  svallist.push(svaltwo);
						$("." + sval).addClass("reserved");
						$("." + svaltwo).addClass("reserved");
		           	 	countnow=countnow-2;
		        	  }else{
		        		  alert("이미 선택하신 좌석입니다.")
		        	  }
					}
		        		
		       		}
			        if (countnow >= 7) {

			        	if (data.reserved == 1 || data.reservedtwo ==1) {
				            alert("이미 예약되어있는 좌석입니다.");
					} else {
		        	  if(!(svallist.includes(sval)) || !(svallist.includes(svaltwo))){
						 svallist.push(sval);
			        	  svallist.push(svaltwo);
						$("." + sval).addClass("reserved");
						$("." + svaltwo).addClass("reserved");
		           	 	countnow=countnow-2;
		        	  }else{
		        		  alert("이미 선택하신 좌석입니다.")
		        	  }
					}
		        		
		       		}
			        $(".selectedseats").text("선택 좌석 : " +svallist);
			      } // success end
			    }); //ajax end
			  });
			}
		seatclick();
		
		
		$(document).on("click", ".finreservation", function(){
			if(!(svallist == null) && !(svallist == "")){
			let form = $('<form></form>');
			form.attr("action","./reseat3");
			form.attr("method", "post");
			form.append($("<input>",{type:'hidden', name:"list", value:svallist}));
			form.append($("<input>",{type:'hidden', name:"ms_idx", value:${ms_idx}}));
			form.append($("<input>",{type:'hidden', name:"adult", value:$(".now1").text()}));
			form.append($("<input>",{type:'hidden', name:"youth", value:$(".now2").text()}));
			form.append($("<input>",{type:'hidden', name:"special", value:$(".now3").text()}));
			form.appendTo("body");
			form.submit();
			}else{
				alert("좌석을 선택해주세요");
				return false;
			}
		});
		
		
	});
	
	
	
	
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<h1>RESEAT</h1>
	<div class="aseats">
	<div class="countppl-box">
		<div class="countppl">
			<p class="txt">성인</p>
			<div class="count">
				<button type="button" class="down">-</button>
				<button type="button" class="now now1">0</button>
				<button type="button" class="up">+</button>
			</div>
		</div>
		<div class="countppl">
			<p class="txt">청소년</p>
			<div class="count">
				<button type="button" class="down">-</button>
				<button type="button" class="now now2">0</button>
				<button type="button" class="up">+</button>
			</div>
		</div>
		<div class="countppl">
			<p class="txt">우대</p>
			<div class="count">
				<button type="button" class="down">-</button>
				<button type="button" class="now now3">0</button>
				<button type="button" class="up">+</button>
			</div>
		</div>
		
	</div>

	<div class="clear"></div>
	
			<img style="width:500px; " alt="image" src="../img/screen2.png">
			<div class="row">
				<p class="p">A</p> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
			
				<c:forEach begin="0" end="9" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>

			<div class="row">
				<p class="p">B</p> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
				<c:forEach begin="10" end="19" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">C</p> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
				<c:forEach begin="20" end="29" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">D</p>
				<c:forEach begin="30" end="43" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">E</p>
				<c:forEach begin="44" end="57" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">F</p>
				<c:forEach begin="58" end="71" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">G</p>
				<c:forEach begin="72" end="85" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">H</p>
				<c:forEach begin="86" end="99" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">I</p>
				<c:forEach begin="100" end="113" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">J</p>
				<c:forEach begin="114" end="127" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<p class="p">K</p>
				<c:forEach begin="128" end="141" var="i">
					<div class="seat ${slist[i].ss_seat}">${slist[i].ss_seat}
						<input type="hidden" name="${slist[i].ss_seat}"
							class="${slist[i].ss_res}">
					</div>
				</c:forEach>
	
			</div>
			
			
			<div class="clear"></div>

	</div>
	
		<div class="payment">
			<div >
				<img class="gradeimg" alt="" src="${movieschedule.mv_gradeimg }">	
				<div class="mv_title">${movieschedule.mv_name }</div>
				<div class="mv_grade">(${movieschedule.mv_grade })</div>
			</div>
			<hr>
			<div>
			<img class="poster" alt="img" src="${movieschedule.mv_poster }">
				<br>
				<div>${movieschedule.th_city }드림박스</div>
				<div>
    <c:if test="${movieschedule.th_kind eq '4'}">IMAX LASER</c:if>
    <c:if test="${movieschedule.th_kind eq '3'}">일반(3D)</c:if>
    <c:if test="${movieschedule.th_kind eq '2' || movieschedule.th_kind eq '1'}">일반(2D)</c:if>
</div>

				<div>${movieschedule.th_kind }관</div>
				<div>${movieschedule.ms_sdate }</div>
				<br>
				<div>상영시간 : ${movieschedule.ms_stime } ~ ${movieschedule.ms_etime }</div>
				<div>${movieschedule.mv_runtime }분</div>
				<br>
			<hr>
			</div>
			<br>
			<div class="coloroptions">
				<div class="color" style="background-color:  #209BF2;"></div>
				<div class="option">선택</div>
				<div class="color" style="background-color: red;"></div>
				<div class="option">예매완료</div>
				<div class="color" style="background-color: gray;"></div>
				<div class="option">선택불가</div>
			</div>
			<div class="clear"></div>
			<div>
				<div class="selectednum">선택인원수 : 0 </div>
				<div class="성인">성인 : &nbsp &nbsp &nbsp &nbsp   16,000원 X 0 = 0원 </div>
				<div class="청소년">청소년 : &nbsp &nbsp    13,000원 X 0 = 0원 </div>
				<div class="우대">우대 : &nbsp &nbsp &nbsp &nbsp     10,000원 X 0 = 0원</div>
				<div class="selectedseats">선택좌석 :</div>
				<br><br>
				<div class="ttm">총결제액 : </div>
				<div class="totalmoney"></div>
			<div class="clear" style="height:30px; "></div>
				<button class="goback">이전</button>
				<button class="finreservation">결제하기</button>
			</div>
			
		</div>

</body>
</html>