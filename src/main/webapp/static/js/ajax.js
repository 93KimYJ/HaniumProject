/**
 * 
 */


//
function appendCountAndTry(dataMap, range) {
	$("#"+range+"Count").append("횟수 : "+ dataMap.get(range+"Count"));
    $("#"+range+"Try").append("세트 : "+ dataMap.get(range+"Try"));
    $("#"+range+"AverageCount").append("세트당 평균 횟수 : "+ getAverageOrZero(dataMap, range));
}

function getAverageOrZero(dataMap, range) {
	let averageCount = Math.round(dataMap.get(range+"Count") / dataMap.get(range+"Try"))
    averageCount = isNaN(averageCount) ? 0 : averageCount
    
    return averageCount
}

// 이거 분리하는게 좋음
function selectType(type) {

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
				$("#selectExerciseRecode").append("<p>" + vo.eNo + " " + vo.userId  + " " + vo.cnt + " " + vo.endTime + "</p>");

			}

            // 화면에 표시
			$("#searchExerciseDataDiv").css("display", "block")
        },
        error: function() {
            alert("데이터를 가져오는 데 실패했습니다.");
        }
	});
	
	// 이런거 각각 함수로 분리
	/*$.ajax({
		url: "getBestExerciseRecode",
		type: "GET",
		dataType: "json",
		data: {type : type},
		success: function(data) {

            // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
            $(".exTypeRecode").empty(); // 컴포넌트 초기화
           	
            const dataMap = new Map(Object.entries(data));
            
            let recode = dataMap.get("exerciseRecode");
            for(let i = 0; i < 5; ++i) {	
				let vo = recode[i]
				$("#selectBestExerciseRecode").append("<p>" + vo.userId + " " + vo.cnt + " " + vo.endTime + "</p>");

			}

            // 화면에 표시
			$("#searchExerciseDataDiv").css("display", "block")
        },
        error: function() {
            alert("데이터를 가져오는 데 실패했습니다.");
        }
	});*/

}