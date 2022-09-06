<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!-- Import java classes -->
<%@ page import="java.util.List" 
	import="beans.Post" %>
		 
<!-- Initialize Data -->
<%List<Post> posts = (List<Post>)request.getAttribute("posts"); %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<title>Home - Rental Rater</title>
	</head>
	<body>
	
		<!-- Navbar -->
		<jsp:include page="navbar-logged-in.jsp">  
			<jsp:param name="active" value="login" />  
		</jsp:include>  
		
		<!-- Your Code Here -->
		
		
		<% 
		
		
		if (posts.size() > 0) {
			for (int postIndex = 0; postIndex < posts.size(); postIndex++) {
			Post post = posts.get(postIndex);
			if (post.getVisibility() != "Hidden" || post.getVisibility() != "Invisibile"){
			
			%>
				<jsp:include page="post.jsp">  
					<jsp:param name="id" value='<%= postIndex %>' /> 
					<jsp:param name="Name"  value='<% %>'/>
				</jsp:include>  
			<% }
		}}
		else { %>
			<h4>No active Ratings. <a href="account">Create one</a> to get the Ratings started!</h4>
		<% } %>
		
			</body>
</html>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</body>
</html>

