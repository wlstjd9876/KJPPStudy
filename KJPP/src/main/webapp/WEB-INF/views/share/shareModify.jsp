<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</style>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/share/shareWrite.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/gowith/gowitRegister.css">
<!-- 중앙 컨텐츠 시작 -->
<br>
<div class="container">
	<div>
		<h1 class="hn">
			<b>일정을 수정해주세요!</b>
		</h1>
	</div>
	<br>
	<div id="img" class="hn">
		<img width="100" src="${pageContext.request.contextPath}/resources/img/scope.png">
	</div>
	<br> <br>
	<div id="content1">상세일정 가져올 거에요~</div>
	<br>
	<div id="img" class="hn">
		<div>
			<button type="button" class="btn btn-default btn-lg" value="일정가져오기">나의
				일정 가져오기!</button>
		</div>
	</div>
	
	  
	  
	<!-- 수정 시작 -->
<div class="container">
	<div class="col-sm-4 col-md-offset-4">
		<form:form commandName="command" action="update.do" enctype="multipart/form-data" id="register_form"> <!-- 파일 첨부시 enctype 반드시 명시 필요★ -->
			<form:hidden path="email"/> <!-- 자동으로 id값 읽어옴 -->
			<form:hidden path="num"/> <!-- 자동으로 num값 읽어옴 -->
			<form:errors element="div" cssClass="error-color"/>
			
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
					<c:if test="${!empty command.thumbfile}">
					<br>
					<span>파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
			</div>
			<div class="form-group hn">
				<label for="photofile2">사진업로드</label>
					<input type="file" name="photofile2" id="photofile2">
					<c:if test="${!empty command.photofile2}">
					<br>
					<span>파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
			</div>
			<div class="form-group hn">
				<label for="photofile3">사진업로드</label>
					<input type="file" name="photofile3" id="photofile3">
					<c:if test="${!empty command.photofile3}">
					<br>
					<span>파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
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
