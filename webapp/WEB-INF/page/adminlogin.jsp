<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="navbar.jsp" %>
	<section>
		<div class="container mt-5">
			<div class="row">
				<div class="col-md-6 offset-md-3">
					<div class="card">
						<div class="card-header text-center">
							<h3>Admin Login Page</h3>
							<c:if test="${not empty error}">
								<h5 class="text-danger text-center">${error}</h5>
								<c:remove var="error" />
							</c:if>
							<c:if test="${not empty msg}">
								<h5 class="text-success text-center">${msg}</h5>
								<c:remove var="msg" />
							</c:if>
						</div>

					
					
					<div class="card-body">
						<form action="<%=request.getContextPath()%>/adminLogin" method="post">
							<div class="mb-3">
								<label>Enter Email</label> <input type="email"
									class="form-control" name="email">
							</div>
							<div class="mb-3">
								<label>Enter Password</label> <input type="password"
									class="form-control" name="password">
							</div>
							<button class="btn btn-primary col-md-12">Sign In</button>
						</form>
					</div>
					
				</div>
			</div>
		</div>
		</div>
	</section>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>