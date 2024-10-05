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
<div class="container">
		<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Mobile Number</th>
      <th scope="col">City</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <c:forEach var="list" items="${user}">
  
  <tbody>
    <tr>
      <th scope="row">${list.id}</th>
      <td>${list.name}</td>
      <td>${list.email}</td>
      <td>${list.mobileNumber}</td>
      <td>${list.city}</td>
      <td><a href="<%=request.getContextPath()%>/updateAccountant/${list.id}">Update</a> <a href="<%=request.getContextPath()%>/deleteAcc/${list.id}">Delete</a></td>
    </tr>
  </tbody>
  </c:forEach>
</table>
</div>
	</section>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>