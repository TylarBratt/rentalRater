package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.LocalURLBuilder;
import beans.User;
import beans.navbar.LoggedInNavbar;
import beans.navbar.LoggedOutNavbar;
import beans.navbar.Navbar;

@WebServlet("/own-user")

public class OwnUserController extends JSPController {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OwnUserController() {
		super("own-user.jsp", true, true);
	}
	

		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// If user is not logged in, redirect to login screen.
			boolean isLoggedIn = req.getSession().getAttribute("user") != null;
			if (isLoginRequired && !isLoggedIn) 
				resp.sendRedirect(req.getContextPath() + "/login");
			else {
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
			
		
		
		
		
		}
		}