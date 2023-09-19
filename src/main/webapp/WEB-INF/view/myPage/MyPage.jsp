<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/static/css/MyPage.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<c:import url="/WEB-INF/view/Header.jsp" />
	<h1> 마이페이지 </h1>
	
	<form method="get" action="${pageContext.request.contextPath}/toAccountInfoUpdate">
		<input type="submit" value="개인 정보 수정">
	</form>

	<c:import url="/WEB-INF/view/Footer.jsp" />
</body>
</html>