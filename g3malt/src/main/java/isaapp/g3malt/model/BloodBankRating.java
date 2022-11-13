package isaapp.g3malt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bloodBankRatings")
public class BloodBankRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bloodBankRatingId;
	@Column(name="bloodBankId", unique=false, nullable=true)
	private String bloodBankID;
	@Column(name="userId", unique=false, nullable=true)
	private String userID;
	@Column(name="rating", unique=false, nullable=true)
	private int rating;

	public BloodBankRating(Integer bloodBankRatingId, String bloodBankID, String userID, int rating) {
		super();
		this.bloodBankID = bloodBankID;
		this.userID = userID;
		this.rating = rating;
		this.bloodBankRatingId = bloodBankRatingId;
	}

	public BloodBankRating() {
		super();
	}

	public Integer getBloodBankRatingId() {
		return bloodBankRatingId;
	}

	public void setBloodBankRatingId(Integer bloodBankRatingId) {
		this.bloodBankRatingId = bloodBankRatingId;
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