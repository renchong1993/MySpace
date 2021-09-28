<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<p><form:errors path="user.*"/></p>
    
    <form:form method="POST" action="/auth" modelAttribute="user">
    
    	<p>
    		<form:label path="name">Name</form:label>
    		<form:input type="name" path="name"/>
    	</p>
    
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirm">Password Confirmation:</form:label>
            <form:password path="passwordConfirm"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
    
    <hr>
    
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <p>
            <label type="email" for="email">Email</label>
            <input type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        
        <input type="submit" value="Login!"/>
    </form>
    
    <form  action='/edit' method='POST'>
    <input type='hidden' name='hidden' value='email'>
    <input type='email' name='thing_to_edit'>
    <input type='submit' value='edit email'>
    </form>
    
    <form  action='/edit' method='POST'>
    <input type='hidden' name='hidden' value='birthday'>
    <input type='text' name='thing_to_edit'>
    <input type='submit' value='edit birthday'>
    </form>
    
    </body>
</html>