<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List"
    import="beans.Database"
    import="beans.User"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<title>Sign Up - Rental Rater</title>
		</head>
	<body>
	
		<!-- Navbar -->
		<jsp:include page="navbar-logged-out.jsp">  
			<jsp:param name="active" value="login" />  
		</jsp:include>  
		
		<h1>Welcome to Rental Rater</h1>
		<h3>Where you and your landlord can keep a track record.</h3>
		
		<form method="post">
		<label>Username:</label> <br><input type="text" name="username" id="title" size=20 maxlength=60 required><br>
		<label>Password:</label> <br><input type="password" name="password" id="title" size=20 maxlength=60 required><br>
		<label>First Name:</label> <br><input type="text" name="firstname" id="title" size=20 maxlength=60 required><br>
		<label>Last Name:</label> <br><input type="text" name="lastname" id="title" size=20 maxlength=60 required><br>
		<label>Date of Birth:</label> <br><input type="text" name="dob" id="title" size=20 maxlength=60 required><br>
		<label>Owner:</label> <br><input type="text" name="owner" id="title" size=20 maxlength=60 required><br>
		
		
		
		
		<input type="submit" value="Create Account" id="signup">
		</form>
	</body>
	</html>