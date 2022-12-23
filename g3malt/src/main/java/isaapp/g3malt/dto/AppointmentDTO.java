package isaapp.g3malt.dto;

import isaapp.g3malt.model.Customer;

import java.util.Date;

public class AppointmentDTO {

    public Integer Id;
    public Integer bloodBankId;
    public Integer customerId;
    public Date scheduleDateTime;
    public int duration;
    public boolean free;

    public AppointmentDTO(){}

    public AppointmentDTO(Integer id, Integer bloodBankId, Integer customerId, Date scheduleDateTime, int duration, boolean free) {
        this.Id = id;
        this.bloodBankId = bloodBankId;
        this.customerId = customerId;
        this.scheduleDateTime = scheduleDateTime;
        this.duration = duration;
        this.free = free;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getBloodBankId() {
        return bloodBankId;
    }

    public void setBloodBankId(Integer bloodBankId) {
        this.bloodBankId = bloodBankId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
