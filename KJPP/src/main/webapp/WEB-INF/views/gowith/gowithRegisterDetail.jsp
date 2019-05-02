<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/gowith/gowithRegister.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style2.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/gowith/popup.js"></script>	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/gowith/gowithApp.js"></script>	
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
	height: 700px;
	background-color: white;
	border: 3px solid;
	text-align: center;
	border: 1px solid;
}

#content2 {
	width: 1000px;
	height: 300px;
	/* background-color: white; */
	margin-left: 80px;
	/* border: 1px solid; */
	text-align: left;
}

#content3 {
	width: 47%;
	height: 400px;
	background-color: white;
	border: 3px solid;
	text-align: left;
	float: left;
	padding: 10px;
}

#content4 {
	width: 47%;
	height: 400px;
	background-color: white;
	border: 3px solid;
	text-align: left;
	float: right;
	padding: 10px;
}

#content5 {
	
	width: 900px;
	min-height:800px;
	margin-top: 10px;
}

#img {
	text-align: center;
}
li{
	margin-top: 3px;
}
#calendar {
	width: 600px;
	height: 500px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
//다이얼로그
$('#close').click(function() {
	$('#pop').hide();
	});
//팝업창 처리
$('.popupBtn').click(function() {
	window.open("${pageContext.request.contextPath}/popup/popup.do?app_num="
				+ $(this).attr('data-num')+"&go_num="+$(this).attr('data-gonum'),//this는 함수의 나의값을 불러서 .attr('data-num')반복되는값을 지정해주는것이다.
				"childForm","width=400, height=500, resizable = no, scrollbars = no");});

var mydate = '${gowith.go_startdate}';
  var s_num = '${gowith.s_num}';
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
<!-- ==========================상세페이지 시작================================================-->
<div align="center">
	<div>
		<h1 class="hn">
			<b>동행 일정을 확인하세요!</b>
		</h1>
	</div>
	<br>
	<div id="img" class="hn">
		<img src="${pageContext.request.contextPath}/resources/img/team2.png" width="100">
	</div>
	<br> <br>
	<div id="calendar"></div>
	<br>
	<div id="img" class="hn"></div>
	<div class="dishes padd con">
		<div class="container">		
		<!--사진 시작-->
		 <c:if test="${!empty gowith.go_photo1 || !empty gowith.go_photo2 || !empty gowith.go_photo3 || !empty gowith.go_photo4}">
			<div id="content2">
				<h2 class="hn">&nbsp;&nbsp;여행 사진이에요!</h2>
				<div class="border"></div>
				<div class="col-md-3 col-sm-6">
					<div class="dishes-item-container">
					    <c:if test="${!empty gowith.go_photo1}">
						<div class="img-frame">
							<img id="imageFile1" src="imageView1.do?go_num=${gowith.go_num}&photo_type=1" class="img-responsive" alt="" />
							<div class="img-frame-hover"></div>
						</div>
						</c:if>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="dishes-item-container">
					    <c:if test="${!empty gowith.go_photo2}">
						<div class="img-frame">
							<img id="imageFile1" src="imageView1.do?go_num=${gowith.go_num}&photo_type=2" class="img-responsive" alt="" />
							<div class="img-frame-hover"></div>
						</div>
						</c:if>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="dishes-item-container">
					    <c:if test="${!empty gowith.go_photo3}">
						<div class="img-frame">
							<img id="imageFile1" src="imageView1.do?go_num=${gowith.go_num}&photo_type=3" class="img-responsive" alt="" />
							<div class="img-frame-hover"></div>
						</div>
						</c:if>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="dishes-item-container">
					    <c:if test="${!empty gowith.go_photo4}">
						<div class="img-frame">
							<img id="imageFile1" src="imageView1.do?go_num=${gowith.go_num}&photo_type=4" class="img-responsive" alt="" />
							<div class="img-frame-hover"></div>
						</div>
						</c:if>
					</div>
				</div>
				<br> <br> <br> <br>
			</div>
			</c:if>
			<!--사진끝 -->
			<br><br><br>
			<!--당부의말, 프로필-->
			<div id="content5">
				<div id="content3" class="align-center hn">
					<h2 class="hn">등록자 프로필!</h2>
					<br>
					<div align="center">
					<img width="100" id="imageFile" src="imageView.do?email=${gowith.email}"/>
					</div>
					
					<ul>
						<li style="list-style: none;">닉네임: ${gowith.td_nickname}</li>
						<li style="list-style: none;">이메일: ${gowith.email}</li>
						<li style="list-style: none;">성별: <c:if test="${gowith.td_gender==1}">
								<a>남자</a>
							</c:if> <c:if test="${gowith.td_gender==2}">
								<a>여자</a>
							</c:if>
						</li>
						<li style="list-style: none;">나이: ${gowith.td_birth}</li>
						<li style="list-style: none; width: 100%; height: 100%">
							<c:if test="${0<=gowith.td_score && gowith.td_score<10}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/bronze.png">
			브론즈 등급 회원입니다!
			</c:if>
			<c:if test="${10<=gowith.td_score && gowith.td_score<50}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/silver.png">
			실버 등급 회원입니다!
			</c:if>
			<c:if test="${50<=gowith.td_score && gowith.td_score<100}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/gold.png">
			골드 등급 회원입니다!
			</c:if>
			<c:if test="${100<=gowith.td_score && gowith.td_score<250}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/platinum.png">
			플래티넘 등급 회원입니다!
			</c:if>
			<c:if test="${250<=gowith.td_score}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/diamond.png">
			다이아몬드 등급 회원입니다!
			</c:if>
						</li>
					<li style="list-style: none;"> <br></li>
					</ul>
					
		<br>
					
				</div>

				<div id="content4">
					<h2 class="hn">여행날짜</h2><span><b>${gowith.go_startdate} ~ ${gowith.go_enddate}</b></span>
					<br><br><br><br>
					<h2 class="hn">당부의 말!</h2>
					<div class="hn">${gowith.go_say}</div>
				</div>
			<!--당부의말, 프로필 끝-->
			
		<div class="col-lg-12" align="right">			
			<c:if test="${!empty user_email && user_email == gowith.email}">
			<br><br>
				<input type="button" value="삭제" class="btn btn-warning hn" onclick="location.href='${pageContext.request.contextPath}/gowith/delete.do?go_num=${gowith.go_num}'">		
				<input type="button" value="수정"  class="btn btn-primary hn" onclick="location.href='${pageContext.request.contextPath}/gowith/gowithModify.do?go_num=${gowith.go_num}'">	
			</c:if>
		</div>
		<!-- 신청하기 버튼  -->
		<div id="contents" class="text-center col-lg-12">
			<br><br><br>
			<c:set var="today" value="<%=new Date()%>"/>
			<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="today"/>
			<c:if test="${!empty user_email && user_email != gowith.email && gowith.go_status > 0 && count==0 && gowith.go_deadline >= today}">
			<a href="#layerpopup"> <input type="button" class="btn btn-primary hn" value="신청하기"></a> &nbsp;&nbsp;
			</c:if>			
			<button type="button" class="btn btn-warning hn" onclick="location.href='${pageContext.request.contextPath}/gowith/gowithList.do'">목록으로</button>
		</div>
		
		</div> 
			<!--======================== 상세페이지 끝 ======================================= -->
			
			<!-- =======================신청 시작=========================================== -->
			<div id="content6">
			<!-- 신청자 List 띄우는 레이어팝업 -->
			<div class="col-lg-12"><br><br><br>
			<c:set var="today" value="<%=new Date()%>"/>
				<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="today"/>
				<c:if test="${gowith.go_status == 0 || gowith.go_deadline < today}">
					<h1 align="center" class="hn" style="color: #ff6c32;"><동행 신청이 마감되었습니다></h1>
				</c:if>
			<br><br>
				<h1 class="hn">신청자 목록</h1>
				<br>
			</div>
				<c:forEach var="app" items="${list}" varStatus="status">				
					<div class="card col-lg-3 col-sm-3">
						<c:if test="${app.app_gen == '남자'}">
						<img src="../resources/img/gowithphoto/men.png" style="width: 40%">
						</c:if>
						<c:if test="${app.app_gen == '여자'}">
						<img src="../resources/img/gowithphoto/women.png" style="width: 40%">
						</c:if>
						<c:if test="${app.app_gen == '혼성'}">
						<img src="../resources/img/gowithphoto/mix.png" style="width: 40%">
						</c:if>
						<p class="hn">신청 인원수 : ${app.app_member}명</p>
						<!-- 팝업창 누르는 버튼 -->
						<c:if test="${gowith.email==user_email || app.email==user_email}">
							<input type="button" value="${app.td_nickname} 님의 신청서" class="popupBtn hn" data-num="${app.app_num}" data-gonum="${gowith.go_num}">
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<br> <br>

</div>




<!--신청서 작성하는 폼 레이어 팝업 ==================================================== -->

<div id="layerPopup" class="formtag">
	<h2 class="hn">신청하기</h2>
	<br>
	<form:form commandName="command" action="gowithappList.do"
		enctype="multipart/form-data" id="app_form">
		<input type="hidden" name="go_num" value="${param.go_num}"/>
		<form:errors element="div" cssClass="form-horizontal error-color" />
		<div class="form-group align">
			<p>	
				<label for="email">신청자 이메일 </label>
				<input type="hidden" id="email" name="email" class="form-control"
					value="${user_email}"/>${user_email}
			</p>
			<p>		
				<label for="td_nickname">신청자 닉네임</label>
				<input type="hidden" id="td_nickname" name="td_nickname" class="form-control"
					value="${user_nickname}"/>${user_nickname}
				<form:errors path="email" cssClass="error-color" />
				<input type="hidden" id="go_num" name="go_num" class="form-control" value="${gowith.go_num}">
			</p>
			<p>
			<label for="app_member">신청 인원</label>
				<form:select path="app_member">
				    <option value="">신청 인원을 선택하세요</option>
				    <c:forEach var="i" begin="1" end="${gowith.go_status}">				    
					<form:option value="${i}" />
					</c:forEach>
				</form:select>
				<span id="app_member_msg"></span>
			</p>
			<p>
				<label for="app_gen">성별</label>
				<form:select path="app_gen">
					<option value="">성별을 선택하세요</option>
					<form:option value="남자" />
					<form:option value="여자" />
					<form:option value="혼성" />
				</form:select>
				<span id="app_gen_msg"></span>
			</p>
			<br> <span>사진등록</span> <label for="app_photofile"></label> 
			<input type="file" id="app_photofile" name="app_photofile"> <br>
			<span>신청이유</span>
			<p>
				<form:textarea path="app_why" rows="3" cols="50" style="resize: none;"/>
			</p>
			<div align="center">
			<input type="submit" class="btn btn-primary btn-md hn" value="신청하기">
			&nbsp;&nbsp;
			<button type="button" class="btn btn-primary btn-md  hn">취소하기</button>
			</div>
		</div>
	</form:form>
</div>



