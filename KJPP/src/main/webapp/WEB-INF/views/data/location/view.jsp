<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href='//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/data/location/style.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath }";
	var contentId = "${param.contentId}";
	var contenttypeid = "${param.contenttypeid}";
	
	
	
</script>

<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}

#substance{
	width: 1170px;
	height: 400px;
	

}
div#reply_div{
	padding:5px 10px 40px 10px;
	margin-top:10px;
	background-color:#eeeeee;
	

}
div#re_second{
	float:left;
	width:20%;
	margin-bottom:10px;
}
span.letter-count{
	font-size:10pt;
	color:#999;
}
.align-right{
	text-align:right;
}
form#re_form{
	width:650px;
	margin:0 auto;
}

</style>
<script>
function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
  }
  x[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " w3-opacity-off";
}
</script>


<script
	src="${pageContext.request.contextPath}/resources/js/data/location/detailform.js"></script>
<div id="contents">
	<div class="js_center align-center">
		<div class="headerMenu hn">
			<div class="Menu-tab tab-active" id="Menu-tab1"
				onclick="javascript:detail();">공통정보</div>
			<div class="Menu-tab" id="Menu-tab2" onclick="javascript:intro();">소개정보</div>
			<div class="Menu-tab" id="Menu-tab3" onclick="javascript:imageo();">추가 이미지</div>  
		</div><br>
		<a href="${pageContext.request.contextPath}/data/location/list.do" class="btn btn-primary hn">목록으로</a>
		<a class="btn btn-primary hn" onclick="javascript:insert()">즐겨찾기추가하기</a>
		
	</div>  
	<br>
	
	<div id="output" class="view"></div>
</div>
