package isaapp.g3malt.dto;

import java.util.Date;

public class FreeAppointmentDto {
	private Integer bloodBankId;
	private Integer appointmentId;
	private Integer duration;
	private String scheduleDate;
	private String scheduleTime;
	
	
	public Integer getBloodBankId() {
		return bloodBankId;
	}
	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	
	
}
