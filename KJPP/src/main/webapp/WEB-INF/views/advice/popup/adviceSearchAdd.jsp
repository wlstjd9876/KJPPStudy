<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
		<h1>일정상세등록</h1>
		<form:form commandName="adviceDetailCommand" action="searchAdd.do" id="register_form">
			<form:hidden path="adv_num"/>
			<div class="form-group">
				<%-- <label for="sd_code" class="col-sm-3">관광지 코드</label>
				<form:hidden path="sd_code" class="form-control" value="${command.ad_code}"/>${command.ad_code} --%>
			</div>
			<div class="form-group">
				<label for="ad_day">일 차</label>
				<form:input path="ad_day" class="form-control"/>
				<%-- <form:errors path="sd_day" cssClass="error-color"/> --%>
			</div>
			<div class="form-group">
				<label for="starttime">일정 시작 시간</label>
				<input type="time" name="starttime" id="starttime" class="form-control">
			</div>
			<div class="form-group">
				<label for="endtime">일정 끝 시간</label>
				<input type="time" name="endtime" id="endtime" class="form-control">
			</div>
			<div class="form-group">
				<label for="ad_money">경비</label>
				<input type="number" name="ad_money" id="ad_money" class="form-control">원
			</div>
			<div class="form-group">
				<label for="ad_memo">일정 메모</label>
				<textarea rows="5" cols="40" name="ad_memo" class="form-control" style="resize:none; "></textarea>
			</div>
			<div class="form-group" style="text-align: center;">
				<input type="submit" value="추가" class="btn btn-default">
			</div>
		</form:form>
	</div>
</div>