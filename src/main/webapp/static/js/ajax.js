/**
 * 
 */


// ExerciseDashboard의 특정 구조의 화면 요소에 출력하는 함수
function appendCountAndTry(dataMap, range) {
	$("#"+range+"Count").append("횟수 : "+ dataMap.get(range+"Count"));
    $("#"+range+"Try").append("세트 : "+ dataMap.get(range+"Try"));
    $("#"+range+"AverageCount").append("세트당 평균 횟수 : "+ getAverageOrZero(dataMap, range));
}

// 평균값과 NaN을 0으로 바꾸는 함수
function getAverageOrZero(dataMap, range) {
	let averageCount = Math.round(dataMap.get(range+"Count") / dataMap.get(range+"Try"))
    averageCount = isNaN(averageCount) ? 0 : averageCount
    
    return averageCount
}



// 다중 책임... 가져오는것과 화면 출력으로 분리할것
function getExerciseCountWithAjax(type) {
	$.ajax({
		url: "getExerciseCount",
		type: "GET",
		dataType: "json",
		data: {type : type},
		success: function(data) {

            // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
            $(".searchExData").empty(); // 컴포넌트 초기화
           
           	
            const dataMap = new Map(Object.entries(data));
            
            // 일
            appendCountAndTry(dataMap, "today");
            
            // 주
            appendCountAndTry(dataMap, "week");      
            
            // 월
            appendCountAndTry(dataMap, "month");      
			
			// 년
            appendCountAndTry(dataMap, "year");      
			
			// all
            appendCountAndTry(dataMap, "allTime");      
            
            // 화면에 표시
			$("#searchExerciseDataDiv").css("display", "block")
        },
        error: function() {
            alert("데이터를 가져오는 데 실패했습니다.");
        }
	});
}

function getExerciseRecodeWithAjax(type) {
	$.ajax({
		url: "getExerciseRecode",
		type: "GET",
		dataType: "json",
		data: {type : type},
		success: function(data) {

            // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
            $(".exTypeRecode").empty(); // 컴포넌트 초기화
           	
            const dataMap = new Map(Object.entries(data));
            
            let recode = dataMap.get("exerciseRecode");
            for(let i = 0; i < 5; ++i) {	
				let vo = recode[i];
				console.log(vo);
				
				// 임시값임. 화면 스타일에 따라 변경할것
				$("#selectExerciseRecode").append("<p>" + " " 
				 + "운동횟수: " + vo.cnt + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 종료시간: " + vo.endTime + "</p>");

			}

            // 화면에 표시
			$("#searchExerciseDataDiv").css("display", "block")
        },
        error: function() {
            alert("데이터를 가져오는 데 실패했습니다.");
        }
	});
}

function getUsersBestRecodeWithAjax(type) {
	$.ajax({
		url: "getExerciseRecode",
		type: "GET",
		dataType: "json",
		data: {type : type},
		success: function(data) {

            // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
            $(".exTypeRecode").empty(); // 컴포넌트 초기화
           	
            const dataMap = new Map(Object.entries(data));
            
            let recode = dataMap.get("exerciseRecode");
            for(let i = 0; i < 5; ++i) {	
				let vo = recode[i];
				console.log(vo);
				
				// 임시값임. 화면 스타일에 따라 변경할것
				$("#selectExerciseRecode").append("<p>" + " " 
				 + "운동횟수: " + vo.cnt + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 종료시간: " + vo.endTime + "</p>");

			}

            // 화면에 표시
			$("#searchExerciseDataDiv").css("display", "block")
        },
        error: function() {
            alert("데이터를 가져오는 데 실패했습니다.");
        }
	});
}

// 운동 매뉴 선택시 실행되는 함수 1
function selectType(type) {

	getExerciseCountWithAjax(type);
	
	getExerciseRecodeWithAjax(type);
	
	//getUsersBestRecodeWithAjax(type);
	
	$("#statContainer").css("display", "none")
	
}