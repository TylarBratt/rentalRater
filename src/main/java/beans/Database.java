package beans;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

public class Database {
	public Connection connection = null;
	public Database(ServletContext servletContext) {

		// Get connection to database.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("MySQL JDBC Driver Registered!");

			String databaseSchema = servletContext.getInitParameter("databaseSchema");
			String databaseUser = servletContext.getInitParameter("databaseUser");
			String databasePassword = servletContext.getInitParameter("databasePassword");
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + databaseSchema/* +"?allowMultiQueries=true" */,
					databaseUser, databasePassword);

		} catch (ClassNotFoundException e) {
			System.out.print("MySQL JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("Connection failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Connection made to DB!");
		}
	}

	public void shutdown() {
		System.out.println("Shutting down MySQL connection...");

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Logs in a user if the provided credentials match an entry in the user
	 * database.
	 */
	public User login(String userName, String password) {
		ResultSet results = null;
		try {
			String query = "SELECT * FROM user WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			results = statement.executeQuery();

			// If a user was returned as a result, then login was successful.
			// Store the userID to the current session to indicate the user has logged in.
			if (results.next())
				return new User(results);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}

	/*
	 * Creates a new user account in the database and logs in with that user
	 * account.
	 * We must do this via a stored procedure because we need to create AND return
	 * an entry at the same time.
	 */
	public User createAccount(String userName, String password) {
		try {
			PreparedStatement statement = connection.prepareStatement("CALL insert_user(?,?,?,?);");
			statement.setString(1, userName);
			statement.setString(2, password);
			statement.setString(4, User.Role.USER.name());
			ResultSet results = statement.executeQuery();
			return login(userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Post> getPosts(long userID) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"SELECT ratingid, renterid, ownerid, date, post.rating, 'description', visibility, "
				+ "a.id as renterid, a.firstName as renterfirstName, a.lastname as renterlastName, "
				+ "b.id as ownerid, b.firstName as ownerfirstname, b.lastName as ownerlastname "
				+ "FROM post LEFT JOIN oop_capstone.user a ON post.renterid = a.id LEFT JOIN oop_capstone.user b ON post.ownerid = b.id WHERE renterid = 1 OR ownerid = 1"
				
				//"SELECT * FROM post LEFT JOIN user ON post.renterid = user.id LEFT JOIN user on post.ownerid = user.id WHERE renterid = " + userID + " OR ownerid = " + userID
				 );
		ResultSet result = statement.executeQuery();
		List<Post> posts = new ArrayList<>();
		while(result.next()) {
			Post post = new Post(result);
			posts.add(post);
		}
		
		return posts;

		
	}

	public List<String> getComments(long getid) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE ratingID = " + getid);
		ResultSet result = statement.executeQuery();
		List<String> comments = new ArrayList<>();
		while(result.next()) {
			if(result.getString("visibility") != "hidden" || result.getString("visibility") != "invisible")
			comments.add(result.getString("comment"));
		}
		return comments;
	}

	public void addComment(int userid, String postid, String comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM comments");
		ResultSet result1 = statement.executeQuery();
		result1.next();
		int maxID = result1.getInt(1);
		maxID++;
		result1.close();
		statement = connection.prepareStatement("INSERT INTO comments VALUES(" + maxID + ", " + postid + ", " + userid + ", " + comment + ", " + "visible)");
		statement.execute();
	}
	
	
	public int getHighestID() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM oop_capstone.user");
		ResultSet rs = statement.executeQuery();
		rs.next();
		int highest = rs.getInt(1);
		rs.close();
		return highest;
	}

	public void createAccount(User user) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(user.dob);
		PreparedStatement statement = connection.prepareStatement("INSERT INTO oop_capstone.user (id, username, password, firstname, lastname, dateofbirth, status) VALUES ( " + user.id + ", '" + user.userName + "', '" + user.password + "', '" 
				+ user.firstname + "', '" + user.lastname + "', '" + date + "', '" + user.status + "')");
		statement.execute();
		
	}
	
	
	
	
	
	
	
}