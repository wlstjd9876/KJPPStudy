<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
<style>
	form ul{
		list-style:none;
		padding:0;
		margin:2px;
	}
	form ul li{
		margin:0 0 9px 0;
		padding:0;
	}
</style>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<div class="container">
	<div class="row" align="center">
	<c:if test="${count == 0}">
	<br>
		등록된 일정이 없습니다.
	<br>
	</c:if>
	<c:if test="${count > 0}">
		<div class="hn" style="text-align: center; padding: 10px;">
		<br><br>
			<form action="list.do" id="search_form" class="form-inline" method="get">
				<div class="form-group">
					<select name="keyfield" class="form-control">
						<option value="s_title">일정제목</option>
						<option value="s_tag">연관검색어</option>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="keyword" id="keyword" class="form-control">
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-default btn-sm" value="찾기">
					<input type="button" class="btn btn-default btn-sm" value="목록" onclick="location.href='list.do'">
				</div>
				<br>
			</form>
		</div>
		<div class="col-md-6">
			<h1 style="font-family: 'Courgette', cursive;"><b>Dream Of</b></h1>
			<div class="hn">
			<br>
				<table class="table table-center">
					<tr>
						<th>글번호</th>
						<th>일정제목</th>
						<th>여행시작날짜</th>
						<th>공유여부</th>
						<th>완성/미완성</th>
					</tr>
					<c:forEach var="schedule" items="${list}">
				 <tr>
						<td>${schedule.s_num}</td>
						<td><a href="${pageContext.request.contextPath}/calendar/view.do?s_num=${schedule.s_num}">${schedule.s_title}</a></td>
						<td>${schedule.s_startdate}</td>
						<td>${schedule.s_share}</td>
						<td>${schedule.s_finish}</td>
					</tr> 
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="col-md-6">
			<h1 style="font-family: 'Courgette', cursive;"><b>Come True</b></h1>
			<div class="hn">
			<br>
				<table class="table">
					<tr>
						<th>글번호</th>
						<th>일정제목</th>
						<th>여행시작날짜</th>
						<th>공유여부</th>
					</tr>
					<c:forEach var="schedule" items="${list}">
					<tr>
						<td>${schedule.s_num}</td>
						<td><a href="${pageContext.request.contextPath}/calendar/detail.do?s_num=${schedule.s_num}">${schedule.s_title}</a></td>
						<td>${schedule.s_startdate}</td>
						<td>${schedule.s_share}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div style="text-align: center;">
			<!-- 페이징 처리 -->
		</div>
	</c:if>
	</div>
	<div class="row hn" style="text-align: center;">
		<input type="button" value="일정등록" class="btn btn-default" onclick="location.href='finish.do'">
	</div>
</div>