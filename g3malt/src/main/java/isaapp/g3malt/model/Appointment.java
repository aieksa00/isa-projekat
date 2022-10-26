package isaapp.g3malt.model;

import java.util.Date;
import java.util.List;

public class Appointment {
	
	private String id;
	private List<User> medicalStaff;
	private Date scheduleDateTime;
	private int duration;
	private double price;
	private User user;
	private boolean isFree;
	
	public Appointment(String id, List<User> medicalStaff, Date scheduleDateTime, int duration, double price, User user,
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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