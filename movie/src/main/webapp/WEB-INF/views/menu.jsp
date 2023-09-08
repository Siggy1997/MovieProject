<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="./js/jquery-3.7.0.min.js"></script>
<!-- <link rel="icon" href="./img/favicon.ico" type="image/x-icon"> -->
<nav>
	<div class="menu">
		<div class="menu_left">
			<span class="cgv"><a href='./'>CGV</a></span>
		</div>
		<div class="menu_center">
			<ul>
				<li onclick="link('search')"><span class="xi-search"></span></li>
				<li onclick="link('detail')"><span class="list1">영화</span></li>
				<li onclick="link('theater')"><span class="list2">극장</span></li>
				<li onclick="link('reservation')"><span class="list3">예매</span></li>
				<li onclick="link('discount')"><span class="list4">제휴/할인</span></li>
				<li onclick="link('admin')"><span class="list5">관리자</span></li>
			</ul>
		</div>
		<div class="menu_right">
			<ul>
				<li onclick="link('login')"><span class="list6">로그인</span></li>
				<li onclick="link('')"><span class="list6">로그아웃</span></li>
				<li onclick="link('mypage')"><span class="list7">MY CGV</span></li>
			</ul>
		</div>
	</div>
</nav>
<script>
	function link(url) {
		location.href = "./" + url;
	}
</script>