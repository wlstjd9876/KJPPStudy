<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/replyWrite.js"></script>
<script>
	var context = "${pageContext.request.contextPath}";
</script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<h1>도와줄께요</h1>
		<form:form commandName="command" action="replyWrite.do" id="registerform">
			<div class="col-md-12">
				<form:hidden path="email"/>
				<form:hidden path="adv_num"/>
				<form:errors element="div" cssClass="form-horizontal error-color"/>
			</div>
			<!-- 왼쪽 -->
			<div class="col-md-6" style="padding: 10px;">
				<div class="form-group">
					<label for="ar_comment" class="col-md-4 control-label">내용</label>
					<div class="col-md-8">
						<textarea rows="5" cols="40" name="ar_comment" class="form-control" style="resize:none; "></textarea>
						<form:errors path="ar_comment" cssClass="error-color"/>
					</div>
				</div>
				<div class="form-group">
					<label for="startdate" class="col-md-4 control-label">출발 날짜</label>
           			<div class="col-md-8">
               			<input type="date" name="startdate" id="startdate" class="form-control">
               			<form:errors path="startdate" cssClass="error-color"/>
           			</div>
            	</div>
            	<div class="form-group">
					<label for="enddate" class="col-md-4 control-label">종료 날짜</label>
					<div class="col-md-8">
						<input type="date" name="enddate" id="enddate" class="form-control">
						<form:errors path="enddate" cssClass="error-color"/>
					</div>
				</div>
				<div class="form-group" style="text-align: center;">
					<input type="button" name="makePlan" id="makePlan" value="일정만들기" class="btn btn-default makePlan">
					<input type="button" name="deletePlan" id="deletePlan" value="삭제하기" style="display: none;" class="btn btn-default deletePlan">
					<input type="button" value="일정가져오기" name="getPlan" id="getPlan" class="btn btn-default">
				</div>
				<div class="form-group">
					<!-- 내 일정 가져오는 부분 -->
					<div class="adv_myPlan"></div>
				</div>
			</div>
			<!-- 오른쪽 -->
			<div class="col-md-6">
				<!-- 일정 넣을 부분 -->
				<div class="adv_plan"></div>
			</div>
			<!-- 버튼 -->
			<div class="col-md-12" style="text-align: center; padding: 10px;">
				<input type="submit" value="전송" class="btn btn-default">
				<input type="button" value="목록으로" onclick="location.href='adviceList.do'" class="btn btn-default">
			</div>
		</form:form>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->