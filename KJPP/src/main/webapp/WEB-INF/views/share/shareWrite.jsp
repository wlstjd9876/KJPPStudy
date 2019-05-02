<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}

h1 {
	text-align: center;
}

#content1 {
	width: 600px;
	height: 300px;
	background-color: white;
	margin-left: 285px;
	border: 1px solid;
}

#img {
	text-align: center;
}
#calendar {
	width: 600px;
	height: 500px;
	margin-left: 285px;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
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
<!-- 팝업창 -->
<script type="text/javascript">
	$(document).ready(function() {//팝업창 처리
		
		var uri = "${pageContext.request.contextPath}/calendar/sharelisttPopup.do";
		var popupName = "childForm";
		var options = "width=1200, height=650, resizable = no, scrollbars = no";

		$('.popupBtn').click(function() {
			openDialog(uri, popupName, options, function(win) {
				var mydate = document.getElementById('startdate').value;
				  var s_num = document.getElementById('s_num').value;
			      var email =  "<%=(String) session.getAttribute("user_email")%>";
			      var calendar = $('#calendar').fullCalendar({
			         header : {
			            left : 'prev,next,today',
			            center : 'title',
			            right : 'listDay,listWeek,month'
			         },
			         eventLimit:true, //하루에 너무 많은 이벤트가 발생하면 popover 허용
			         selectable : true,
			         selectHelper : true,
			         events : function(start, end, timezone, callback) {
			            $.ajax({
			               url : '${pageContext.request.contextPath}/calendar/eventdetail.do',
			               type : 'post',
			               data : {
			                  s_num : s_num
			               },
			               dataType : 'json',
			               success : function(data) {
			            	  var color = '#'+data.color;
			                  var events = [];
			                  var list = data.list;
			                  $(list).each(function(index, item) {
								 var v = getDt10(mydate, item.sd_day-1);
								var startdate = v;
								//----------------------
								var title
								$.ajax({        
									url: '${pageContext.request.contextPath}/detailAjax',
									data:{contentId:item.sd_code},
									type: 'get',
									dataType: 'json',
									async:false,
									cache:false,
									timeout:30000,
									success: function(data){
										var myItem = data.response.body.items.item;
										var myBody = data.response.body;
										title = myItem.title;
										
									},
									error: function(XMLHttpRequest, textStatus, errorThrown) { 
										alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
									}  
								});
								//-----------------------
			                     events.push({
			                        title : item.sd_starttime + ' ' +title,
			                        start : startdate+'T'+item.sd_starttime+':00',
			                        end : startdate+'T'+item.sd_endtime+':00',
			                        color : color,
			                        allDay : false
			                        /* url : 'view.do?s_num=' + item.s_num */
			                     });
			                  });
			                  callback(events);
			               }
			            });
			         },
			         defaultDate : mydate
			      });
			});
			});
	});
	function openDialog(uri, name, options, closeCallback) {
	    var win = window.open(uri, name, options);
	    var interval = window.setInterval(function() {
	        try {
	            if (win == null || win.closed) {
	                window.clearInterval(interval);
	                closeCallback(win);
	            }
	        }
	        catch (e) {
	        }
	    }, 1000);
	    return win;
	};
	function getDt10(s, i){ 
	    var newDt = new Date(s); 
	    newDt.setDate( newDt.getDate() + i );
	    return converDateString(newDt); 
	    }
   function converDateString(dt){ 
		return dt.getFullYear() + "-" + addZero(eval(dt.getMonth()+1)) + "-" + addZero(dt.getDate()); 
		}
	function addZero(i){ 
		var rtn = i + 100; 
		return rtn.toString().substring(1,3); 
		}
	var result = '${result}';
	if(result == 'success'){
		alert('글이 등록되었습니다!');
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/share/shareWrite.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/gowith/gowitRegister.css">
<!-- 중앙 컨텐츠 시작 -->
<br>
<div class="container">
	<div>
		<h1 class="hn">
			<b>일정을 소개해주세요!</b>
		</h1>  
	</div>
	<br>
	<div id="img" class="hn">
		<img width="100" src="${pageContext.request.contextPath}/resources/img/scope.png">
	</div>
	<br> <br>
	<div id="calendar"></div>
	<br>
	<div id="img" class="hn">
		<div>
			<button type="button" class="popupBtn btn btn-default btn-lg" value="일정가져오기">나의 일정 가져오기!</button>
		</div>
	</div>
	<!-- 등록시작 -->
	<br> <br>
	<div class="container">
	<div class="col-sm-4 col-md-offset-4">
		<form:form commandName="command" action="write.do" id="registerform" enctype="multipart/form-data">
				<form:hidden path="s_num"/>
				<form:hidden path="startdate"/>
				<form:hidden path="email"/>
			<div class="form-group">
				<label for="num"></label>
				<form:input path="num" type="hidden"/>
			</div>
			<div class="form-group hn">
				<label for="title">제목</label> 
				<form:input path="title" type="text" class="form-control hn" placeholder="제목을 입력해주세요!" autocomplete="false"/>
			</div>
			
			<div class="form-group hn">
				<label for="content">내용</label>
				<form:textarea path="content" style="width:100%;" rows="5" cols="15" placeholder="내용을 입력해주세요!"
				padding="10px"/>
			</div>
			
			<div class="form-group hn">
				<label for="thumbfile">사진업로드</label>
					<input type="file" name="thumbfile" id="thumbfile">
			</div>
			<div class="form-group hn">
				<label for="photofile2">사진업로드</label>
					<input type="file" name="photofile2" id="photofile2">
			</div>
			<div class="form-group hn">
				<label for="photofile3">사진업로드</label>
					<input type="file" name="photofile3" id="photofile3">
			</div>
			<br><br>
			<div class="form-group text-center">
				<button type="submit" class="btn btn-lg-primary hn">등록하기</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-lg-warning hn" onclick="location.href='list.do'">목록으로</button>
			</div>
		</form:form>
	</div>
	</div>
</div>

