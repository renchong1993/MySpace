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
<title>Edit Blurb</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<!-- BootStrap Tutorial link: https://www.w3schools.com/bootstrap/default.asp -->

<body>

<h1>Edit Blurb</h1>
<p>${message }</p>

<form method="POST" action="/editBlurb/${loguser.id}" enctype="multipart/form-data">

<label for="about">About me: </label>
<input type="text" name="about" value="Hi there! Welcome to my page! "><br>

<input type="file" name="pic"><br>


<label for="meet">I'd like to meet: </label>
<input type="text" name="meet" value="New Friends "><br>

<label for="quote">My quote: </label>
<input type="text" name="quote" value=";-) "><br>


<label for="location">Current location: </label>
<input type="text" name="location" value="World Wide Web "><br>



<button>Create!</button>

</form>

</body>
</html>