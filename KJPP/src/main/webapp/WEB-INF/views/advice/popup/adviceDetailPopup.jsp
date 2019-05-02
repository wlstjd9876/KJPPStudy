<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/fullcalendar.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fullcalendar.js"></script>
<!-- �ѱ����� -->
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
                    duration: {days: addDay}   //�ϼ��� �޾Ƽ� �־��ָ� Ÿ�����̺� ���� ���� ���� ���� ��...
                }
            },
            
            selectable:true, //�̺�Ʈ ���� ���
            eventLimit:true, //�Ϸ翡 �ʹ� ���� �̺�Ʈ�� �߻��ϸ� popover ���
            
            //navLinks : ��/�ֺ� �޷¿��� ���ڸ� Ŭ���ϸ� �Ϻ� ����� ��ȯ�ϴ� ��� ��뿩��
            navLinks: false,
            //editable : ����� �޷¿��� ������ ǥ���� �ٸ� ���콺�� �̵��� �� �ְ� �ϴ� ��
            editable: true,
            
            //allDaySlot : week - allDay �������
            allDaySlot: false,
            
            //��ũ�� ���� �ð� ����
            scrollTime: '08:00:00',
            
            titleFormat: 'YYYY MMMM',
            
            //�̺�Ʈ ����
            //���� ������ ������
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
                                title: item.sd_code + ' ������ �̸�',
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
	<h4> ${command.email} ����</h4>  
	<div class="row">
		<div class="col-md-10 col-md-offset-1" style="padding: 10px;">
			<div class="row">
				<label for="ar_comment" class="col-md-4">�� ����</label>
				<div class="col-md-8">
	             			${command.ar_comment}
	         			</div>
			</div>
			<div class="row">
				<label for="startdate" class="col-md-4">��� ��¥</label>
	         			<div class="col-md-8">
	             			${command.startdate}
	         			</div>
	          	</div>
	          	<div class="row">
				<label for="enddate" class="col-md-4">���� ��¥</label>
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
		<input type="button" value="����" onclick="location.href='adviceModify.do?ar_num=${command.ar_num}'" class="btn btn-default">
		<input type="button" value="����" onclick="location.href='adviceDelete.do?ar_num=${command.ar_num}'" class="btn btn-default">
	</div>
</div>
