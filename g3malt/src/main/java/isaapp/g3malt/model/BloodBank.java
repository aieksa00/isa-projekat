package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="bloodBanks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BloodBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name", unique=false, nullable=true)
	private String name;
	@Column(name="street", unique=false, nullable=true)
	private String street;
	@Column(name="city", unique=false, nullable=true)
	private String city;
	@Column(name="country", unique=false, nullable=true)
	private String country;
	
	@Size(min = 10, max = 200, message="Description should be between 10 and 200 characters")
	@Column(name="description", unique=false, nullable=true)
	private String description;
	@Column(name="rating", unique=false, nullable=true)
	private double rating;
	
	@OneToMany(mappedBy = "bloodBankId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> freeAppointments;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> allStaff;
	
	@Column(name="workingHours", unique=false, nullable=true)
	private String workingHours;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bloodBankStorage_id")
	private BloodBankStorage bloodBankStorage;
	
	@OneToMany(mappedBy = "bloodBank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	//@Column(name="medicalStaff", unique=false, nullable=true)
	private Set<BloodBankRating> bloodBankRatings;

	public BloodBank(Integer id, String name, String street, String city, String country, String description,
			double rating, Set<Appointment> freeAppointments, Set<User> allStaff, String workingHours,
			BloodBankStorage bloodBankStorage, Set<BloodBankRating> bloodBankRatings) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.city = city;
		this.country = country;
		this.description = description;
		this.rating = rating;
		this.freeAppointments = freeAppointments;
		this.allStaff = allStaff;
		this.workingHours = workingHours;
		this.bloodBankStorage = bloodBankStorage;
		this.bloodBankRatings = bloodBankRatings;
	}

	public BloodBank() {
		super();
	}

	public Set<BloodBankRating> getBloodBankRatings() {
		return bloodBankRatings;
	}

	public void setBloodBankRatings(Set<BloodBankRating> bloodBankRatings) {
		this.bloodBankRatings = bloodBankRatings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<Appointment> getFreeAppointments() {
		return freeAppointments;
	}

	public void setFreeAppointments(Set<Appointment> freeAppointments) {
		this.freeAppointments = freeAppointments;
	}

	public Set<User> getAllStaff() {
		return allStaff;
	}

	public void setAllStaff(Set<User> allStaff) {
		this.allStaff = allStaff;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public BloodBankStorage getBloodBankStorage() {
		return bloodBankStorage;
	}

	public void setBloodBankStorage(BloodBankStorage bloodBankStorage) {
		this.bloodBankStorage = bloodBankStorage;
	}
}