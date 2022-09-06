<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!-- Import java classes -->
<%@ page import="java.util.List" 
	import="beans.Post" %>
		 
<!-- Initialize Data -->
<%List<Post> posts = (List<Post>)request.getAttribute("posts"); 

Long userID = (Long) request.getAttribute("userID");


Long mid = Long.valueOf(request.getParameter("id"));
int index = mid.intValue();

Post post = posts.get(index);
List<String> comments;
int total = 0;

if (post.getComments() != null){
comments = post.getComments();
total = comments.size();
}
%>


<div class="post">
		<h3>Property Owner: </h3>
	<h3 class="post-owner"><%= post.getOwnerID() %></h3>
		<h3>Property Renter: </h3>
	<h3 class="post-renter"><%= post.getRenterID() %></h3>
	
	<form action="post-details" method="get">
	<input class="post-image" type="image" id="image" src='images?src='/>
	<input type="hidden" name="aid" value=<%= post.getid() %> />
	</form>
	
	<!-- Auction details -->
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