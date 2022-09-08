<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!-- Import java classes -->
<%@ page import="java.util.List" 
	import="beans.Post"
	import="beans.User" %>

		 
<!-- Initialize Data -->
<%

Long userid = (Long) request.getSession().getAttribute("user");
Post post = (Post) request.getAttribute("post");
List<String> comments = (List<String>)request.getAttribute("comments");
int total = comments.size() / 2;


%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<title>Post Details- Rental Rater</title>
		</head>
	<body>
	
		<!-- Navbar -->
		<jsp:include page="navbar-logged-in.jsp">  
			<jsp:param name="active" value="home" />  
		</jsp:include>  
		
	<h3></h3>

	
	<!-- Auction details -->

		<h4>Owner: </h4>
		<h5><%= post.getOwnerName() %></h5>
	
		<h4>Renter: </h4>
		<h5><%= post.getRenterName() %></h5>
	
	
		<h4>Date: </h4>
		<h5><%= post.getDate() %></h5>
	
		<h4>Rating: </h4>
		<h5><%= post.getRating() %></h5>
	
		<h4>Description: </h4>
		<h5><%= post.getDesc() %></h5>
		
		
		<h4>Comments: </h4>
		<h5><%= total %></h5>
		
	<%for (int i = 1; i <= comments.size(); i++){ %>	
		<h3>User: <%=comments.get(i) %></h3>
		<h3>Comment: <%=comments.get(i+1) %></h3>
		
		<%i++; } %>
		
		
		
	<form method=post>
		<input type="text" placeholder="Enter your Comment" name="newComment"/>
		<input type=hidden name="postID" value=<%= post.id %>>
		<input type=hidden name="userID" value=<%= userid %>>
		<input type = "submit" name="action" value="Submit"/>
	
	</form>
		
	</body>
	</html>