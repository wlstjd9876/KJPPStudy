<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}

h1 {
	text-align: center;
}

#content {
	width: 600px;
	height: 600px;
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gowith/gowithRegister.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/gowith/gowitRegister.css">
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
		
		var uri = "${pageContext.request.contextPath}/calendar/listPopup.do";
		var popupName = "childForm";
		var options = "width=1200, height=650, resizable = no, scrollbars = no";

		$('.popupBtn').click(function() {
			openDialog(uri, popupName, options, function(win) {
				var mydate = document.getElementById('go_startdate').value;
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
</script>
<br>

<div class="container">
	<div>
		<h1 class="hn">
			<b>동행 정보를 입력해주세요!</b>
		</h1>
	</div>
	<br>
	<div id="img" class="hn">
		<img src="${pageContext.request.contextPath}/resources/img/team2.png" width="100">
	</div>
	<br><br>
	<div id="calendar"></div>
	<br>
	<div id="img" class="hn">
		<div>
			<button type="button" class="popupBtn btn btn-default btn-lg" value="일정가져오기">나의 일정 가져오기!</button>
		</div>
	</div>
	<br> <br>
	<div class="col-sm-4 col-md-offset-4">
		<form:form commandName="command" action="gowithRegister.do" id="registerform" enctype="multipart/form-data">
				<form:hidden path="email"/>
				<form:input path="s_num" type="hidden"/>
			<div class="form-group">
				<label for="go_num"></label>
				<form:input path="go_num" type="hidden"/>
			</div>
			<div class="form-group">
				<label for="go_member" class="hn">모집 인원</label>
				<form:input path="go_member" type="number" class="form-control hn"
					min="1" max="9" placeholder="모집인원을 입력하세요!" />
			</div>

			<div class="form-group hn">
				<label for="go_age">모집 연령대</label>
				<form:select path="go_age" class="form-control hn">
					<form:option value="연령대를 선택하세요!"/>
					<form:option value="10대만"/>
					<form:option value="20대만"/>
					<form:option value="30대만"/>
					<form:option value="연령무관"/>
				</form:select>
			</div>
			<div class="form-group hn">
				<label for="go_age">모집 성별</label>
				<form:select path="go_gen" class="form-control hn">
					<form:option value="성별을 선택하세요!"/>
					<form:option value="여자만"/>
					<form:option value="남자만"/>
					<form:option value="성별무관"/>
				</form:select>
			</div>
			<div class="form-group hn">
				<label for="go_type" class="hn">여행 타입</label> 
				<form:select path="go_type" class="form-control hn">
					<form:option value="여행타입을 선택하세요!"/>
					<form:option value="힐링여행"/>
					<form:option value="먹방여행"/>
					<form:option value="레저여행"/>
					<form:option value="가성비여행"/>
					<form:option value="출사여행"/>
					<form:option value="자유여행"/>
				</form:select>
			</div>
			<div class="form-group hn">
				<label for="go_area">여행지</label> 
				<form:input maxlength="10" path="go_area" type="text" class="form-control hn" placeholder="여행지를 10자 이내로 입력하세요!" autocomplete="false"/>
			</div>
													
			<div class="form-group hn">
				<label for="go_startdate">여행 출발일</label> 
				<form:input path="go_startdate" type="date" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_enddate">여행 도착일</label> 
				<form:input path="go_enddate" type="date" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_deadline">신청 마감일</label> 
				<form:input path="go_deadline" type="date" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_photofile1">등록자 사진1</label> 
				<input name="go_photofile1" id="go_photofile1" type="file" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_photofile2">등록자 사진2</label> 
				<input name="go_photofile2" id="go_photofile2" type="file" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_photofile3">등록자 사진3</label> 
				<input name="go_photofile3" id="go_photofile3" type="file" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_photofile4">등록자 사진4</label> 
				<input name="go_photofile4" id="go_photofile4" type="file" class="form-control hn"/>
			</div>
			<div class="form-group hn">
				<label for="go_say">※&nbsp;당부의 말</label>
				<form:textarea path="go_say" style="width:100%;" rows="7"/>
			</div>
			<br>
			<br>
			<div class="form-group text-center">
				<button type="submit" class="btn btn-primary-lg hn">등록하기</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-warning-lg hn"
					onclick="location.href='${pageContext.request.contextPath}/gowith/gowithList.do'">목록으로</button>
			</div>
		</form:form>
	</div>
</div>


