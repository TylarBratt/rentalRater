package beans;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class Post {
	

	public final long id;
	public final long renterID;
	public String renterName;
	public long ownerID;
	public String ownerName;
	public String date;
	public long rating;
	

	public String desc;
	public String visibility;
	public List<String> comments;
	
	
	public Post(ResultSet data) throws SQLException {
		this.id = data.getLong("ratingid");
		this.renterID = data.getLong("renterID"); 
		this.renterName = data.getString("renterFirstName") + " " + data.getString("renterLastName");
		this.ownerID = data.getLong("ownerid");
		this.ownerName = data.getString("ownerFirstName") +  " " + data.getString("ownerLastName");
		this.date = data.getString("date");
		this.rating = data.getLong("rating");
		this.desc = data.getString("description");
		this.visibility = data.getString("visibility");
	}

	
	public List<String> getComments(){
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public long getid() {
		return id;
	}

	public long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public long getId() {
		return id;
	}

	public long getRenterID() {
		return renterID;
	}
	
	public String getRenterName() {
		return renterName;
	}


	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/*public String getMaskedUserName() {
		char first = userName.charAt(0);
		char last = userName.charAt(userName.length()-1);
		return first + "******" + last;
	}*/
}
