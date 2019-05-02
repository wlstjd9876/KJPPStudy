<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member/confirmNickname.js"></script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<br>
		<h1 class="hn">회원정보 수정</h1>
		<div class="col-md-offset-2 col-md-8 hn">   <!-- 파일 업로드 enctype 명시 -->
			
			<form:form id="nickname_form" action="updateNick.do" cssClass="form-horizontal"> 
			<form:hidden path="email"/>
			<div class="form-group">
				<label for="td_nickname" class="col-md-4 control-label">닉네임</label>
				<div class="col-md-8">
				    <div id="nick_div">
				    <span>${command.td_nickname}</span>
				    <input type="button" value="수정" id="nick_btn" class="btn btn-primary">
				    </div>
				    <div id="nick_form" style="display:none;">
						<form:input path="td_nickname" cssClass="form-control"/>
						<form:errors path="td_nickname" cssClass="error-color"/>
							<input type="button" id="confirmNickname" value="닉네임 중복체크" class="btn btn-primary">
							<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
							<span id="message_nickname"></span>
							<input type="button" value="취소" class="nick_reset">
							<button type="submit">변경</button>
					</div>  
				</div>
			</div>
			</form:form> 
		
			<form:form commandName="command" enctype="multipart/form-data" cssClass="form-horizontal" action="update.do" id="register_form">
			<form:hidden path="email"/>
			<form:errors element="div" cssClass="error-color"/>
			  
			<%-- <div class="form-group">
				<label for="td_nickname" class="col-md-4 control-label">닉네임</label>
				<div class="col-md-8">
					<form:input path="td_nickname" cssClass="form-control"/>
					<form:errors path="td_nickname" cssClass="error-color"/>
						<input type="button" id="confirmNickname" value="닉네임 중복체크" class="btn btn-primary">
						<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
						<span id="message_nickname"></span>
				</div>
			</div>   --%>
			
			<div class="form-group">
				<label for="upload" class="col-md-4 control-label">프로필 사진</label>
				<div class="col-md-8">
					<input type="file" name="upload" id="upload">
				</div>
			</div>
			
			<div class="form-group">
				<label for="td_content" class="col-md-4 control-label">자기 소개</label>
				<div class="col-md-8">
					<form:textarea path="td_content" cssClass="form-control"/>
					<form:errors path="td_content" cssClass="error-color"/>
				</div>
			</div>
			
			<div class="form-group">
			<label for="td_birth" class="col-md-4 control-label">생년월일</label>
				<div class="col-md-8">
					<input type="date" name="td_birth" id="td_birth"
			  	     size="6" maxlength="12" autocomplete="off" value="${command.td_birth}">
			  	     <form:errors path="td_birth" cssClass="error-color"/>
				</div>
			</div>
			
				<div class="form-group">
				<label for="td_gender" class="col-md-4 control-label">성별</label>  
				<div class="col-md-8">
					<input type="radio" name="td_gender" value="1" <c:if test="${command.td_gender==1}">checked</c:if>>남자
					<input type="radio" name="td_gender" value="2" <c:if test="${command.td_gender==2}">checked</c:if>>여자
					<form:errors path="td_gender" cssClass="error-color"/>
				</div>               
			</div>
			<div class="form-group">
				<div class="col-md-offset-5 col-md-7">
				<br>
					<input type="submit" value="전송" class="btn btn-default">
					<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'" class="btn btn-default">
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->