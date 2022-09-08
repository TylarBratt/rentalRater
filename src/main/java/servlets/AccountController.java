package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;


@WebServlet("/AccountController")

public class AccountController extends JSPController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountController() {
		super("account.jsp", true, true);
	}

	@Override
	
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// If user is not logged in, redirect to login screen.
		boolean isLoggedIn = req.getSession().getAttribute("user") != null;
		if (isLoginRequired && !isLoggedIn) 
			resp.sendRedirect(req.getContextPath() + "/login");
		else {
			String postAction = req.getParameter("action");
        	System.out.println("Post action: "+ postAction);
        	if (postAction == null)
        		throw new RuntimeException("Post request recieved with no action parameter specified.");
        	else if (postAction.equals("Submit")) {
        		Long userID = (Long) req.getSession().getAttribute("user");
        		String username = req.getParameter("username");
        		String firstname = req.getParameter("firstname");
        		String lastname = req.getParameter("lastname");
        		String date = req.getParameter("dob");
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        		Date dob = null;
				try {
					dob = format.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		String owner = req.getParameter("owner");
        	
        	
        		try {
					database.editUser(userID, username, firstname, lastname, dob, owner);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
			//Attach database attribute to the request.
			initializeData(req);
			
			//Forward the request to the associated JSP file.
			doGet(req,resp);
		}}

	

	public void initializeData(HttpServletRequest req) {
		Long userID = (Long) req.getSession().getAttribute("user");
		User user = null;
		try {
			user = database.getUser(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		req.setAttribute("User", user);
	}
		
			
			
		
	}

	
	
	
