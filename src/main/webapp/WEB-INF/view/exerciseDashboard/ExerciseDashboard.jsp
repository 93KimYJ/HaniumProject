<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ExerciseDashboard</title>
<style>
	#mainContainer {
		display: grid;
		grid-template-columns: 200px 1fr;
		grid-template-rows: 200px 50px; 
	}
</style>
</head>
<body>
	<c:import url="/WEB-INF/view/Header.jsp" />
	
	<h1>운동 대쉬보드</h1>
	<div id="mainContainer">
		
		<div class="sidemenu">
			<ul>
				<li><input type="button" onclick="selectType('pushUp')"  value="팔굽혀펴기"/></li>
				<li><input type="button" onclick="selectType('dumbbel')" value="덤벨"/></li>
				<li><input type="button" onclick="selectType('squat')" value="스쿼드"/></li>
			</ul>
		</div>
		
		<div class="contain">
			<h3>운동 기록</h3>
			<div class="exTypeRecode" id="selectExerciseRecode">
			
			</div>
			
			<hr>
			
			<h3>운동 횟수</h3>
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
			
		</div>
	
	</div> <!-- mainContainer -->
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/static/js/ajax.js"></script> <!-- webapp/static 아래에 있음 -->
	<c:import url="/WEB-INF/view/Footer.jsp" />

</body>
</html>