package isaapp.g3malt.dto;

import java.util.Set;

import isaapp.g3malt.model.Appointment;

public class BloodBankDto {
	private Integer bloodBankId;
	private String bloodBankName;
	private String bloodBankStreet;
	private String bloodBankCity;
	private String bloodBankCountry;
	private String bloodBankDescription;
	private double bloodBankRating;
	private Set<Appointment> bloodBankFreeAppointments;
	private Set<StaffDto> bloodBankAdministrators;
	private String bloodBankWorkingHours;
		
	private int bloodBankStorageAPlus;
	private int bloodBankStorageBPlus;
	private int bloodBankStorageABPlus;
	private int bloodBankStorageOPlus;
	private int bloodBankStorageAMinus;
	private int bloodBankStorageBMinus;
	private int bloodBankStorageABMinus;
	private int bloodBankStorageOMinus;
	
	public BloodBankDto() {
		super();
	}

	public BloodBankDto(Integer bloodBankId, String bloodBankName, String bloodBankStreet, String bloodBankCity,
			String bloodBankCountry, String bloodBankDescription, double bloodBankRating,
			Set<Appointment> bloodBankFreeAppointments, Set<StaffDto> bloodBankAdministrators, String bloodBankWorkingHours,
			int bloodBankStorageAPlus, int bloodBankStorageBPlus, int bloodBankStorageABPlus, int bloodBankStorageOPlus,
			int bloodBankStorageAMinus, int bloodBankStorageBMinus, int bloodBankStorageABMinus,
			int bloodBankStorageOMinus) {
		super();
		this.bloodBankId = bloodBankId;
		this.bloodBankName = bloodBankName;
		this.bloodBankStreet = bloodBankStreet;
		this.bloodBankCity = bloodBankCity;
		this.bloodBankCountry = bloodBankCountry;
		this.bloodBankDescription = bloodBankDescription;
		this.bloodBankRating = bloodBankRating;
		this.bloodBankFreeAppointments = bloodBankFreeAppointments;
		this.bloodBankAdministrators = bloodBankAdministrators;
		this.bloodBankWorkingHours = bloodBankWorkingHours;
		this.bloodBankStorageAPlus = bloodBankStorageAPlus;
		this.bloodBankStorageBPlus = bloodBankStorageBPlus;
		this.bloodBankStorageABPlus = bloodBankStorageABPlus;
		this.bloodBankStorageOPlus = bloodBankStorageOPlus;
		this.bloodBankStorageAMinus = bloodBankStorageAMinus;
		this.bloodBankStorageBMinus = bloodBankStorageBMinus;
		this.bloodBankStorageABMinus = bloodBankStorageABMinus;
		this.bloodBankStorageOMinus = bloodBankStorageOMinus;
	}

	public Integer getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public String getBloodBankStreet() {
		return bloodBankStreet;
	}

	public void setBloodBankStreet(String bloodBankStreet) {
		this.bloodBankStreet = bloodBankStreet;
	}

	public String getBloodBankCity() {
		return bloodBankCity;
	}

	public void setBloodBankCity(String bloodBankCity) {
		this.bloodBankCity = bloodBankCity;
	}

	public String getBloodBankCountry() {
		return bloodBankCountry;
	}

	public void setBloodBankCountry(String bloodBankCountry) {
		this.bloodBankCountry = bloodBankCountry;
	}

	public String getBloodBankDescription() {
		return bloodBankDescription;
	}

	public void setBloodBankDescription(String bloodBankDescription) {
		this.bloodBankDescription = bloodBankDescription;
	}

	public double getBloodBankRating() {
		return bloodBankRating;
	}

	public void setBloodBankRating(double bloodBankRating) {
		this.bloodBankRating = bloodBankRating;
	}

	public Set<Appointment> getBloodBankFreeAppointments() {
		return bloodBankFreeAppointments;
	}

	public void setBloodBankFreeAppointments(Set<Appointment> bloodBankFreeAppointments) {
		this.bloodBankFreeAppointments = bloodBankFreeAppointments;
	}

	public Set<StaffDto> getBloodBankAdministrators() {
		return bloodBankAdministrators;
	}

	public void setBloodBankAdministrators(Set<StaffDto> bloodBankAdministrators) {
		this.bloodBankAdministrators = bloodBankAdministrators;
	}

	public String getBloodBankWorkingHours() {
		return bloodBankWorkingHours;
	}

	public void setBloodBankWorkingHours(String bloodBankWorkingHours) {
		this.bloodBankWorkingHours = bloodBankWorkingHours;
	}

	public int getBloodBankStorageAPlus() {
		return bloodBankStorageAPlus;
	}

	public void setBloodBankStorageAPlus(int bloodBankStorageAPlus) {
		this.bloodBankStorageAPlus = bloodBankStorageAPlus;
	}

	public int getBloodBankStorageBPlus() {
		return bloodBankStorageBPlus;
	}

	public void setBloodBankStorageBPlus(int bloodBankStorageBPlus) {
		this.bloodBankStorageBPlus = bloodBankStorageBPlus;
	}

	public int getBloodBankStorageABPlus() {
		return bloodBankStorageABPlus;
	}

	public void setBloodBankStorageABPlus(int bloodBankStorageABPlus) {
		this.bloodBankStorageABPlus = bloodBankStorageABPlus;
	}

	public int getBloodBankStorageOPlus() {
		return bloodBankStorageOPlus;
	}

	public void setBloodBankStorageOPlus(int bloodBankStorageOPlus) {
		this.bloodBankStorageOPlus = bloodBankStorageOPlus;
	}

	public int getBloodBankStorageAMinus() {
		return bloodBankStorageAMinus;
	}

	public void setBloodBankStorageAMinus(int bloodBankStorageAMinus) {
		this.bloodBankStorageAMinus = bloodBankStorageAMinus;
	}

	public int getBloodBankStorageBMinus() {
		return bloodBankStorageBMinus;
	}

	public void setBloodBankStorageBMinus(int bloodBankStorageBMinus) {
		this.bloodBankStorageBMinus = bloodBankStorageBMinus;
	}

	public int getBloodBankStorageABMinus() {
		return bloodBankStorageABMinus;
	}

	public void setBloodBankStorageABMinus(int bloodBankStorageABMinus) {
		this.bloodBankStorageABMinus = bloodBankStorageABMinus;
	}

	public int getBloodBankStorageOMinus() {
		return bloodBankStorageOMinus;
	}

	public void setBloodBankStorageOMinus(int bloodBankStorageOMinus) {
		this.bloodBankStorageOMinus = bloodBankStorageOMinus;
	}
}
