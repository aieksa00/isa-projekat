package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="medical_staff")
@DiscriminatorValue("1")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicalStaff extends User{
	@ManyToMany(mappedBy = "medicalStaff")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	public MedicalStaff() {
		super();
	}
	public MedicalStaff(Integer id, String name, String surname, String street, String city, String country, String phoneNumber,
			String jmbg, GenderType gender, String profession, String workplace, List<UserType> userType,String lastApp,Set<Appointment> appointments) {
		super(id, name, surname, street, city, country, phoneNumber, jmbg, gender, profession, workplace, userType, lastApp);
		this.appointments = appointments;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
