package isaapp.g3malt.dto;

public class CreateAppointmentByPatientDTO {
	private Integer appointmentId;
	private Integer duration;
	private String scheduleDateTime;
	private Integer customerId;
	
	public CreateAppointmentByPatientDTO() {
		super();
	}
	public CreateAppointmentByPatientDTO(Integer appointmentId, Integer duration, String scheduleDateTime,
			Integer customerId) {
		super();
		this.appointmentId = appointmentId;
		this.duration = duration;
		this.scheduleDateTime = scheduleDateTime;
		this.customerId = customerId;
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
	public String getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
