<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style2.css">

<script src="${pageContext.request.contextPath}/resources/js/popup.js"></script>


<!-- 신청서 작성하는 폼 레이어 팝업 -->
<div id="contents">
		
		<h2 class="hn">수정하기</h2>
		<br>${gowith}

		<form:form commandName="command" action="update.do"
			enctype="multipart/form-data" id="register_form">
			<form:hidden path="app_num"/>
			<p>
				이메일: ${command.email}
			</p>
			 <p>
				<label for="app_member">신청 인원</label>
				<form:select path="app_member">
				    <option value="">신청 인원을 선택하세요</option>
				    <c:forEach var="i" begin="1" end="${count}">				    
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
			<br>
			<span>사진등록</span>
			<label for="app_photofile"></label>
			<input type="file" id="app_photofile" name="app_photofile">
			<br>
			<br>
			
			<span>신청이유</span>
			<p>
			
				<form:textarea path="app_why" rows="3" cols="50"
					style="resize: none;" />
			</p>
			<p>
				<input type="submit" class="myButton" value="수정하기">
				<button type="submit" class="closeButton div vc">취소하기</button>
			</p>
		</form:form> 
	</div>