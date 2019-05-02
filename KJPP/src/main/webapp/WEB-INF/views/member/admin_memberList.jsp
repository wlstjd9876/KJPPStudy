<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row hn">
		<h1 class="hn">회원목록(관리자용)</h1>
		<form action="admin_list.do" id="search_form" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="email">ID</option>
						<option value="td_nickname">이름</option>
						<option value="all">전체</option>
					</select>
				</li>
				<li>
					<input type="text" name="keyword" id="keyword">
				</li>
				<li>
					<input type="submit" value="찾기">
					<input type="button" value="목록" onclick="location.href='admin_list.do'">
				</li>
			</ul>
		</form>
		<c:if test="${count == 0}">
		<div class="align-center hn">등록된 회원이 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
		<div class="table-responsive hn">
			<table class="table table-striped">
				<tr>
					<th>아이디</th>
					<th>닉네임</th>
					<th>가입일</th>
					<th>권한</th>
				</tr>
				<c:forEach var="member" items="${list}">
				<tr>
					<td>
						<c:if test="${t.t_auth==0}">${td.email}</c:if>
						<c:if test="${member.t_auth>0}"><a href="admin_detail.do?id=${td.email}">${td.email}</a></c:if>
					</td>
					<td>${td.td_nickname}</td>
					<td>${td.td_reg_date}</td>
					<td>
						<c:if test="${t.t_auth==0}">관리</c:if>
						<c:if test="${t.t_auth==1}">스태프</c:if>
						<c:if test="${t.t_auth==2}">일반회원</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="align-center">${pagingHtml}</div>
		</c:if>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->
