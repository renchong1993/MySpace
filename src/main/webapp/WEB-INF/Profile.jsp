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
</div>



<div class="userInfo">
	<h1>${user.name }</h1>
	<a href="/logout">LogOut</a>
	<img alt="User image" src="${user.userInfo.image_url }">
	<a href="editBlurb">Edit User Status</a>
	
	
	
	<h4>${user.userInfo.gender }</h4>
	<h4>${user.userInfo.location }</h4>
	<h4>${user.userInfo.age }</h4>
	<h4>${user.blurb.quote }</h4>
</div>




<div class="userBlurb">
	<h3>${user.name }'s blurbs</h3>
	
	<h4>About me</h4>		
	<h5>${user.blurb.about }</h5>
	
	<h4>Who I'd like to meet</h4>
	<h5>${user.blurb.meet }</h5>
</div>




<div class="userFriends">
	<h4>${user.name }'s Friend Space</h4>
	<h5>${user.name } has ${user.friend.size() } friends</h5>
	
	<c:forEach items="${top8 }" var="topFrnd">
	<h5>${topFrnd.oneUser.name }</h5>
	<img alt="top8 image" src="${topFrnd.oneUser.image_url }">
	</c:forEach>
	
	<a href="/${user.id}/allfriends">View All ${user.name } Friends</a>
</div>




<div class="create_comment">
<!-- Need a logic to hide the input box if the user is looking at his own page -->
	<h3>Leave a comment</h3>
	<form:form method="POST" action="/main/${user.id}/postComment" modelAttribute="comment">
		<form:label path="content">Comment: </form:label>
		<form:errors path="content"/>
		<form:textarea path="content" placeholder="Enter your comment"/>
		
		
		<form:input type="hidden" value="${user.id}" path="receiver"/>
		<form:input type="hidden" value="${creator.id}" path="creator"/>
		<button>Submit</button>
	</form:form>

</div>




<div class="commentList">
<!-- Need a logic to hide the input box if the user is looking at his own page -->
	<h3>Comments</h3>
	<c:forEach items="${user.commentReceived }" var="comment">
		<img alt="user image" src="${comment.creator.image_url }">
		<h4><a href="/main/${comment.creator.id}">${comment.creator.name }</a></h4>
		<h5>${comment.updatedAt }</h5>
		<h5>${comment.content }</h5>
	</c:forEach>
</div>



<div class="sendFriendRequest">
<!-- if the current page owner is not in the login user's friend list, show the "add friend" btn -->
	<form:form method="POST" action="/${user.id}/sendfriendrequest" modelAttribute="friend">
		<form:input type="hidden" value="${user.id}" path="oneUser"/>
		<form:input type="hidden" value="${sender.id}" path="owner"/>
		<button>Add Friend</button>
	</form:form>
</div>



<div class="footer">
</div>

</body> 
</html>
