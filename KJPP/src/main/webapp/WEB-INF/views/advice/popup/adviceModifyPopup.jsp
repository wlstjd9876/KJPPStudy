<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adviceWrite/style.css">
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<h1>글 수정</h1>
		<form:form commandName="command" action="adviceModify.do" id="modifyform">
			<form:hidden path="ar_num"/>
			<div class="col-md-10 col-md-offset-1" style="padding: 10px;">
				<div class="form-group">
					<label for=ar_comment class="col-md-4 control-label">글 내용</label>
					<div class="col-md-8">
						<form:input path="ar_comment" cssClass="form-control"/>
						<form:errors path="ar_comment" cssClass="error-color"/>
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
