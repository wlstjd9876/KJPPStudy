<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>  
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style2.css">

<script src="${pageContext.request.contextPath}/resources/js/popup.js"></script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}
</style>
<meta charset="UTF-8">
<!-- 신청서 리스트 -->
<title>신청서</title>
</head>
<body>
	<h1>신청서</h1>
	<div>
		<p>이메일 : ${command.email}</p>

		<p>인원수 : ${command.app_member}</p>

		<p>성별 : ${command.app_gen}</p>

		<div>
			<img src="imageView.do?app_num=${command.app_num}" style="max-width: 500px;">
		</div>

		<p>이유 : ${command.app_why}</p>
	</div>
	<!-- 신청자인 경우 수정/삭제 버튼 -->
	<c:if test="${!empty user_email && user_email==command.email && command.app_status==0}">
	<p>
		<input type="button" class="btn btn-primary hn" value="수정하기"
			onclick="location.href='${pageContext.request.contextPath}/popup/update.do?app_num=${command.app_num}&go_num=${go_num}'"><!-- get방식으로 app_num을 넘겨준다 -->
		<input type="button" class="btn btn-warning hn" value="삭제하기" onclick="location.href='${pageContext.request.contextPath}/popup/delete.do?app_num=${command.app_num}'">
	</p>
	</c:if>
	<!-- 동행글 작성자인 경우 신청 수락 버튼 -->
	<c:if test="${!empty user_email && user_email==command.w_email && command.app_status==0}">
	<p>
		<input type="button" value="신청 수락" 	onclick="location.href='${pageContext.request.contextPath}/popup/goappConfirm.do?app_num=${command.app_num}&go_num=${command.go_num}'">
	</p>
	</c:if> 
</body>
</html>