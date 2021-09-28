<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
</head>
<body>
	<div class="float float-center">
		<h3>Message</h3>
		<form:form action="/main/{id}/message" method="post" modelAttribute="message">
			<div class="form-group">
				<form:label path="name">${user.name}</form:label>
				<form:errors path="title"></form:errors>
				<form:input class="form-control" path="title">${user.name}</form:input>
			</div>
			 <div class="form-group">
			    <form:label path="content">Message</form:label>
				<form:errors path="content"/>
				<form:input class="form-control" path="content" />
			</div>
			<button>Send</button>
		</form:form>
	</div>

</body>
</html>