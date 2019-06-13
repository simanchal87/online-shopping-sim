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
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">


<!-- Bootstrap core CSS bootstrap3_3.css-->
<%-- <link href="${css}/bootstrap3_3.css" rel="stylesheet"> --%>


<!-- Bootstrap readable theme -->
<%-- <link href="${css}/bootstarp-readable-theme.css" rel="stylesheet"> --%>

<!-- Bootstrap DataTable CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Online
						Shopping</a>
				</div>
			</div>
		</nav>



		<div class="content">

			<div class="container">
			
<!-- 			This will display if the credential are wrong. -->

			<c:if test="${not empty message}">
			
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="alert alert-danger">
						${message}
						</div>
					</div>
				</div>
			</c:if>
			
			<!-- 			This will display only when user logged out. -->

			<c:if test="${not empty logout}">
			
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="alert alert-success">
						${logout}
						</div>
					</div>
				</div>
			</c:if>
			
			
			
				<div class="row">

					<div class="col-md-6 col-md-offset-3">

						<div class="card card-primary">
							<div class="card card-header">
								<h4>Login</h4>
							</div>

							<div class="card-body">

								<form action="${contextRoot}/login" class="card-body"
									method="POST" id="loginForm">


									<div class="card-text">
										<label for="username" class="control-label col-md-4">E-Mail:
										</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="card-control" placeholder="abc@xyz.com" />
										</div>
									</div>
									<div class="card-text">
										<label for="password" class="control-label col-md-4">Password:
										</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="card-control" />
										</div>
									</div>
									<div class="card-text">
										
										<div class="col-md-8">
											<input type="submit" value="Login" class="btn btn-primary" />
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</div>
									</div>

									<div class="card-footer">

										<div class="col-md-8">
											New User - <a href="${contextRoot}/register">Register Here</a>
											

										</div>
									</div>

								</form>
							</div>

						</div>
					</div>

				</div>

			</div>


		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>


		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>

		<!-- J Query validate script -->
		<script src="${js}/jquery.validate.js"></script>


		<!-- self coded JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>




