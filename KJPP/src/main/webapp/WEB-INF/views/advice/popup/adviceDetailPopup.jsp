<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	window.opener.document.getElementById("adv_num").value=document.getElementById("adv_num").value;
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/advice/advice.reply2.js"></script>
<div class="container">
	<h4> ${command.email} 일정</h4>  
	<div class="row">
		<div class="col-md-10 col-md-offset-1" style="padding: 10px;">
			<div class="row">
				<label for="ar_comment" class="col-md-4">글 내용</label>
				<div class="col-md-8">
	             			${command.ar_comment}
	         			</div>
			</div>
			<div class="row">
				<label for="startdate" class="col-md-4">출발 날짜</label>
	         			<div class="col-md-8">
	             			${command.startdate}
	         			</div>
	          	</div>
	          	<div class="row">
				<label for="enddate" class="col-md-4">종료 날짜</label>
				<div class="col-md-8">
					${command.enddate}
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="calendar"></div>
	</div>
	<div class="row">
		<input type="button" value="수정" onclick="location.href='adviceModify.do?ar_num=${command.ar_num}'" class="btn btn-default">
		<input type="button" value="삭제" onclick="location.href='adviceDelete.do?ar_num=${command.ar_num}'" class="btn btn-default">
	</div>
</div>
