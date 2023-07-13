<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Health Care</title>
    <link rel="stylesheet" type="text/css" href="css/Header.css" />
  </head>
  <body>
    <div class="headerbody">
      <div class="headercontainer">
        <img class="headerlogo" src="img/logo.png" alt="" />
        <a class="headertitle">Health Care System</a>
        <nav class="headerright">
          <% if (session.getAttribute("userId") == null) { %>
          <a class="headersignin">로그인&nbsp;&nbsp;</a>
          <a class="headersignup">회원가입&nbsp;&nbsp;</a>
          <% } else { %>
          <a class="headersignup"><%= session.getAttribute("userId") %>님 사용 중&nbsp;&nbsp;</a>
          <a class="headersignup">로그아웃&nbsp;&nbsp;</a>
          <% } %>
          <input type="checkbox" id="headermenuicon" />
          <label for="headermenuicon">
            <span></span>
            <span></span>
            <span></span>
          </label>
          <div class="headermenu" id="menu">
            <ul>
              <li><a>아령 운동</a></li>
              <li><a>스쿼트</a></li>
              <li><a>랭킹?</a></li>
              <li><a>또 뭐 넣지</a></li>
              <li><a>마이페이지</a></li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
    <c:forEach var="v" items="${exList}">
		${v.getCnt() }
	</c:forEach>
  </body>
</html>