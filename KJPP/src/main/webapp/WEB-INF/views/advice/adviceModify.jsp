<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adviceWrite/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/adviceModify.js"></script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<h1>글 수정</h1>
		<form:form commandName="command" action="adviceModify.do" id="modifyform">
			<div class="col-md-12">
				<form:hidden path="email"/>
				<form:hidden path="adv_num" class="adv_num"/>
				<form:errors element="div" cssClass="form-horizontal error-color"/>
			</div>
			<div class="col-md-10 col-md-offset-1" style="padding: 10px;">
				<div class="form-group">
					<label for="adv_title" class="col-md-4 control-label">글 제목</label>
					<div class="col-md-8">
						<form:input path="adv_title" cssClass="form-control"/>
						<form:errors path="adv_title" cssClass="error-color"/>
					</div>
				</div>
				<div class="form-group">
					<label for="adv_phone" class="col-md-4 control-label">전화번호</label>
           			<div class="col-md-8">
               			<form:input path="adv_phone" cssClass="form-control"/>
               			<form:errors path="adv_phone" cssClass="error-color"/>
           			</div>
				</div>
				<div class="form-group">
					<label for="startdate" class="col-md-4 control-label">출발 날짜</label>
           			<div class="col-md-8">
               			<input type="date" value="${command.startdate}" name="startdate" id="startdate" class="form-control">
               			<form:errors path="startdate" cssClass="error-color"/>
           			</div>
            	</div>
            	<div class="form-group">
					<label for="enddate" class="col-md-4 control-label">종료 날짜</label>
					<div class="col-md-8">
						<input type="date" value="${command.enddate}" name="enddate" id="enddate" class="form-control">
						<form:errors path="enddate" cssClass="error-color"/>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<h3>상세일정</h3>
				<!-- 만들어진 일정 넣을 부분 -->
				<div class="adv_Plan"></div>
			</div>
			<!-- 버튼 -->
			<div class="col-md-12" style="text-align: center; padding: 10px;">
				<input type="submit" value="수정" class="btn btn-default">
				<input type="button" value="목록으로" onclick="location.href='adviceList.do'" class="btn btn-default">
			</div>
		</form:form>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->
