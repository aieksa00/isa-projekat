package isaapp.g3malt.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bloodBank_id")
	//@Column(name="customer", unique=false, nullable=true)
	private BloodBank bloodBank;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@Column(name="medicalStaff", unique=false, nullable=true)
	private Set<User> medicalStaff;
	
	@Column(name="scheduleDateTime", unique=false, nullable=true)
	private Date scheduleDateTime;
	
	@Column(name="duration", unique=false, nullable=true)
	private int duration;
	
	@Column(name="price", unique=false, nullable=true)
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	//@Column(name="customer", unique=false, nullable=true)
	private Customer customer;
	
	@Column(name="isFree", unique=false, nullable=true)
	private boolean isFree;

	public Appointment() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
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