package isaapp.g3malt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer extends User{
	
	@Column(name="loyatyPoints", unique=false, nullable=true)
	private int loyaltyPoints;
	@Column(name="loyaltyType", unique=false, nullable=true)
	private LoyaltyType loyaltyType;
	@Column(name="penalty", unique=false, nullable=true)
	private int penalty;
	private List<Appointment> appointmentHistory;
	
	public Customer(Integer id, String name, String surname, String address, String city, String country,
			String phoneNumber, String jmbg, GenderType gender, String profession, String workplace, UserType userType,
			int loyaltyPoints, LoyaltyType loyaltyType, int penalty, List<Appointment> appointmentHistory) {
		super(id, name, surname, address, city, country, phoneNumber, jmbg, gender, profession, workplace, userType);
		this.loyaltyPoints = loyaltyPoints;
		this.loyaltyType = loyaltyType;
		this.penalty = penalty;
		this.appointmentHistory = appointmentHistory;
	}

	public Customer() {
		super();
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public LoyaltyType getLoyaltyType() {
		return loyaltyType;
	}

	public void setLoyaltyType(LoyaltyType loyaltyType) {
		this.loyaltyType = loyaltyType;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public List<Appointment> getAppointmentHistory() {
		return appointmentHistory;
	}

	public void setAppointmentHistory(List<Appointment> appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}
}