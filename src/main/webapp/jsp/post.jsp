<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!-- Import java classes -->
<%@ page import="java.util.List" 
	import="beans.Post" 
	import="beans.User" %>
		 
<!-- Initialize Data -->
<%List<Post> posts = (List<Post>)request.getAttribute("posts"); 

Long userID = (Long) request.getAttribute("userID");


Long mid = Long.valueOf(request.getParameter("id"));
int index = mid.intValue();

Post post = posts.get(index);

List<String> comments = post.getComments();
int total = comments.size() / 2;

User user = (User)request.getAttribute("User");

request.setAttribute("post", post);
request.setAttribute("comments", comments);
request.setAttribute("user", user);

Long userid = user.getId();

%>


<div class="post">
<div id="intro">
		<h3>Property Owner: <%= post.getOwnerName() %></h3>
		<h3>Property Renter: <%= post.getRenterName( )%></h3>
	
	</div>
<div id="intro_image">
	<form action="post-details" method="get">
	<input class="post-image" type="image" id="image" src="images/details.png"/>
	<input type="hidden" name="aid" value=<%= post.getid() %> />
	<input type="hidden" name="index" value=<%=index %> />
	<input type="hidden" name="uid" value=<%=userid %> />
	</form>
	</div>
	
	
	<!-- Auction details -->

	<div id="body">
	<form method=post>
	
		<h4>Date: </h4>
		<h5><%= post.getDate() %></h5>
	
		<h4>Rating: </h4>
		<h5><%= post.getRating() %></h5>
	
		<h4>Description: </h4>
		<h5><%= post.getDesc() %></h5>
		
		
		<h4>Comments: </h4>
		<h5><%= total %></h5>
		
		
		
		<input type="text" placeholder="Enter your Comment" name="newComment"/>
		<input type=hidden name="postID" value=<%= post.id %>>
		<input type=hidden name="userID" value=<%= userID %>>
		<input type = "submit" name="action" value="Submit"/>
	
	</form>
	
</div>
</div>