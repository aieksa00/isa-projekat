package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bloodBankRatings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BloodBankRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bloodBankRatingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bloodBank_id")
	private BloodBank bloodBank;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")	
	private Customer customer;
	
	@Column(name="rating", unique=false, nullable=true)
	private int rating;

	public BloodBankRating() {
		super();
	}

	public Integer getBloodBankRatingId() {
		return bloodBankRatingId;
	}

	public void setBloodBankRatingId(Integer bloodBankRatingId) {
		this.bloodBankRatingId = bloodBankRatingId;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer customer) {
		this.customer = customer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}