<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/data/location/style.css">

<link
   href='//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'
   rel='stylesheet' type='text/css'>
<style type="text/css">
ul.paging_button {
   text-align:center;
   overflow: hidden;
   list-style: none;
}

ul li.paging_btn {
   display:inline-block;
   cursor: pointer;
}
</style>
<script
   src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
   src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
var contextPath = "${pageContext.request.contextPath}";
</script>
<script
   src="${pageContext.request.contextPath}/resources/js/data/location/searchform.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>같이떠나요!</title>
</head>
<body>
   <div id="contents">
      <div id="search_form">
         <h1 class="hn">관광지 정보</h1>
         <br>
         <div class="sel_box">
            <label for="areacode">지역</label> <select name="areacode"
               id="areacode" onchange="doChange(this)">
               <option value="0">지역선택</option>
               <option value="1">서울</option>
               <option value="2">인천</option>
               <option value="3">대전</option>
               <option value="4">대구</option>
               <option value="5">광주</option>
               <option value="6">부산</option>
               <option value="7">울산</option>
               <option value="8">세종특별자치시</option>
               <option value="9">경기도</option>
               <option value="10">강원도</option>
               <option value="11">충청북도</option>
               <option value="12">충청남도</option>
               <option value="13">경상북도</option>
               <option value="14">경상남도</option>
               <option value="15">전라북도</option>
               <option value="16">전라남도</option>
               <option value="17">제주도</option>
               <option value="17">인천</option>
            </select>
         </div>
         <div class="sel_box">
            <label for="people">시군구</label> <select name="sigungucode"
               id="sigungucode">
               <option value="0">시군구선택</option>
            </select>
         </div>
         <div class="search_js">
            <p>
               <label for="keyword">검색어</label>
            </p>
            <div class="search-box-container js_center">
               <button class="submit">
                  <i class="fa fa-search"></i>
               </button>
               <input class="search-box">
            </div>
            <h3 class="response hn"></h3>
         </div>
      </div>
      <div id="output"></div>
      <div class="align-center">
         <ul class="paging_button"></ul>
      </div>
      <span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
   </div>
</body>
</html>