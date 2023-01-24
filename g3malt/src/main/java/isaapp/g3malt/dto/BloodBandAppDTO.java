package isaapp.g3malt.dto;

import isaapp.g3malt.model.Appointment;

import java.util.Set;

public class BloodBandAppDTO {
    public Integer id;
    public String name;
    public String street;
    public String city;
    public Set<AppointmentDTO> freeAppointments;

    public BloodBandAppDTO() {}

    public BloodBandAppDTO(Integer id, String name, String street, String city, Set<AppointmentDTO> freeAppointments) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.freeAppointments = freeAppointments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<AppointmentDTO> getFreeAppointments() {
        return freeAppointments;
    }

    public void setFreeAppointments(Set<AppointmentDTO> freeAppointments) {
        this.freeAppointments = freeAppointments;
    }
}
