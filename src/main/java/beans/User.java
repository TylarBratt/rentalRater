package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {

	public final long id;
	public final String userName;
	public final String password;
	public String firstname;
	public String lastname;
	public Date dob;
	public int status;
	public int rating;
	


	public User(ResultSet data) throws SQLException {
		this.id = data.getLong("id");
		this.userName = data.getString("username");
		this.password = data.getString("password");
		
	}

	public User(long id, String username2, String password2, String firstname, String lastname, Date dob, int status) {
		this.id = id;
		this.userName = username2;
		this.password = password2;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.status = status;
	}

	public long getid() {
		return id;
	}
	
	public String getMaskedUserName() {
		char first = userName.charAt(0);
		char last = userName.charAt(userName.length()-1);
		return first + "******" + last;
	}
	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
