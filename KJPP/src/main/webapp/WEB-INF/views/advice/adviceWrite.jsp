<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/adviceWrite.js"></script>
<script>
	var context = "${pageContext.request.contextPath}";
</script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<h1>조언을 구해주세요!</h1>
		<form:form commandName="command" action="adviceWrite.do" id="registerform">
			<div class="col-md-12">
				<form:hidden path="s_num"/>
				<form:hidden path="email"/>
				<form:errors element="div" cssClass="form-horizontal error-color"/>
			</div>
			<!-- 왼쪽 -->
			<div class="col-md-6" style="padding: 10px;">
				
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
               			<input type="text" name="adv_phone" id="adv_phone" class="form-control">
               			<form:errors path="adv_phone" cssClass="error-color"/>
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

