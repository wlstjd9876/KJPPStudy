<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
		<h1>일정상세등록</h1>
		<form:form commandName="calendarDetailCommand" action="writeDetail.do" id="register_form">
			<%-- <form:hidden path="s_num"/> --%>
			<form:hidden path="sd_num"/>
			<div class="form-group">
				<label for="sd_code">관광지 코드</label>
				<form:input path="sd_code" class="form-control"/>
				<%-- <form:errors path="sd_code" cssClass="error-color"/> --%>
			</div>
			<div class="form-group">
				<label for="sd_day">일 차</label>
				<form:input path="sd_day" class="form-control"/>
				<%-- <form:errors path="sd_day" cssClass="error-color"/> --%>
			</div>
			<div class="form-group">
				<label for="sd_starttime">일정 시작 시간</label>
				<input type="time" name="sd_starttime" id="sd_starttime" class="form-control">
			</div>
			<div class="form-group">
				<label for="sd_endtime">일정 끝 시간</label>
				<input type="time" name="sd_endtime" id="sd_endtime" class="form-control">
			</div>
			<div class="form-group" style="text-align: center;">
				<input type="submit" value="전송" class="btn btn-default">
			</div>
		</form:form>
	</div>
</div>