<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Title here -->
<title>Trips Come True</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Styles -->
<!-- Bootstrap CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link
	href="${pageContext.request.contextPath}/resources/css/settings.css"
	rel="stylesheet">
<!-- FlexSlider Css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flexslider.css"
	media="screen" />
<!-- Portfolio CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/prettyPhoto.css"
	rel="stylesheet">
<!-- Font awesome CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom Less -->
<link
	href="${pageContext.request.contextPath}/resources/css/less-style.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">

<!-- Favicon -->
<link rel="shortcut icon" href="#">
<!-- Javascript files -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script> --%>
<!-- Bootstrap JS -->
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.themepunch.revolution.min.js"></script>
<!-- FLEX SLIDER SCRIPTS  -->
<script defer
	src="${pageContext.request.contextPath}/resources/js/jquery.flexslider-min.js"></script>
<!-- Pretty Photo JS -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.prettyPhoto.js"></script>
<!-- Respond JS for IE8 -->
<script
	src="${pageContext.request.contextPath}/resources/js/respond.min.js"></script>
<!-- HTML5 Support for IE -->
<script
	src="${pageContext.request.contextPath}/resources/js/html5shiv.js"></script>
<!-- Custom JS -->
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>

<!-- JS code for this page -->


</head>

<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />

	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>

</body>
</html>