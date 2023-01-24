package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="appointments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "bloodBankId")
	private int bloodBankId;
	
	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "appointments_medical_staff", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medical_staff_id", referencedColumnName = "id"))
	private Set<User> medicalStaff;
	
	@Column(name="scheduleDateTime", unique=false, nullable=true)
	private Date scheduleDateTime;
	
	@Column(name="duration", unique=false, nullable=true)
	private int duration;
	
	@Column(name="price", unique=false, nullable=true)
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = true)
	private Customer customer;
	
	@Column(name="isFree", nullable=true)
	private boolean isFree;

	public Appointment() {}
	
	public Appointment(int bloodBankId, Set<User> medicalStaff, Date scheduleDateTime, int duration, double price,
			Customer customer, boolean isFree) {
		super();
		this.bloodBankId = bloodBankId;
		this.medicalStaff = medicalStaff;
		this.scheduleDateTime = scheduleDateTime;
		this.duration = duration;
		this.price = price;
		this.customer = customer;
		this.isFree = isFree;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(int bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<User> getMedicalStaff() {
		return medicalStaff;
	}

	public void setMedicalStaff(Set<User> medicalStaff) {
		this.medicalStaff = medicalStaff;
	}

	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}

	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer user) {
		this.customer = user;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
}