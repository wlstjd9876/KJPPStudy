<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/advice.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/adviceDetail.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/fullcalendar.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fullcalendar.js"></script>
<!-- 한글지정 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/locale/ko.js"></script>
<script>
$(document).ready(function() {
    var addDay = 1;
    
    function setCalendar(){
        $('#calendar').fullCalendar({
            defaultView: 'agendaDay',
            
            header: {
                left: 'prev,next today', 
                center: 'title', 
                right: 'month,agendaDay'
            },
            
            views:{
                agendaChangeDay:{
                    type:'agenda',
                    duration: {days: addDay}   //일수를 받아서 넣어주면 타임테이블에 몇일 차로 넣을 껀지 들어갈...
                }
            },
            
            selectable:true, //이벤트 선택 허용
            eventLimit:true, //하루에 너무 많은 이벤트가 발생하면 popover 허용
            
            //navLinks : 월/주별 달력에서 일자를 클릭하면 일별 보기로 전환하는 기능 사용여부
            navLinks: false,
            //editable : 실행된 달력에서 일정을 표시한 바를 마우스로 이동할 수 있게 하는 것
            editable: true,
            
            //allDaySlot : week - allDay 사용유무
            allDaySlot: false,
            
            //스크롤 시작 시간 설정
            scrollTime: '08:00:00',
            
            titleFormat: 'YYYY MMMM',
            
            //이벤트 생성
            //월에 데이터 보여줌
            /* events: function(start, end, timezone, callback) {
                $.ajax({
                    url: 'scheduleAll.do',
                    type : 'post',
                    data : {},
                    dataType: 'json',
                    success: function(data) {
                        
                        var events = [];
                        var list = data.list;
                        
                        $(list).each(function(index, item) {
                            var now = new Date();
                            var nowMonth = ((now.getMonth()< 9) ? '0' : '') + (now.getMonth() + 1);
                            var nowDate = (((now.getDate() + item.sd_day - 1)< 10) ? '0' : '') + (now.getDate() + item.sd_day - 1);
                            
                            events.push({
                            	url:item.sd_num,
                                title: item.sd_code + ' 관광지 이름',
                                start: now.getFullYear() + '-' + nowMonth + '-' + (nowDate) + 'T' + item.sd_starttime,
                                end: now.getFullYear() + '-' + nowMonth + '-' + (nowDate) + 'T' + item.sd_endtime
                            });
                        });
                        callback(events);
                    }
                });
            } */
        });
    }
    
    setCalendar();
});
</script>
<script>
	var context = "${pageContext.request.contextPath}";
</script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<h2>${advice.adv_title}</h2>
		<input type="hidden" id="adv_num" name="adv_num" class="adv_num" value="${advice.adv_num}">
		<div class="form-group">
			<label>여행기간</label>
			${advice.startdate} ~ ${advice.enddate}
		</div>
		<div class="form-group">
			<!-- 일정 상세 목록 출력 -->
			<!-- <div class="adv_detail"></div> -->
			<div class="cal-body">
				<div id="calendar"></div>
			</div>
		</div>
		<!-- 버튼 -->
		<div class="form-group" style="text-align: center;">
			<c:if test="${!empty user_email && user_email == advice.email}">
				<input type="button" value="글수정" class="btn btn-default" id="adviceUpdate" name="adviceUpdate" onclick="location.href='adviceModify.do?adv_num=${advice.adv_num}'">
				<input type="button" value="글삭제" class="btn btn-default" id="adviceDelete" name="adviceDelete" onclick="location.href='adviceDelete.do?adv_num=${advice.adv_num}'">
			</c:if>
		</div>
		
		<!-- 댓글 -->
		<div align="center">
			<c:if test="${empty user_email}">로그인 해야 작성할 수 있습니다.</c:if>
			<c:if test="${!empty user_email && user_email != advice.email}">
				<input type="button" value="댓글쓰기" class="hn btn btn-info btn-sm" id="writeReply" onclick="location.href='adviceReplyWrite.do?adv_num=${advice.adv_num}'">
			</c:if>
		</div>

		<!-- 댓글 목록 출력 -->
		<div id="output"></div>
		
		<div class="paging-button" style="display: none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display: none;">
			<img src="${pageContext.request.contextPath}/resources/img/ajax-loader.gif">
		</div>
		
		<ul class="paging_button"></ul>
		<div id="wrap"></div>
		<ul class="paging_button"></ul>
		
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->