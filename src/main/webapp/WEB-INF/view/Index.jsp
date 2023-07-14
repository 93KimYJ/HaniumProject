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
	<h1>시작페이지</h1>
	
	<form action="${pageContext.request.contextPath}/testApi">
		<input type="submit" value="JSON확인하기">
	</form>
	
	<c:forEach var="v" items="${exList}">
		${v.getCnt() }
	</c:forEach>
	
	
</body>
</html>