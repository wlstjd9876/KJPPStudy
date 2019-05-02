<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/css/gowith/gowithlist.css">
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
	}

</style>

<br><br>
	<h1 class="align-center hn">우리 함께, 떠나요!</h1>
		<br><br>
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/resources/img/friendship2.png">
		</div>
		<br><br>
		
		<!-- 검색시작 -->
		<form action="gowithList.do" id="search_form" method="get" >
			<ul class="search hn">			 	
			 		<select name="keyfield">
			 			<option value="td_nickname">닉네임</option>
			 			<option value="go_area">여행지</option>
			 			<option value="go_type">여행타입</option>
			 			<option value="all">전체</option>
			 		</select>
			 		<input type="text" name="keyword" id="keyword" autocomplete="false">
			 		<input type="submit" value="검색">
			</ul>
		</form>
		<!-- 검색끝 -->
		
		<br>
		<div class="align-right">
			<button type="button" class="btn btn-outline-primary btn-lg hn" 
			onclick="location.href='${pageContext.request.contextPath}/gowith/gowithRegister.do'">동행 구하기!</button>
		</div>
		<br>
		<body>
		<div class="align-center hn">
				<table class="table">
					<thead>
						<tr>
							<th>닉네임</th>
							<th>여행컨셉</th>
							<th>여행지</th>
							<th>연령대</th>
							<th>성별</th>
							<th>모집인원</th>
							<th>남은 인원</th>
							<th>마감일</th>
							<th>진행상황</th>  
						</tr>
					<tbody>
					<c:forEach var="go_list" items="${list}">
						<!-- 오늘 -->
						<c:set var="today" value="<%=new Date()%>"/>
						<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="today"/>
						<tr>
							<td>${go_list.td_nickname}</td>
							<td>${go_list.go_type}</td>
							<td>${go_list.go_area}</td>
							<td>${go_list.go_age}</td>
							<td>${go_list.go_gen}</td>
							<td>${go_list.go_member}명</td>
							<td> ${go_list.go_status}명 </td>
							<td><c:if test="${go_list.dday == 1}"><span class="red mini">D-1</span></c:if>
								<c:if test="${go_list.dday == 0}"><span class="red mini">D DAY!</span></c:if>
								<c:if test="${go_list.dday > 1}">${go_list.go_deadline}</c:if>
								<c:if test="${go_list.dday < 0}"><span class="red mini" style="text-decoration:line-through;">${go_list.go_deadline}</span></c:if>
							</td>
							<td>
							<c:choose>
								<c:when test="${go_list.go_status>0 && go_list.go_deadline >= today}">
								<a href="gowithRegisterDetail.do?go_num=${go_list.go_num}&email=${user_email}" class="btn-gradient blue mini">진행중</a>
								</c:when>
								<c:otherwise>
								<a href="gowithRegisterDetail.do?go_num=${go_list.go_num}&email=${user_email}" class="btn-gradient red mini">마감</a>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>					
					</c:forEach>		
					</tbody>
				</table>
		<div class="align-center">${pagingHtml}</div>
		</div>
		</body>


		
		