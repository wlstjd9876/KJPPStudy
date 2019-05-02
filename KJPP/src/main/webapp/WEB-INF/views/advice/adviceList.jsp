<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adviceWrite/style.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=073c048326b3580b4c7257b8e804ee6b"></script>
<script type="text/javascript">
	var result = '${result}';
	if(result == 'success'){
		alert('처리가 완료되었습니다.');
	}
	
	$(document).ready(function(){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	    mapOption = { 
	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	   var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	});
</script>
<!-- 중앙 컨텐츠 시작 -->	
<div class="container">
	<div class="row hn">
		<br>
		<h1 class="hn">조언 목록</h1>
		<!-- 검색 -->
		<div style="text-align: center; padding: 10px;">
			<form action="adviceList.do" id="search_form" class="form-inline" method="get">
				<div class="form-group">
					<select name="keyfield" class="form-control">
						<option value="adv_title">제목</option>
						<option value="email">이메일</option>
						<option value="all">전체</option>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="keyword" id="keyword" class="form-control">
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-default btn-sm" value="찾기">
					<input type="button" class="btn btn-default btn-sm" value="목록" onclick="location.href='adviceList.do'">
				</div>
			</form>
		</div>
		
		<c:if test="${count == 0}">
			<br><br>
			<div class="align-center hn" align="center">등록된 게시물이 없습니다.</div>
			<br><br><br>
		</c:if>
		
		<c:if test="${count > 0}">
		<div class="col-md-12">
			<c:forEach var="advice" items="${list}">
			<div class="col-md-3">
				<div class="col-md-12">
					<div id="map" style="width: 255px; height: 255px;"></div>
				</div>
				<div class="col-md-12" style="text-align: center; padding: 10px;">
					<c:if test="${!empty user_email}">
					<input type="button" value="상세일정보기" class="btn btn-default"  onclick="location.href='adviceDetail.do?adv_num=${advice.adv_num}'">
					</c:if>
					<div class="col-md-12">
						<input type="hidden">${advice.adv_title}
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
		<div style="text-align: center;">${pagingHtml}</div>
		</c:if>

		<!-- 버튼 -->
		<div class="col-md-12" style="text-align: center;">
			<c:if test="${!empty user_email}">
			<input type="button" value="글쓰기" onclick="location.href='adviceWrite.do'" class="btn btn-default">
			</c:if>
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'" class="btn btn-default">
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->	
