package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.LocalURLBuilder;
import beans.Post;
import beans.User;
import beans.navbar.LoggedInNavbar;
import beans.navbar.LoggedOutNavbar;
import beans.navbar.Navbar;

@WebServlet("/home")

public class HomeController extends JSPController {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super("home.jsp", true, true);
	}
	

		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// If user is not logged in, redirect to login screen.
			boolean isLoggedIn = req.getSession().getAttribute("user") != null;
			if (isLoginRequired && !isLoggedIn) 
				resp.sendRedirect(req.getContextPath() + "/login");
			else {
				String postAction = req.getParameter("action");
	        	System.out.println("Post action: "+postAction);
	        	if (postAction == null)
	        		throw new RuntimeException("Post request recieved with no action parameter specified.");
	        	else if (postAction.equals("Submit")) {
	        		
	        		String comment = req.getParameter("newComment");
	        		String ratingid = req.getParameter("postID");
	        		String userid = req.getParameter("userID");
	        		int userid2 = Integer.valueOf(userid);
	        		database.addComment(userid2, ratingid, comment);
	        	}
	        		
				
				
				
				
				//Attach database attribute to the request.
				initializeData(req);
				
				//Forward the request to the associated JSP file.
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/"+jspPath);
		        dispatcher.forward(req, resp);
			}

		}
		
		/*
		 * This is where you initialize any data that you want passed into the JSP. 
		 * Data must be passed in as an attribute by calling req.setAttribute().
		 */
		public void initializeData(HttpServletRequest req) {
			Long userID = (Long) req.getSession().getAttribute("user");
			List<Post> posts = new ArrayList<>();
			List<Integer> commments = new ArrayList<>();
			try {
				posts = database.getPosts(userID);
				posts.forEach((n) -> {
					try {
						n.setComments(database.getComments(n.getid()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("userID", userID);
			req.setAttribute("posts", posts);
		
		
		
		}
		}