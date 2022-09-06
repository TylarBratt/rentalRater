package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LocalURLBuilder;
import beans.User;

@WebServlet("/signup")


public class SignUpController extends JSPController{

	private static final long serialVersionUID = 1L;

	public SignUpController() {
		super("signup.jsp", true, false);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = 0;
		try {
			id = database.getHighestID() + 1;
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		
		String test = req.getParameter("dob");	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = null;
		try {
			dob = format.parse(test);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int status;
		
		if(req.getParameter("status") == "owner") {
			status = 1;
		}else {
			status = 2;
		}
		User user = new User(id, username, password, firstname, lastname, dob, status);
		Boolean worked = false;
		try {
			database.createAccount(user);
			worked = true;
		} catch (SQLException e) {
			worked = false;
			e.printStackTrace();
		}
		
		if (worked == true) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user.id);
			resp.sendRedirect(new LocalURLBuilder("home", req).toString());
		}else {
			req.setAttribute("fail", "User taken, please try again.");
			doGet(req,resp);
		}
		
		
		
		
	}
	
	
	
	
}