<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<script type="text/javascript">
   window.onload=function(){
			var birth = new Date('${member.td_birth}');
			var today = new Date();
			
			// 오늘날짜 객체의 연도에서 생년월일 객체의 연도를 뺀다.
			var years = today.getFullYear() - birth.getFullYear();
			
			//생년월일 객체의 연도를 오늘 날짜 객체의 연도로 변경
			birth.setFullYear(today.getFullYear());
			
			// 같은 연도가 된 객체를 비교하여 월일이 지났는 지 여부를 판단하여 years 를 뺀다
			if (today < birth) years--; 
			var span_years = document.getElementById('years');
			if(years>=0 && years<=9){
				span_years.innerHTML = '0~9';
			}else if(years>=10 && years<=19){
				span_years.innerHTML = '10대';
			}else if(years>=20 && years<=29){
				span_years.innerHTML = '20대';
			}else if(years>=30 && years<=39){
				span_years.innerHTML = '30대';
			}else if(years>=40 && years<=49){
				span_years.innerHTML = '40대';
			}else if(years>=50 && years<=59){
				span_years.innerHTML = '50대';
			}else if(years>=60){
				span_years.innerHTML = '60대';
			}
   };
</script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
		<br><br>
		<h3 class="hn" align="center">${member.td_nickname}님 상세정보</h3>
		<br><br>
		<!-- 프로필 보이기 -->
		<div class="form-group hn" style="text-align: center;">
			<c:if test="${!empty member.td_profile}">
				<div><img src="imageView.do?email=${member.email}" style="max-width:500px;"></div>
			</c:if>
			<c:if test="${empty member.td_profile}">
				<c:if test="${member.td_gender==1}">
					<img src="${pageContext.request.contextPath}/resources/img/man.png" width="100px">
				</c:if>
	         	<c:if test="${member.td_gender==2}">
	         		<img src="${pageContext.request.contextPath}/resources/img/woman.png" width="100px">
				</c:if>
			</c:if>   
		</div>		
		
		<!-- 등급 표시 -->
		<div class="hn" style="text-align: center;">
			<c:if test="${0<=member.td_score && member.td_score<10}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/bronze.png">
			회원님의 등급은 브론즈 입니다!
			</c:if>
			<c:if test="${10<=member.td_score && member.td_score<50}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/silver.png">
			회원님의 등급은 실버입니다!
			</c:if>
			<c:if test="${50<=member.td_score && member.td_score<100}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/gold.png">
			회원님의 등급은 골드 입니다!
			</c:if>
			<c:if test="${100<=member.td_score && member.td_score<250}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/platinum.png">
			회원님의 등급은 플래티넘 입니다!
			</c:if>
			<c:if test="${250<=member.td_score}">
			<img src="${pageContext.request.contextPath}/resources/img/icon/diamond.png">
			회원님의 등급은 다이아몬드 입니다!
			</c:if>
			<br><br>
		</div>
		
         <!-- 상세내용 보이기 -->
        <div align="center">
		<table class="table hn" style="text-align: center; width: 600px;">
			<tr>
				<td style="width:30%">아이디</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>회원점수</td>
				<td>${member.td_score}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td><c:if test="${member.td_gender==1}">남자</c:if><c:if test="${member.td_gender==2}">여자</c:if></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.td_birth}</td>
			</tr>		
			<tr>
				<td>연령대</td>
				<td><span id="years"></span>입니다!</td>
			</tr>
			<tr>
				<td>가입날짜</td>
				<td>${member.td_reg_date}</td>
			</tr>	
			<tr>
				<td>자기소개</td>
				<td>${member.td_content}</td>
			</tr>			
		</table>
		<br><br>
		</div>
		
		<div class="form-group hn" style="text-align: center;">
			<input type="button" value="수정" class="btn btn-default" onclick="location.href='update.do'">
			<input type="button" value="회원탈퇴" class="btn btn-default" onclick="location.href='delete.do'">
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->
