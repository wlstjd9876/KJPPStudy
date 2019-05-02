<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/w3.css">

<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.hn {
	font-family: 'Hanna', sans-serif;
}
</style>
<style>
.card {
  /* Add shadows to create the "card" effect */
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

/* On mouse-over, add a deeper shadow */
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.card:container {
  padding: 2px 16px;
}
</style>

<script type="text/javascript">
	var result = '${result}';
	if(result == 'success'){
		alert('글이 등록되었습니다!');
	}
</script>
<br><br><br>
		<h1 class="hn" align="center">어디까지 가봤니!?</h1><br>
		<div align="center">
			<img src="../resources/img/scope.png" width="80">
		</div>
<br><br>
		
<!-- =============메인시작================= -->		
<div class="container hn">

<!-- ==============================검색 시작==================================== -->
		<div align="center">
			<form action="list.do" id="search_form" method="get">
				<select name="keyfield">
					<option value="title">제목</option>
					<option value="email">이메일</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select> 
				<input type="text" name="keyword" id="keyword">
				<input type="submit" value="찾기">
			</form>
		</div><br>
<!-- ==============================검색  끝==================================== -->
<!-- 글쓰기버튼 -->
		<div align="right">
			<c:if test="${empty user_email}">
			로그인 하셔야 글쓰기가 가능합니다.
			</c:if>
			<c:if test="${!empty user_email}">
				<button class="btn btn-primary btn-md hn" onclick="location.href='write.do'" >글쓰기</button>
			</c:if>
				<button class="btn-btn-primary btn-md hn" onclick="location.href='list.do'">목록</button>
		</div><br>
<!-- 글쓰기버튼 끝 -->

<!-- ==============================카드 시작==================================== -->
	<c:if test="${count == 0}">
		<div class="align-center" align="center">등록된 게시물이 없습니다.<br><br></div>
	</c:if>		
		<c:if test="${count > 0}">
		<div class="row">
		<c:forEach var="share" items="${list}">
		<div class="col-md-4 col-lg-4">
			<div style="padding: 10px; margin-bottom: 5px;" class="card card:hover" onclick="location.href='${pageContext.request.contextPath}/share/shareDetail.do?num=${share.num}'">
  				<img id="imageFile" src="imageView.do?num=${share.num}" style="max-width: 100%; height: 400px;">
  			<div class="card:container hn">
    			<h3 class="hn"><b>${share.title}</b></h3>
    			<p>${share.td_nickname}</p>
  			</div>
			</div>
		</div>	
		</c:forEach>
		</div><br><br>
<!-- ==============================카드 끝==================================== -->		
		
		<div class="form-group">
			<div align="center">${pagingHtml}</div>
		</div>	
		</c:if>

</div>

<!-- 중앙 컨텐츠 끝 -->
