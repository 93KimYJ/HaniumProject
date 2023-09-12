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
	
	<h3>로그인된 아이디 : ${sessionScope.uid}</h3>
	
	<c:forEach var="v" items="${exList}">
		${v.getCnt() }
	</c:forEach>
	
	<h3>dumbbel(변경가능) top 5</h3>
	<c:forEach var="v" items="${topFiveList }">
		${v.userId } : ${v.cnt } <br>
	</c:forEach>
	
	<form action="${pageContext.request.contextPath}/toMyPage" method="get">
		<input type="submit" value="마이페이지">
	</form>
	
	<form action="${pageContext.request.contextPath}/exequteLogout" method="post">
		<input type="submit" value="로그아웃">
	</form>
	
	
</body>
</html>