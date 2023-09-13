<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/static/css/Index.css" />
<script src="https://unpkg.com/embla-carousel/embla-carousel.umd.js"></script>
<script src="https://unpkg.com/embla-carousel-autoplay/embla-carousel-autoplay.umd.js"></script>
<c:import url="/WEB-INF/view/Header.jsp" />
</head>
<body>
	
	<div class="embla">
      <div class="embla__container">
      	<div class="embla__slide" style="background-color: pink">1번 샘플이미지 공간</div>
      	<div class="embla__slide" style="background-color: green">2번 샘플이미지 공간</div>
      	<div class="embla__slide" style="background-color: powderblue">3번 샘플이미지 공간</div>   
      </div>
      <div class="undermenu">
    	하단메뉴가 들어갈 공간
      </div>
    </div>

    <script type="text/javascript">
      //emblaNode 변수에 클래스가 embla인 요소를 찾아 할당
      var emblaNode = document.querySelector(".embla");
      // options 변수에 Embla Carousel의 옵션 객체를 할당하고 loop 옵션을 true로 설정하여 무한루프로 생성
      var options = { loop: true };
      // autoplay에 EmblaCarouselAutoplay 인스턴스를 생성하여 할당 emblaNode와 delay: 5000을 전달 delay는 슬라이드 간 재생 시간을 나타냄
      var autoplay = EmblaCarouselAutoplay(emblaNode, { delay: 5000 });
      // embla 변수에 EmblaCarousel 인스턴스를 생성하여 전달 emblaNode와 option 객체, autoplay배열을 전달
      var embla = EmblaCarousel(emblaNode, options, [autoplay]);
    </script>
	
	<c:import url="/WEB-INF/view/Footer.jsp" />
</body>
</html>