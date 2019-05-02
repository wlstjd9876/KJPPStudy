<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<!-- 상단 네비게이션 시작 -->
<!-- Page Wrapper -->
<div class="wrapper hn">

	<!-- Header Start -->

	<div class="header hn">
		<div class="container hn">
			<!-- 현호야 여기봐 -->
			
				<div class="row">
				
					
				</div>  
			</div>
			<!-- 현호야 여기d -->
			<div class="row hn">
				<div class="col-md-1 col-sm-1">
				</div>
				<div class="col-md-2 col-sm-2">
					<!-- Link -->
					<div class="logo" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
							<!-- <img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/logo.png" alt="" /> -->
							<!-- Heading -->
							<h1 style="cursor: pointer;">Trips Come True</h1>
							<!-- Paragraph -->
							<p>상상 속 여행을 현실로</p>
						</div>
				</div>
			
				<div class="col-md-5 col-sm-5" align="left">
					<!-- Navigation -->
					<nav class="navbar navbar-default navbar-right" role="navigation">
						<div class="container-fluid">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
								<button type="button" class="navbar-toggle"
									data-toggle="collapse"
									data-target="#bs-example-navbar-collapse-1">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li class="dropdown visible-xs hn"><a href="#"
										class="dropdown-toggle hn" data-toggle="dropdown"> Show Me The Trip <b class="caret"></b>
									</a>
										<ul class="dropdown-menu">
											<li class="hn"><a href="${pageContext.request.contextPath}/share/list.do">공유해요</a></li>
										</ul></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><img
											src="${pageContext.request.contextPath}/resources/img/nav-menu/비행기.jpg"
											class="img-responsive hn" /> Show Me The Trip <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
								<li class="hn"><a href="${pageContext.request.contextPath}/share/list.do">어디까지 가봤니!?</a></li>
								</ul></li>
										
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><img
											src="${pageContext.request.contextPath}/resources/img/nav-menu/배.jpg"
											class="img-responsive" alt="" /> Help Come True <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
											<li class="hn"><a
												href="${pageContext.request.contextPath}/advice/adviceList.do">여행을 부탁해!</a></li>
													<li class="hn"><a
												href="${pageContext.request.contextPath}/gowith/gowithList.do">함께, 떠나요!</a></li>
										</ul></li>
										
									<li class="dropdown hn"><a href="#" class="dropdown-toggle hn"
										data-toggle="dropdown"><img
											src="${pageContext.request.contextPath}/resources/img/nav-menu/트레인.jpg"
											class="img-responsive" alt="" /> Data Factory <b
											class="caret"></b></a>
										<ul class="dropdown-menu hn">
											<li class="hn"><a
												href="${pageContext.request.contextPath}/data/location/list.do">관광지
													정보 상세보기</a></li>
											<li class="hn"><a
												href="${pageContext.request.contextPath}/data/location.do">지도별 관광지 정보</a></li>
											<li class="hn"><a
												href="${pageContext.request.contextPath}/data/chart.do">통계 보기</a></li>
										</ul></li>
									<li class="dropdown"><a href="#"
										class="dropdown-toggle" data-toggle="dropdown"><img
											src="${pageContext.request.contextPath}/resources/img/nav-menu/자전거.jpg"
											class="img-responsive" alt="" /> My Planner <b class="caret"></b></a>
										<ul class="dropdown-menu hn">
																<li ><a href="${pageContext.request.contextPath}/calendar/favList.do">나의 즐겨찾기</a></li>
																<li><a href="${pageContext.request.contextPath}/calendar/registerPlan.do">일정보기</a></li>
																<li><a href="${pageContext.request.contextPath}/calendar/finish.do">일정 바로 추가</a></li>
										</ul></li>
								</ul>
							</div>
							<!-- /.navbar-collapse -->
						</div>
						<!-- /.container-fluid --> 
					</nav>
				</div>
				<div class="col-md-2 col-sm-2" align="center">
				<div style="margin-top: 30px; " align="center">
						<!-- Button Kart -->
						<ul class="nav navbar-nav navbar-right nav-ex">
							<c:if test="${empty user_email}">
								<li class="hn"><a href="${pageContext.request.contextPath}/member/write.do">회원가입</a></li>
								<li class="hn"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
							</c:if>							
						</ul>
						<c:if test="${!empty user_email && user_auth !=3}">
						<div class="btn-cart-md hn" align="center">
							<a class="cart-link hn" href="#"> <!-- Image -->
								<c:if test="${user_gender==1}">
									<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/man.png"/>
								</c:if>
								<c:if test="${user_gender==2}">
									<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/woman.png"/>
								</c:if> <!-- Heading -->
								<c:if test="${0<=user_score && user_score<10}">
									<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/icon/bronze.png">
								</c:if>
								<c:if test="${10<=user_score && user_score<50}">
									<img class="img-responsive"
										src="${pageContext.request.contextPath}/resources/img/icon/silver.png">
								</c:if>
								<c:if test="${50<=user_score && user_score<100}">
									<img class="img-responsive"
										src="${pageContext.request.contextPath}/resources/img/icon/gold.png">
								</c:if>
								<c:if test="${100<=user_score && user_score<250}">
									<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/icon/platinum.png">
								</c:if>
								<c:if test="${250<=user_score}">
									<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/icon/diamond.png">
								</c:if>
								<br><br><br>
								<span class="hn" style="margin: auto;">${user_nickname}님<c:if test="${!empty user_email && user_auth == 0}">(관리자)</c:if>
								<c:if test="${!empty user_email && user_auth == 1}">(스태프)</c:if> 반가워요!</span>
								<!-- <br>하단메뉴 -->
							</a>
							<ul class="cart-dropdown" role="menu">
								<li>
									<!-- Cart items for shopping list -->
									<div class="cart-item">
										<!-- Image -->
										<img class="img-responsive img-rounded" src="${pageContext.request.contextPath}/resources/img/icon/loupe.png"/>
										<!-- Title for purchase item -->
										<span class="cart-title">
										<a href="${pageContext.request.contextPath}/member/detail.do">회원님의 	상세정보</a>
										</span>
										<!-- Cart item price -->
										<div class="clearfix"></div>
									</div>
								</li>
								<li>
									<!-- Cart items for shopping list -->
									<div class="cart-item">
										<!-- Image -->
										<img class="img-responsive img-rounded" src="${pageContext.request.contextPath}/resources/img/icon/key.png"/>
										<!-- Title for purchase item -->
										<span class="cart-title">
										<a href="${pageContext.request.contextPath}/member/changePassword.do">비밀번호 변경</a>
										</span>
										<!-- Cart item price -->
										<div class="clearfix"></div>
									</div>
								</li>
								<!-- Cart items for shopping list -->
								<li class="cart-item">
									<a class="btn btn-danger" data-toggle="modal" href="${pageContext.request.contextPath}/member/logout.do">로그아웃 </a>
								</li>
							</ul>							
							<div class="clearfix"></div>
						</div>
						</c:if>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-1 col-sm-1"></div>
		</div>
		<!-- / .container -->
	</div>
</div>

<!-- Header End -->
<!-- 상단 네비게이션 끝 -->
<!-- 상단 라인 시작 -->
<div class="custom-header-line"></div>
<!-- 상단 라인 끝 -->