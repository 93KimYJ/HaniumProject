<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/view/Header.jsp" />
<title>Health Care</title>
<link rel="stylesheet" type="text/css" href="/static/css/SignUp.css" />
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
<form id="signupForm" action="signup.do" method="post" class="signupform" accept-charset="UTF-8">
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
            name="userId"
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
            name="birthDate"
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
            name="phoneNum"
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
            name="email"
            class="emailbox1"
            placeholder="  이메일"
          />
          <a>@</a>
          <input
            type="text"
            name="email"
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
              name="addr"
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
              name="extraAddress"
            />
          </div>
          <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
          <script>
            function sample6_execDaumPostcode() {
              new daum.Postcode({
                oncomplete: function (data) {
                  // 주소 정보를 폼 필드에 설정
                  document.getElementById("sample6_postcode").value = data.zonecode;
                  document.getElementById("sample6_address").value = data.roadAddress;
                  document.getElementById("sample6_detailAddress").value = data.jibunAddress;
                  document.getElementById("sample6_extraAddress").value = data.extraAddress;

                  // 커서를 상세주소 필드로 이동
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
</html>