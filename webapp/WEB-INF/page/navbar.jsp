<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Billing application</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=request.getContextPath()%>/login">Home</a></li>
						<% if(session.getAttribute("adminloginsession")!=null) {%>
							<li class="nav-item"><a class="nav-link active"
									aria-current="page" href="<%=request.getContextPath()%>/displayAcc">Display Accountant</a></li>
						<%}%>
						

				</ul>
				<% if(session.getAttribute("adminloginsession")!=null){%>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#"><i
							class="fa-solid fa-user"></i>${adminloginsession.name}</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout"><i
							class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
				</ul>
				<% } else{%>
				<% }%>

			</div>
		</div>
	</nav>
</body>
</html>