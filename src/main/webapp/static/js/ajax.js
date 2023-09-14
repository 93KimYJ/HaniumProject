/**
 * 
 */


// 단일책임원칙 준수
function appendCountAndTry(dataMap, range) {
	$("#"+range+"Count").append("횟수 : "+ dataMap.get(range+"Count"));
    $("#"+range+"Try").append("세트 : "+ dataMap.get(range+"Try"));
}

function getAverageOrZero(dataMap, range) {
	let averageCount = Math.round(dataMap.get(range+"Count") / dataMap.get(range+"Try"))
    averageCount = isNaN(averageCount) ? 0 : averageCount
    
    return averageCount
}


function selectType(type) {

		$.ajax({
			url: "getExerciseCount",
			type: "GET",
			dataType: "json",
			data: {type : type},
			success: function(data) {

                // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
                $(".searchExData").empty(); // 컴포넌트 초기화
                
                let todayAverage = 0
                const dataMap = new Map(Object.entries(data));
                
                // 일
                appendCountAndTry(dataMap, "today");
                let todayAverageCount = getAverageOrZero(dataMap, "today");
                $("#todayAverageCount").append("세트당 평균 횟수 : "+ todayAverageCount);
                
                // 주
                appendCountAndTry(dataMap, "week");      
                let weekAverageCount = getAverageOrZero(dataMap, "week");
                $("#weekAverageCount").append("세트당 평균 횟수 : "+ weekAverageCount);
                
                // 월
                appendCountAndTry(dataMap, "month");      
                let monthAverageCount = getAverageOrZero(dataMap, "month");
                $("#monthAverageCount").append("세트당 평균 횟수 : "+ monthAverageCount);
				
				// 년
                appendCountAndTry(dataMap, "year");      
                let yearAverageCount = getAverageOrZero(dataMap, "year");
                $("#yearAverageCount").append("세트당 평균 횟수 : "+ yearAverageCount);
				
				// all
                appendCountAndTry(dataMap, "allTime");      
                let allAverageCount = getAverageOrZero(dataMap, "allTime");
                $("#allAverageCount").append("세트당 평균 횟수 : "+ allAverageCount);

                
                // 화면에 표시
				$("#searchExerciseDataDiv").css("display", "block")
            },
            error: function() {
                alert("데이터를 가져오는 데 실패했습니다.");
            }
		});

	}