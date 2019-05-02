<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/fullcalendar.css" />
<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/fullcalendar.min.css" /> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/lib/moment.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/fullcalendar.js"></script>
<!-- 한글지정 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/locale/ko.js"></script>
<!-- <script>
	window.opener.document.getElementsByClass("fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable").attr('href')=document.getElementById("sd_num").value;
</script> -->
<style>
body {
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	min-height: 900px;
}
	form ul{
		list-style:none;
		padding:0;
		margin:2px;
	}
	form ul li{
		margin:0 0 9px 0;
		padding:0;
	}
#wrap {
	width: 100%;
	margin: 0 auto;
}

#external-events {
	float: left;
	width: 150px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
	text-align: left;
}

#external-events h4 {
	font-size: 16px;
	margin-top: 0;
	padding-top: 1em;
}

#external-events .fc-event {
	margin: 10px 0;
	cursor: pointer;
}

#external-events p {
	margin: 1.5em 0;
	font-size: 11px;
	color: #666;
}

#external-events p input {
	margin: 0;
	vertical-align: middle;
}


#calendar {
	width: 900px;
	height: 600px;
}
</style>



<script type='text/javascript'>
	$(document).ready(function() {
		var aaa = '${mydate}';
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var email =  "<%=(String)session.getAttribute("user_email")%>";
		var calendar = $('#calendar').fullCalendar({
			

			header : {
				left : 'prev,next',
				center : 'title',
				right : 'today'
			},
			selectable : true,
			selectHelper : true,
			events : function(start, end, timezone, callback) {
				$.ajax({
					url : 'eventAll.do',
					type : 'post',
					data:{email:email},
					dataType : 'json',
					success : function(data) {
						var events = [];
						var list = data.list;
						$(list).each(function(index, item) {
							events.push({
								title : item.s_title,
								start : item.s_startdate,
								end : item.s_enddate,
								color : '#'+item.s_color,
								url: 'sfinPopup.do?s_startdate='+item.s_startdate+'&s_enddate='+item.real_enddate+'&s_num='+item.s_num
							});
						});
						callback(events);
					}
				});
			},
			defaultDate:aaa
		});
	});
	function dateDiff(_date1, _date2) {
	    var diffDate_1 = _date1 instanceof Date ? _date1 : new Date(_date1);
	    var diffDate_2 = _date2 instanceof Date ? _date2 : new Date(_date2);
	 
	    diffDate_1 = new Date(diffDate_1.getFullYear(), diffDate_1.getMonth()+1, diffDate_1.getDate());
	    diffDate_2 = new Date(diffDate_2.getFullYear(), diffDate_2.getMonth()+1, diffDate_2.getDate());
	 
	    var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
	    diff = Math.ceil(diff / (1000 * 3600 * 24));
	 
	    return diff;
	}
</script>

<div class="container" style="width: 900px; margin: 0 auto; height: 900px;">
	<div
		style="width: 900px; height: 100px; text-align: center; margin: 30px auto;">
		<input type="button" value="일정등록" class="btn btn-default"
			name="finish" id="finish" onclick="location.href='finish.do'">
		<input type="button" value="임시저장" class="btn btn-default" name="temp"
			id="temp" onclick="">
	</div>
	<div class="cal-body">
		<div id="calendar"></div>
	</div>
</div>
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
					</tr>
					<c:forEach var="schedule" items="${list2}">
					<c:if test="${schedule.s_finish == 0}">
						 <tr>
							<td>${schedule.s_num}</td>
							<td><a href="${pageContext.request.contextPath}/calendar/view.do?s_num=${schedule.s_num}">${schedule.s_title}</a></td>
							<td>${schedule.s_startdate}</td>
							<td>${schedule.s_share}</td>
						</tr> 
					</c:if>
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
					<c:forEach var="schedule" items="${list2}">
					<c:if test="${schedule.s_finish == 1}">
						 <tr>
							<td>${schedule.s_num}</td>
							<td><a href="${pageContext.request.contextPath}/calendar/view.do?s_num=${schedule.s_num}">${schedule.s_title}</a></td>
							<td>${schedule.s_startdate}</td>
							<td>${schedule.s_share}</td>
						</tr> 
					</c:if>
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
