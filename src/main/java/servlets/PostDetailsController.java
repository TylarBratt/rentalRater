package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Post;


@WebServlet("/post-details")

public class PostDetailsController extends JSPController {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostDetailsController() {
		super("post-details.jsp", true, true);
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
		String mid = req.getParameter("aid");
		int id = Integer.valueOf(mid);
		
		String test = req.getParameter("uid");
		Long userid = Long.valueOf(test);
		
		
		
		
		
		Post post = null;
		try {
			post = database.getPost(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long postid = post.getid();
				
		req.setAttribute("post", post);
		

		List<String> comments = null;
		try {
			if (database.getComments(postid) != null){
			comments  = database.getComments(postid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("comments", comments);
		
		
		
		
		}
		}