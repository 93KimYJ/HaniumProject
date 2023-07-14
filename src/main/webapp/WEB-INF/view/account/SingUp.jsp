<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/view/Header.jsp" />
<title>Health Care</title>
<link rel="stylesheet" type="text/css" href="css/SignUp.css" />
<script>
      function goBack() {
        window.location.href = "main.do";
      }
      
      function goSignUp(event) {
    	  var inputs = document.getElementsByTagName("input");
    	  var isFormValid = true;
    	  var firstInvalidInput = null;

    	  // Check if all input fields are filled
    	  for (var i = 0; i < inputs.length; i++) {
    	    var input = inputs[i];
    	    if (input.value === "") {
    	      isFormValid = false;
    	      if (!firstInvalidInput) {
    	        firstInvalidInput = input;
    	      }
    	    }
    	  }

    	  if (isFormValid) {
    	    document.getElementById("signupForm").submit();
    	  } else {
    	    alert("모든 항목을 입력해주세요.");
    	    if (firstInvalidInput) {
    	      firstInvalidInput.focus();
    	    }
    	    event.preventDefault(); // 폼 제출 기본 동작 막기
    	  }
    	}
      
    </script>
</head>
<body>
<body>
    <form id="signupForm" action="signup.do" method="post" class="signupform">
      <table>
        <tr>
          <td>
            <hr style="margin-bottom: -1px; margin-left: 1px" />
            <div class="signupmenu">
              <a class="signuplabel">이름</a>
              <input
                type="text"
                name="name"
                id="name"
                class="namebox"
                placeholder="  이름"
              />
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <hr style="margin-bottom: 0px; margin-top: 0px; margin-left: 1px" />
            <div class="signupmenu">
              <a class="signuplabel">아이디</a>
              <input
                type="text"
                id="id"
                name="id"
                class="idbox"
                placeholder="  아이디"
              />
              <input type="button" value="중복확인" onclick="checkDuplicateId()" />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr
              style="margin-bottom: -1.2px; margin-top: 0px; margin-left: 1px"
            />
            <div class="signupmenu">
              <a class="signuplabel">비밀번호</a>
              <input
                type="password"
                name="password"
                class="pwbox"
                placeholder="  비밀번호"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr
              style="margin-bottom: -1.2px; margin-top: 0px; margin-left: 1px"
            />
            <div class="signupmenu">
              <a class="signuplabel">비밀번호 확인</a>
              <input
                type="password"
                name="password2"
                class="pwbox2"
                placeholder="  비밀번호 확인"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr style="margin-bottom: 0px; margin-top: 0px; margin-left: 1px" />
            <div class="signupmenu">
              <a class="signuplabel">생년월일</a>
              <input
                type="date"
                id="birth"
                name="birth"
                class="birth"
                placeholder="  주민번호 앞자리"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr style="margin-bottom: 0px; margin-top: 0px; margin-left: 1px" />
            <div class="signupmenu">
              <a class="signuplabel">휴대폰 번호</a>
              <input
                type="text"
                name="phone"
                class="phonebox"
                placeholder="  휴대폰 번호"
                maxlength="11"
              />
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr
              style="margin-bottom: -1.2px; margin-top: 0px; margin-left: 1px"
            />
            <div class="signupmenu">
              <a class="signuplabel">이메일</a>
              <input
                type="text"
                name="email1"
                class="emailbox1"
                placeholder="  이메일"
              />
              <a>@</a>
              <input
                type="text"
                name="email2"
                class="emailbox2"
                placeholder="  직접 입력"
              />
              
              <select id="email-domain" class="emailbox3" onchange="setEmailOption(this)">
                <option value="">이메일 선택</option>
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="daum.net">daum.net</option>
              </select>
              <script>
				  function setEmailOption(selectElement) {
				    var selectedOption = selectElement.value;
				    var emailBox2 = document.getElementsByClassName('emailbox2')[0];
				    emailBox2.value = selectedOption;
				  }
				</script>
            </div>
          </td>
        </tr>

        <tr>
          <td>
            <hr
              style="margin-bottom: -1.2px; margin-top: 0px; margin-left: 1px"
            />
            <div class="signupmenu">
              <a class="signuplabel" style="height: 140px; margin-top: 90px"
                >주소</a
              >
              <br />
              <input
                type="text"
                id="sample6_postcode"
                placeholder="우편번호"
                name="postcode"
              />
              <input
                type="button"
                onclick="sample6_execDaumPostcode()"
                value="우편번호 찾기"
              />
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="signupmenu">
              <div class="adressbox">
                <input
                  type="text"
                  id="sample6_address"
                  placeholder="주소"
                  name="address1"
                />
                <br />
                <input
                  type="text"
                  id="sample6_detailAddress"
                  placeholder="상세주소"
                  name="address2"
                />
                <input
                  type="text"
                  id="sample6_extraAddress"
                  placeholder="참고항목"
                />
              </div>
              <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
              <script>
                function sample6_execDaumPostcode() {
                  new daum.Postcode({
                    oncomplete: function (data) {
                      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                      var addr = ""; // 주소 변수
                      var extraAddr = ""; // 참고항목 변수

                      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                      if (data.userSelectedType === "R") {
                        // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                      } else {
                        // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                      }

                      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                      if (data.userSelectedType === "R") {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (
                          data.bname !== "" &&
                          /[동|로|가]$/g.test(data.bname)
                        ) {
                          extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (
                          data.buildingName !== "" &&
                          data.apartment === "Y"
                        ) {
                          extraAddr +=
                            extraAddr !== ""
                              ? ", " + data.buildingName
                              : data.buildingName;
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== "") {
                          extraAddr = " (" + extraAddr + ")";
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("sample6_extraAddress").value =
                          extraAddr;
                      } else {
                        document.getElementById("sample6_extraAddress").value =
                          "";
                      }

                      // 우편번호와 주소 정보를 해당 필드에 넣는다.
                      document.getElementById("sample6_postcode").value =
                        data.zonecode;
                      document.getElementById("sample6_address").value = addr;
                      // 커서를 상세주소 필드로 이동한다.
                      document.getElementById("sample6_detailAddress").focus();
                    },
                  }).open();
                }
              </script>
            </div>
            <hr style="margin-top: 40px; margin-left: 1px" />
            <div class="buttoncontainer">
              <input
                type="button"
                value="뒤로가기"
                class="signupbutton1"
                onclick="goBack()"
              />
              <input
                type="submit"
                name="회원가입"
                value="회원가입"
                class="signupbutton2"
                 onclick="goSignUp(event)"
              />
            </div>
          </td>
        </tr>
      </table>
    </form>
  </body>
</body>
</html>