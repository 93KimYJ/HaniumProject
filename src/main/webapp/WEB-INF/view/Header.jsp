<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Health Care</title>
    <link rel="stylesheet" type="text/css" href="/static/css/Header.css" />
  </head>
  <body>
    <div class="headerbody">
      <div class="headercontainer">
        <img class="headerlogo" src="/static/img/logo.png" alt="" />
        <a class="headertitle" href="${pageContext.request.contextPath}/index">Health Care System</a>
        <nav class="headerright">
          <% if (session.getAttribute("userId") == null) { %>
          <a class="headersignin" href="${pageContext.request.contextPath}/toLogin">로그인&nbsp;&nbsp;</a>
          <a class="headersignup" href="${pageContext.request.contextPath}/toSingUp">회원가입&nbsp;&nbsp;</a>
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
              <li><a href="${pageContext.request.contextPath}/toMyPage">마이페이지</a></li>
              <hr>
              <li><a>운동 순위</a></li>
              <hr>
              <li><a>덤벨 운동정보</a></li>
              <hr>
              <li><a>팔굽혀펴기 운동정보</a></li>
              <hr>
              <li><a>스쿼트 운동정보</a></li>
              <hr>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </body>
</html>