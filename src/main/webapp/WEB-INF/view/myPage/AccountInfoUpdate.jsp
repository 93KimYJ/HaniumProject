<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/view/Header.jsp" />
	<h1>개인정보 수정</h1>
	
	비밀번호 등 수정 구현<br>
	
	${ userInfo }
	<c:import url="/WEB-INF/view/Footer.jsp" />
</body>
</html>