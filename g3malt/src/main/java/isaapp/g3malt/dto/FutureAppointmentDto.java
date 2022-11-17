package isaapp.g3malt.dto;

import java.util.Date;
import java.util.Set;

public class FutureAppointmentDto {
	private Integer appointmentId;
	private Integer duration;
	private Date scheduleDateTime;
	private Set<StaffDto> medicalStaff;
	
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
	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public Set<StaffDto> getMedicalStaff() {
		return medicalStaff;
	}
	public void setMedicalStaff(Set<StaffDto> medicalStaff) {
		this.medicalStaff = medicalStaff;
	}
}
