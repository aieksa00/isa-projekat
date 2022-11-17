package isaapp.g3malt.dto;

import isaapp.g3malt.model.User;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
	@NotEmpty
	@NotNull
	 public String name;
	@NotEmpty
	@NotNull
	 public String surname;
	@NotEmpty
	@NotNull
	 public String address;
	@NotEmpty
	@NotNull
	 public String city;
	@NotEmpty
	@NotNull
	 public String country;
	@NotEmpty
	@NotNull
	@Size(min=6, max=15)
	public String phoneNumber;
	@NotEmpty
	@NotNull
	@Size(min=13, max=13)
	 public String jmbg;
	@NotEmpty
	@NotNull
	 public String gender;
	@NotEmpty
	@NotNull
	 public String profession;
	@NotEmpty
	@NotNull
	 public String workplace;
	 public int userType;
	 public int userId;

	 public UserDTO() {}
	 
	 public UserDTO(User u) {
		 name = u.getName();
		 surname = u.getSurname();
		 address = u.getStreet();
		 city = u.getCity();
		 country = u.getCountry();
		 phoneNumber = u.getPhoneNumber();
		 jmbg = u.getJmbg();
		 gender = u.getGender().toString();
		 profession = u.getProfession();
		 workplace = u.getWorkplace();
		 userType = u.getUserType().getValue();
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	
}
