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
}
