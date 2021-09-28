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
<title>User Info</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<!-- BootStrap Tutorial link: https://www.w3schools.com/bootstrap/default.asp -->
<body>

<div class="header">
<a href="/">Go Back</a>
<h2>${user.name }'s Friend List</h2>
</div>



<div class="top8_list">
<h3>${user.name }'s Top 8 List</h3>
<table>
	<tr>
		<th>Head</th>
		<th>Name</th>
		<th>Action</th>
	</tr>

	<tbody>
		<c:forEach items="${top8 }" var="top8">
		<tr>
			<td><img alt="friend image" src="${top8.image_url }"></td>
			<td>${top.name }</td>
			<td><a href="/">Remove from Top8</a> | <a href="/">Remove From Friend List</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>



<div class="allFriends">
<h3>${user.name }'s All Friend List</h3>
<table>
	<tr>
		<th>Head</th>
		<th>Name</th>
		<th>Action</th>
	</tr>

	<tbody>
		<c:forEach items="${allFrnd }" var="friend">
		<tr>
			<td><img alt="friend image" src="${friend.image_url }"></td>
			<td>${friend.name }</td>
			<td><a href="/">Add To Top8</a> | <a href="/">Remove From Friend List</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>




<div class="footer">
</div>


</body>
</html>