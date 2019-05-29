<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>

<script type="text/javascript">
	window.manu = '${title}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable theme -->
<%-- <link href="${css}/bootstarp-readable-theme.css" rel="stylesheet"> --%>

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div calss="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>


		<div calss="content">
			<!-- Page Content -->
			<!-- Loading the home content -->

			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- Load only when user clicks about-->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Load only when user clicks contact-->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- /.container -->

		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>


		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js" type="text/javascript"></script>
		<script src="${js}/bootstrap.bundle.min.js" type="text/javascript"></script>

		<!-- self coded JavaScript -->
		<script src="${js}/myapp.js" type="text/javascript"></script>

	</div>

</body>

</html>

