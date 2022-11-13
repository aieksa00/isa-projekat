package isaapp.g3malt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="medicalStaff", unique=false, nullable=true)
	private List<User> medicalStaff;
	@Column(name="scheduleDateTime", unique=false, nullable=true)
	private Date scheduleDateTime;
	@Column(name="duration", unique=false, nullable=true)
	private int duration;
	@Column(name="price", unique=false, nullable=true)
	private double price;
	@Column(name="user", unique=false, nullable=true)
	private User user;
	@Column(name="isFree", unique=false, nullable=true)
	private boolean isFree;
	
	public Appointment(Integer id, List<User> medicalStaff, Date scheduleDateTime, int duration, double price, User user,
			boolean isFree) {
		super();
		this.id = id;
		this.medicalStaff = medicalStaff;
		this.scheduleDateTime = scheduleDateTime;
		this.duration = duration;
		this.price = price;
		this.user = user;
		this.isFree = isFree;
	}

	public Appointment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getMedicalStaff() {
		return medicalStaff;
	}

	public void setMedicalStaff(List<User> medicalStaff) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
}