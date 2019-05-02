<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href='//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/data/location/style.css">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath }";
	var contentId = "${param.contentId}";
	var contenttypeid = "${param.contenttypeid}";
</script>
<!-- a를 집어 넣을때 하는 function이다. -->
<script type="text/javascript">
	$(document).ready(function(){
		$('#use_btn').click(function(){
			alert('일정에 추가했습니다.');
			opener.document.getElementById('sd_code').value = $('#kdk').val();
			self.close();
		});
	});
</script>

<script
	src="${pageContext.request.contextPath}/resources/js/data/location/detailform.js"></script>
<div id="contents">
	<div class="js_center align-center">
		<div class="headerMenu hn">
			<div class="Menu-tab tab-active" id="Menu-tab1"
				onclick="javascript:detail();">공통정보</div>
			<div class="Menu-tab" id="Menu-tab2" onclick="javascript:intro();">소개정보</div>
			<div class="Menu-tab" id="Menu-tab3">추가 이미지</div>
		</div><br>
		<a href="${pageContext.request.contextPath}/data/location/list.do" class="btn btn-primary hn">목록으로</a>
	</div>
	<br>
	<div id="output" class="view"></div>
	
	<!-- 사용하기 버튼 (a를 안에 넣는 버튼 함수) -->
	<input type="button" class="btn btn-primary hn" value="사용하기" id="use_btn">
	<input type="hidden" id="kdk" value="${contentId }">
</div>
