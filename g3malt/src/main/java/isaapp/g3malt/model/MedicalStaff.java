package isaapp.g3malt.model;

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
public class MedicalStaff extends User{
	@ManyToMany(mappedBy = "medicalStaff")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	public MedicalStaff() {
		super();
	}
	public MedicalStaff(Integer id, String name, String surname, String street, String city, String country, String phoneNumber,
			String jmbg, GenderType gender, String profession, String workplace, UserType userType,Set<Appointment> appointments) {
		super(id, name, surname, street, city, country, phoneNumber, jmbg, gender, profession, workplace, new ArrayList<UserType>(){{add(userType);}}, workplace);
		this.appointments = appointments;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
