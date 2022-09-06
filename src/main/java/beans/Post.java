package beans;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Post {
	

	public final long id;
	public final long renterID;
	public long ownerID;
	public Date date;
	public long rating;
	public String desc;
	public String visibility;
	public List<String> comments;
	
	
	public Post(ResultSet data) throws SQLException {
		this.id = data.getLong("ratingid");
		this.renterID = data.getLong("renterID");
		this.ownerID = data.getLong("ownerid");
		this.date = data.getDate("date");
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
	
	/*public String getMaskedUserName() {
		char first = userName.charAt(0);
		char last = userName.charAt(userName.length()-1);
		return first + "******" + last;
	}*/
}
