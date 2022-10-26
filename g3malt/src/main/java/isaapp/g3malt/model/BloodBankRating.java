package isaapp.g3malt.model;

public class BloodBankRating {

	private String bloodBankID;
	private String userID;
	private int rating;

	public BloodBankRating(String bloodBankID, String userID, int rating) {
		super();
		this.bloodBankID = bloodBankID;
		this.userID = userID;
		this.rating = rating;
	}

	public String getBloodBankID() {
		return bloodBankID;
	}

	public void setBloodBankID(String bloodBankID) {
		this.bloodBankID = bloodBankID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}