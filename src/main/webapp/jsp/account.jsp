<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!-- Import java classes -->
<%@ page import="java.util.List" 
	import="beans.Post" 
	import="beans.User"
	import="beans.Database"%>
		 
<!-- Initialize Data -->
<% 

User user = (User)request.getAttribute("User");
String username = user.getUserName();
%>
<!-- HTML starts here -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<title>Account - Rental Rater</title>
	</head>
	<body>
	
		<!-- Navbar -->
		<jsp:include page="navbar-logged-in.jsp">  
			<jsp:param name="active" value="login" />  
		</jsp:include>  
		
		<!-- Your Code Here -->
		<h2>Account Details!</h2>
		
		<form method="post" >
		<label>Username:  <%=username %></label> <br><input type="text" name="username" id="title" size=20 maxlength=60><br>
		<label>First Name: <%=user.getFirstname() %></label> <br><input type="text" name="firstname" id="title" size=20 maxlength=60><br>
		<label>Last Name: <%=user.getLastname() %></label> <br><input type="text" name="lastname" id="title" size=20 maxlength=60><br>
		<label>Date of Birth: <%=user.getDob() %></label> <br><input type="text" name="dob" id="title" size=20 maxlength=60><br>
		<label>Owner: <%=user.getStatus() %></label> <br><input type="text" name="owner" id="title" size=20 maxlength=60><br>
		<input type="hidden" name="action" value="Submit">
		<input type="submit" value="Edit Details" id="edit">
		</form>
		
		
		</body>
		</html>
		