package isaapp.g3malt.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@DiscriminatorValue("2")
public class Customer extends User{
	
	@Column(name="loyaltyPoints", unique=false, nullable=true)
	private int loyaltyPoints;
	@Column(name="loyaltyType", unique=false, nullable=true)
	private LoyaltyType loyaltyType;
	@Column(name="penalty", unique=false, nullable=true)
	private int penalty;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointmentHistory;
	
	public Customer(Integer id, String name, String surname, String address, String city, String country,
			String phoneNumber, String jmbg, GenderType gender, String profession, String workplace, UserType userType,
			int loyaltyPoints, LoyaltyType loyaltyType, int penalty, Set<Appointment> appointmentHistory) {
		super(id, name, surname, address, city, country, phoneNumber, jmbg, gender, profession, workplace, new ArrayList<UserType>(){{add(userType);}}, workplace);
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

	public Set<Appointment> getAppointmentHistory() {
		return appointmentHistory;
	}

	public void setAppointmentHistory(Set<Appointment> appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}
}