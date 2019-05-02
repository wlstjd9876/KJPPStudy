<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link rel="stylesheet"  href="${pageContext.request.contextPath}/resources/css/w3.css" type="text/css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" type="text/css"> 
  
<!-- 애가 메뉴를 못뜨게 막아줌 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/comfav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/placeModal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scheduleModal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberModal.js"></script>
<script type="text/javascript" >var contextPath = "${pageContext.request.contextPath}"</script>
	
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn {
   font-family: 'Hanna', sans-serif;
}
</style>

<div class="container">
<div class="hn" style="padding: 60">

   <!-- 즐겨찾기한 장소 시작 -->
   <div class="col-lg-4 row align-center" align="center" style="padding-right: 50; padding-top: 50px;">
      <img align="middle"
         src="${pageContext.request.contextPath}/resources/img/placeholder.png">
      <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 장소</h3>
      <br>
      <c:forEach var="fav" items="${list1}" begin="0" end="3" varStatus="status">
         <div class="w3-card-4 w3-margin" align="center" style="width: 90%">
            <div class="w3-container">
               <div style="width: 25%; height: 12%; float: left;">
          	   <img style="width: 100%; height: 100%"  id="juso${status.count}">
          	   
               </div>
               <div style="width: 75%; height: 12%; float: right;">
               <script type="text/javascript">var a = ${fav.f_code1}</script>
               <script type="text/javascript">detail1(a,${status.count});</script>
           
                  <div align="left">
               <span>주소 : </span><span id="nunu${status.count}"></span> 
            	  </div>
                  <div align="right">
                  	<button class="btn btn-warning btn-sm" onclick="location.href='favDelete.do?f_num=${fav.f_num}'">삭제</button>
                    <button class="btn btn-primary btn-sm">내일정에 추가</button>
                  </div>
               </div>
              
            </div>
         </div>
      </c:forEach>
      <br>
      <!-- 모달창 클릭 버튼 -->
      <div align="center">
         <button type="button" class="hn btn btn-info btn-sm" id="placeBtn">즐겨찾기한 장소 더보기</button>
      </div>
   </div>
   <!-- 즐겨찾기한 장소 끝 -->

   <!-- 즐겨찾기한 일정 시작 -->
   <div class="col-lg-4 row align-center" align="center" style="padding-right: 50; padding-top: 50px;" >
      <img align="middle" src="${pageContext.request.contextPath}/resources/img/planning.png">
      <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 일정</h3>
      <br>
      <c:forEach var="fav" items="${list2}" begin="0" end="3">
         <div class="w3-card-4 w3-margin" align="center" style="width: 90%">
            <div class="w3-container">
               <div style="width: 25%; height: 12%; float: left;">
                  <img width="60" height="60" src="${pageContext.request.contextPath}/resources/img/star2.png">
               </div>
               <div style="width: 75%; height: 12%; float: right;">
                  <h5 align="left" class="card-text">글 제목 : ${fav.s_title}</h5>
                  <h5 align="left" class="card-text">작성자 닉네임 : ${fav.td_nickname}</h5>
                  <div align="right">
                  	<button class="btn btn-warning btn-sm" onclick="location.href='favDelete.do?f_num=${fav.f_num}'">삭제</button>
                    <button class="btn btn-default btn-sm" onclick="location.href=''">일정 상세 보기</button>
                    <button class="btn btn-primary btn-sm" onclick="location.href=''">내 일정에 추가</button>
                  </div>
               </div>
            </div>
         </div>
      </c:forEach>
      <br>
      <!-- 모달창 클릭 버튼 -->
      <div align="center">
         <button type="button" class="hn btn btn-info btn-sm" id="scheduleBtn">즐겨찾기한
            일정 더보기</button>
      </div>
   </div>
   <!-- 즐겨찾기한 일정 끝 -->

   <!-- 즐겨찾기한 회원 시작 -->
   <div class="col-lg-4 row align-center" align="center" style="padding-top: 50px;">
      <img width="128" height="128" align="middle"
         src="${pageContext.request.contextPath}/resources/img/group.png">
      <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 회원</h3>
      <br>
      <c:forEach var="fav" items="${list3}" begin="0" end="3">
         <div class="w3-card-4 w3-margin" align="center" style="width: 90%">
            <div class="w3-container">
               <div style="width: 25%; height: 12%; float: left;">
                  <img style="width: 100%; max-height: 50%" id="imageFile" class="card-img-top" align="middle" src="imageView2.do?email=${fav.f_code3}" alt="프로필 사진">
               </div>
               <div class="card-body" style="width: 75%; height: 12%; float: right;">
                  <p align="left" class="card-text">회원 이메일 : ${fav.f_code3}</p>
                  <p align="left" class="card-text">회원 등급 : ${fav.td_score}</p>
                  <div align="right">
                  	<button class="btn btn-warning btn-sm" onclick="location.href='favDelete.do?f_num=${fav.f_num}'">삭제</button>
                    <button class="btn btn-primary btn-sm" onclick="location.href=''">내 일정에 추가</button>
                  </div>
               </div>
            </div>
         </div>
      </c:forEach>
      <br>
      <!-- 모달창 클릭 버튼 -->
      <div align="center">
         <button type="button" class="hn btn btn-info btn-sm" id="memberBtn">즐겨찾기한
            회원 더보기</button>
      </div>
   </div>
   <!-- 즐겨찾기한 회원 끝 -->
</div>
<br><br><br>
</div>
<br>
<!-- 중앙 컨텐츠 끝 -->


<!-- 모달창 시작 -->

<!-- 장소 모달창 시작 -->
<div class="modal fade hn" id="placeModal" tabindex="-1" role="dialog" 
   aria-labelledby="placeModalTitle" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-body" align="center">
            <img align="middle"
               src="${pageContext.request.contextPath}/resources/img/placeholder.png">
            <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 장소</h3>
            <br>
            <!-- 검색창 시작 -->
            <div class="col-lg-3">
               <select id="keyfield_place" style="width: 100%" name="keyfield">
                  <option value=""></option>
                  <option value="f_code1">장소코드</option>
               </select>
            </div>
            <div class="col-lg-6">
               <input style="width: 100%" type="text" name="keyword"
                  id="keyword_place">
            </div>
            <div class="col-lg-3">
               <input type="button" id="search_place" value="찾기" class="btn btn-default btn-sm">
               <input type="button" id="place_list_btn" value="목록" class="btn btn-default btn-sm">
            </div>
            <!-- 검색창 끝 -->
            <br>
            
            <div id="output1">
               	
            </div>
            <div id="noPaging1">
            <hr>            
			<ul class="paging_button pagination"></ul>
			</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
         </div>
      </div>
   </div>
</div>
<!-- 장소 모달창 끝 -->



<!-- 일정 모달창 시작 -->
<div class="modal fade hn" id="scheduleModal" tabindex="-1"
   role="dialog" aria-labelledby="scheduleModalTitle" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-body" align="center">
            <img align="middle"
               src="${pageContext.request.contextPath}/resources/img/planning.png">
            <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 일정</h3>
            <br>
            <!-- 검색창 시작 -->
            <div class="col-lg-3">
               <select id="keyfield_schedule" style="width: 100%" name="keyfield">
                  <option value=""></option>
                  <option value="s_title">제목</option>
                  <option value="s_tag">태그</option>
                  <option value="td_nickname">작성자 닉네임</option>
               </select>
            </div>
            <div class="col-lg-6">
               <input style="width: 100%" type="text" name="keyword"
                  id="keyword_schedule">
            </div>
            <div class="col-lg-3">
               <input type="button" id="search_schedule" value="찾기" class="btn btn-default btn-sm">
               <input type="button" id="schedule_list_btn" value="목록" class="btn btn-default btn-sm">
            </div>
            <!-- 검색창 끝 -->
            <br>
            <hr>
            <div id="output2"></div>
            <div id="noPaging2">
			<ul class="paging_button pagination"></ul>
			</div>
            <br>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
         </div>
      </div>
   </div>
</div>
<!-- 일정 모달창 끝 -->

<!-- 회원 모달창 시작 -->
<div class="modal fade hn" id="memberModal" tabindex="-1" role="dialog"
   aria-labelledby="memberModalTitle" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-body" align="center">
            <img align="middle"   src="${pageContext.request.contextPath}/resources/img/group.png">
            <h3 style="background-color: #d1d2d3;" align="center">즐겨찾기한 회원</h3>
            <br>
            <!-- 검색창 시작 -->
            <div class="col-lg-3">
               <select id="keyfield_member" style="width: 100%" name="keyfield">
               	  <option value=""></option>
                  <option value="td_nickname">작성자 닉네임</option>
                  <option value="email">작성자 이메일</option>
                  <option value="td_score">작성자 등급</option>
               </select>
            </div>
            <div class="col-lg-6">
               <input style="width: 100%" type="text" name="keyword"
                  id="keyword_member">
            </div>
            <div class="col-lg-3">
               <input type="button" id="search_member" value="찾기" class="btn btn-default btn-sm">
               <input type="button" id="member_list_btn" value="목록" class="btn btn-default btn-sm">
            </div>
            <!-- 검색창 끝 -->
            <br>
            <div id="output3"></div>
            <div id="noPaging3">
            <hr>
			<ul class="paging_button pagination"></ul>
			</div>
            <br>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
         </div>
      </div>
   </div>
</div>
<!-- 회원 모달창 끝 -->
