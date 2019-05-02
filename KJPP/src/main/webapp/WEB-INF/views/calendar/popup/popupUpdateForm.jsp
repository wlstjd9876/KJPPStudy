<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- <script>
	window.opener.document.getElementById("sd_num").value=document.getElementById("sd_num").value;
</script> -->
<div class="container">
	<div class="row">
		<h1>여행 상세 수정</h1>
		<form:form commandName="scheduleDetailCommand" action="updateDetail.do" id="updateDetail_form">
			<%-- <form:hidden path="email"/> --%>
			<form:hidden path="sd_num"/>
			<form:errors element="div" cssClass="error-color"/>
			<div class="form-group">
				<label for="sd_code" class="col-sm-3">관광지 코드</label>
				<form:hidden path="sd_code" class="form-control" value="${command.sd_code}"/>${command.sd_code}
			</div>
			<div class="form-group">
				<label for="sd_memo" class="col-xs-3">여행 메모</label>
				<div class="col-xs-9">
					<form:input path="sd_memo" class="form-control" value="${command.sd_memo}"/>
					<form:errors path="sd_memo" cssClass="error-color"/>
				</div>
			</div>
			<div class="form-group">
				<label for="sd_money" class="col-xs-3">경비</label>
				<div class="col-xs-9">
					<form:input path="sd_money" class="form-control" value="${command.sd_money}"/>원
					<form:errors path="sd_money" cssClass="error-color"/>
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<input type="submit" value="전송" class="btn btn-default">
				<input type="button" value="목록" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/calendar/popup/detail.do?sd_num=${command.sd_num}'">
			</div>
		</form:form>
	</div>
</div>