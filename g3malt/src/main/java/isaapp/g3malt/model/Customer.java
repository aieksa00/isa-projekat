package isaapp.g3malt.model;

import java.util.List;

public class Customer extends User{

	private int loyaltyPoints;
	private LoyaltyType loyaltyType;
	private int penalty;
	private List<Appointment> appointmentHistory;
	
	public Customer(String id, String name, String surname, String address, String city, String country,
			String phoneNumber, String jmbg, GenderType gender, String profession, String workplace, UserType userType,
			int loyaltyPoints, LoyaltyType loyaltyType, int penalty, List<Appointment> appointmentHistory) {
		super(id, name, surname, address, city, country, phoneNumber, jmbg, gender, profession, workplace, userType);
		this.loyaltyPoints = loyaltyPoints;
		this.loyaltyType = loyaltyType;
		this.penalty = penalty;
		this.appointmentHistory = appointmentHistory;
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