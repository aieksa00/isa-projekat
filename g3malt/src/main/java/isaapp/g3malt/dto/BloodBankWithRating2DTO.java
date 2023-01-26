package isaapp.g3malt.dto;

public class BloodBankWithRating2DTO {
	public int id;
	public String name;
	public String description;
	public String street;
	public String city;
	public String country;
	public String workingHours;
	public String rating;
	public Boolean hasPredefinedAppointment;
	
	
	public Boolean getHasPredefinedAppointment() {
		return hasPredefinedAppointment;
	}
	public void setHasPredefinedAppointment(Boolean hasPredefinedAppointment) {
		this.hasPredefinedAppointment = hasPredefinedAppointment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
}
