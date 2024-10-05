<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<section>
<%@ include file="navbar.jsp" %>
		<div class="container mt-5">
			<div class="row">
				<div class="col-md-6 offset-md-3">
					<div class="card">
						<div class="card-header text-center">
							<h3>Update Accountant</h3>
							<c:if test="${not empty mess}">
								<p class="text-success text-center">${mess}</p>
								<c:remove var="mess"/>
							</c:if>
							<c:if test="${not empty error}">
								<p class="text-danger text-center">${error}</p>
								<c:remove var="error"/>
							</c:if>
							<c:if test="${not empty erroremailexists}">
								<p class="text-danger text-center">${erroremailexists}</p>
								<c:remove var="erroremailexists"/>
							</c:if>
						</div>
						<div class="card-body">
							<form action="<%=request.getContextPath()%>/updateAccountant" method="post">
							<input type="hidden" name="id" value="${update.id}">
								<div class="mb-3">
									<label>Enter Name</label> <input type="text"
										class="form-control" name="name" value="${update.name}">
								</div>
								<div class="mb-3">
									<label>Enter Email</label> <input type="email"
										class="form-control" name="email" value="${update.email}">
								</div>
								<div class="mb-3">
									<label>Enter Password</label> <input type="password"
										class="form-control" name="password" readonly="readonly" value="${update.password}">
								</div>
								<div class="mb-3">
									<label>Mobile Number</label> <input type="tel"
										class="form-control" name="mobileNumber" value="${update.mobileNumber}">
								</div>
								<div class="mb-3">
									<label>Enter City</label> <input type="text"
										class="form-control" name="city" value="${update.city}">
								</div>
								<button class="btn btn-primary col-md-12">Create Account</button>
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