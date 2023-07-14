<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/view/Header.jsp" %>
<title>Health Care</title>
<link rel="stylesheet" type="text/css" href="css/SignIn.css" />
</head>
<body>
	<div class="signinbody">
		<div class="signincontainer">
		<h1>User Login</h1>
			<form action="" method="post">
	            <div class="signinbox">
	        	    <input type="text" placeholder="  아이디" name="id" class="idbox">
	        	    <br>
	        	    <input type="password" placeholder="  비밀번호" name="password" class="passwordbox">
	            </div>
	        	<br>
	            <div class="signinbuttonbox">
	        	    <input type="submit" value="뒤로가기" class="signinbutton">
	        	    <input type="button" value="로그인" class="backbutton">
	            </div>
	            <hr>
	            <div class="othersigninbuttonbox">
	            	<input type="button" value="카카오 로그인" class="kakaobutton">
	            	<input type="button" value="회원가입" class="googlebutton">
	            </div>
	        </form>
	    </div>
	</div>	    
</body>
</html>