<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row hn">
		<div class="col-sm-4 col-lg-4">
		<br><br>
		<h1 class="hn" align="center">회원로그인</h1>
		</div>
		<form:form commandName="command" action="login.do" id="login_form">
			<form:errors element="div" cssClass="error-color"/>
			<br><br><br>
			<div class="col-sm-6 col-lg-6"><!-- 그리드 -->
			<div class="form-group custom-input">
				<div class="col-sm-3 col-lg-3 hn">
					<label for="email">아이디</label>
				</div>
				<div class="col-sm-9 col-lg-9">
					<form:input path="email" cssClass="form-control"/>
					<span id="check_id"></span>
					<form:errors path="email" cssClass="error-color"/>
				</div>
				</div>
			
			<div class="form-group custom-input">
				<div class="col-sm-3 col-lg-3 hn">
					<label for="td_password">비밀번호</label>
				</div>
				<div class="col-sm-9 col-lg-9">
					<form:password path="td_password" cssClass="form-control"/>
					<span id="check_passwd"></span>
					<form:errors path="td_password" cssClass="error-color"/>
				</div>
			</div>
			</div>
			<div class="col-sm-2 col-lg-2 custom-login-submit hn">
				<input class="btn btn-default custom-submit hn" type="submit" value="로그인">
				<br><br><br><br><br><br>
			</div>
			</form:form>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->