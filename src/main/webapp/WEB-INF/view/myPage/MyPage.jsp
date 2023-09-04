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
	<h1>마이페이지</h1>
	총 운동 횟수 (덤벨) : ${totalCount}<br>
	이번달 총합 (덤벨) : ${monthCount}<br>
	모든 운동 세트 총 합 : ${totalTry}
	
<%-- 	<form method="get" action="${pageContext.request.contextPath}/getExerciseCount"></form> --%>
	<button id="selectButton">선택</button>
	<select id="exerciseSelect">
		<option value="dumbbel">덤벨</option>
		<option value="pushUp">팔굽혀펴기</option>
	</select>
	
	<div id="exerciseTryData"></div>
	
	<script type="text/javascript">
	$("#selectButton").click(function() {
		
		var type = $("#exerciseSelect").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/getExerciseCount",
			type: "GET",
			dataType: "json",
			data: {type : type},
			success: function(data) {
                // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
                var appendDiv = $("#exerciseTryData");
                appendDiv.empty(); // 컴포넌트 초기화

                // JSON 데이터 반복하며 컴포넌트에 추가
                $.each(data, function(key, value) {
                	console.log(value)
                    appendDiv.append("<p>" + key + " : " + value + "</p>");
                });
            },
            error: function() {
                alert("데이터를 가져오는 데 실패했습니다.");
            }
		});
	});
</script>
</body>
</html>