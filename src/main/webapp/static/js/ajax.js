/**
 * 
 */
 $("#selectButton").click(function() {
		
		var type = $("#exerciseSelect").val();
		
		$.ajax({
			url: "getExerciseCount",
			type: "GET",
			dataType: "json",
			data: {type : type},
			success: function(data) {

                // 서버에서 받은 JSON 데이터를 사용하여 컴포넌트 변경
                $(".searchExData").empty(); // 컴포넌트 초기화

                // JSON 데이터 반복하며 컴포넌트에 추가
                $.each(data, function(key, value) {
                	console.log(value)
                    
                    if (key == "todayCount") {
						$("#todayCount").append("횟수 : "+ value)
					}
                    if (key == "weekCount") {
						$("#weekCount").append("횟수 : "+ value)
					}
                    if (key == "monthCount") {
						$("#monthCount").append("횟수 : "+ value)
					}
                    if (key == "yearCount") {
						$("#yearCount").append("횟수 : "+ value)
					}
                    if (key == "allTimeCount") {
						$("#allTimeCount").append("횟수 : "+ value)
					}
					
					// tryCount
                    if (key == "todayTry") {
						$("#todayTry").append("세트 : "+ value)
					}
                    if (key == "weekTry") {
						$("#weekTry").append("세트 : "+ value)
					}
                    if (key == "monthTry") {
						$("#monthTry").append("세트 : "+ value)
					}
                    if (key == "yearTry") {
						$("#yearTry").append("세트 : "+ value)
					}
                    if (key == "allTimeTry") {
						$("#allTimeTry").append("세트 : "+ value)
					}
					
					
                });
                
                // 화면에 표시
				$("#searchExerciseDataDiv").css("display", "block")
            },
            error: function() {
                alert("데이터를 가져오는 데 실패했습니다.");
            }
		});

	});