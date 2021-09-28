<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>All Request List</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<!-- BootStrap Tutorial link: https://www.w3schools.com/bootstrap/default.asp -->

<body>

<div class="AllRequest">

<h3>${receiver.name }'s Friend Request List</h3>
<table>
	<tr>
		<th>Picture</th>
		<th>Name</th>
		<th>Action</th>
	</tr>

	<tbody>
		<c:forEach items="${allReq }" var="req">
		<tr>
			<td><img alt="friend image" src="${req.owner.picture.image_url }"></td>
			<td><a href="/main/${req.owner.id}">${req.owner.name }</a></td>
			<td><a href="/">Accept</a> | <a href="/">Ignore</a></td>
		</tr>
		</c:forEach>
	</tbody>
	
	
	<tr>
		<th>Picture</th>
		<th>Name</th>
		<th>Action</th>
	</tr>
	<tbody>
		<c:forEach items="${user.friend }" var="req">
		<tr>
			<td><img alt="friend image" src="${req.owner.picture.image_url }"></td>
			<td><a href="/main/${req.owner.id}">${req.owner.name }</a></td>
			<td><a href="/">Accept</a> | <a href="/">Ignore</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>



</body>
</html>