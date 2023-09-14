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
	만들것들<br>
	세트 당 평균 횟수, 평균 횟수 유저 전체평균과 비교, 이전 기록과 비교, 최고 기록<br>
	(사이드 메뉴는 기능 구현을 위해 임시로 제작한거라 기능구현 이후 운동 기록과 함께 css 재작업 예정)
	<h1>마이페이지</h1>
	모든 운동 세트 총 합 : ${totalTry}<br>
	
	<div class="sidemenu">
		<ul>

			<hr>
			<li><input type="button" onclick="selectType('pushUp')"  value="팔굽혀펴기"/></li>
			<hr>
			<li><input type="button" onclick="selectType('dumbbel')" value="덤벨"/></li>
			<hr>
			<li><input type="button" onclick="selectType('squat')" value="스쿼드"/></li>


			<hr>
			<li>개인정보 조회</li>
		</ul>
	</div>
	
	<hr>
	<h3>운동기록 (조금 손보면 나타내는 갯수 선택 가능)</h3>
	<c:forEach var="vo" items="${ exRecodeList }">
		<%-- ${ vo.toString() } --%> <br>
		
		식별번호: ${ vo.eNo }
		
		uid: ${ vo.userId }
		
		운동종류: 
		<c:if test="${ vo.type eq 'dumbbel' }">
			덤벨
		</c:if>
		<c:if test="${ vo.type eq 'pushUp' }">
			팔굽혀펴기
		</c:if>
		<c:if test="${ vo.type eq 'squat' }">
			스쿼트
		</c:if>
		
		횟수: ${ vo.cnt }
		
		종료시간: ${ vo.endTime }
		
		
		
		
	</c:forEach>
	<hr>
	
	<div id="searchExerciseDataDiv" style="display:none">
	
		<div id="toDayInfo">
			<h4>오늘</h4>
			<p class="searchExData" id="todayTry"></p>
			<p class="searchExData" id="todayCount"></p>
			<p class="searchExData" id="todayAverageCount"></p>
		</div>
		<div id="weekInfo">
			<h4>이번 주</h4>
			<p class="searchExData" id="weekTry"></p>
			<p class="searchExData" id="weekCount"></p>
			<p class="searchExData" id="weekAverageCount"></p>
		</div>
		<div id="monthInfo">
			<h4>이번 달</h4>
			<p class="searchExData" id="monthTry"></p>
			<p class="searchExData" id="monthCount"></p>
			<p class="searchExData" id="monthAverageCount"></p>
		</div>
		<div id="yearInfo">
			<h4>올 해</h4>
			<p class="searchExData" id="yearTry"></p>
			<p class="searchExData" id="yearCount"></p>
			<p class="searchExData" id="yearAverageCount"></p>
		</div>
		<div id="allTimeInfo">
			<h4>총 합</h4>
			<p class="searchExData" id="allTimeTry"></p>
			<p class="searchExData" id="allTimeCount"></p>
			<p class="searchExData" id="allTimeAverageCount"></p>
		</div>
	
	</div>
	
	<script src="/static/js/ajax.js"></script> <!-- webapp/static 아래에 있음 -->
	<script type="text/javascript">

</script>
<c:import url="/WEB-INF/view/Footer.jsp" />
</body>
</html>