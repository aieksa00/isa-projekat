package isaapp.g3malt.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bloodBanks")
public class BloodBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name", unique=false, nullable=true)
	private String name;
	@Column(name="address", unique=false, nullable=true)
	private String address;
	@Column(name="description", unique=false, nullable=true)
	private String description;
	@Column(name="rating", unique=false, nullable=true)
	private double rating;
	private List<Appointment> freeAppointments;
	private List<User> allStaff;
	@Column(name="workingHours", unique=false, nullable=true)
	private String workingHours;
	private Map<String, Integer> bloodStorage = new HashMap<String, Integer>();

	public BloodBank(String name, Integer id, String address, String description, double rating,
			List<Appointment> freeAppointments, List<User> allStaff, String workingHours) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.description = description;
		this.rating = rating;
		this.freeAppointments = freeAppointments;
		this.allStaff = allStaff;
		this.workingHours = workingHours;
	}

	public BloodBank() {
		super();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Appointment> getFreeAppointments() {
		return freeAppointments;
	}

	public void setFreeAppointments(List<Appointment> freeAppointments) {
		this.freeAppointments = freeAppointments;
	}

	public List<User> getAllStaff() {
		return allStaff;
	}

	public void setAllStaff(List<User> allStaff) {
		this.allStaff = allStaff;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public Map<String, Integer> getBloodStorage() {
		return bloodStorage;
	}

	public void setBloodStorage(Map<String, Integer> bloodStorage) {
		this.bloodStorage = bloodStorage;
	}
}