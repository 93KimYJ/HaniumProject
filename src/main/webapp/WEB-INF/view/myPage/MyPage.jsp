<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<c:import url="/WEB-INF/view/Header.jsp" />
	만들것들<br>
	세트 당 평균 횟수, 평균 횟수 유저 전체평균과 비교, 이전 기록과 비교, 최고 기록
	<h1>마이페이지</h1>
	모든 운동 세트 총 합 : ${totalTry}<br>
	
<%-- 	<form method="get" action="${pageContext.request.contextPath}/getExerciseCount"></form> --%>
	<button id="selectButton">선택</button>
	<select id="exerciseSelect">
		<option value="dumbbel">덤벨</option>
		<option value="pushUp">팔굽혀펴기</option>
	</select>
	
	<div id="searchExerciseDataDiv" style="display:none">
	
		<div id="toDayInfo">
			<h4>오늘</h4>
			<p class="searchExData" id="todayTry"></p>
			<p class="searchExData" id="todayCount"></p>
		</div>
		<div id="weekInfo">
			<h4>이번 주</h4>
			<p class="searchExData" id="weekTry"></p>
			<p class="searchExData" id="weekCount"></p>
		</div>
		<div id="monthInfo">
			<h4>이번 달</h4>
			<p class="searchExData" id="monthTry"></p>
			<p class="searchExData" id="monthCount"></p>
		</div>
		<div id="yearInfo">
			<h4>올 해</h4>
			<p class="searchExData" id="yearTry"></p>
			<p class="searchExData" id="yearCount"></p>
		</div>
		<div id="allTimeInfo">
			<h4>총 합</h4>
			<p class="searchExData" id="allTimeTry"></p>
			<p class="searchExData" id="allTimeCount"></p>
		</div>
	
	</div>
	
	<script src="js/ajax.js"></script> <!-- webapp/static 아래에 있음 -->
	<script type="text/javascript">

</script>
</body>
</html>