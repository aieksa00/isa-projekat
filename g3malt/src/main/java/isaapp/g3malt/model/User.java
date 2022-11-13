package isaapp.g3malt.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name", unique=false, nullable=true)
	private String name;
	@Column(name="surname", unique=false, nullable=true)
	private String surname;
	@Column(name="address", unique=false, nullable=true)
	private String address;
	@Column(name="city", unique=false, nullable=true)
	private String city;
	@Column(name="country", unique=false, nullable=true)
	private String country;
	@Column(name="phoneNumber", unique=false, nullable=true)
	private String phoneNumber;
	@Column(name="jmbg", unique=false, nullable=true)
	private String jmbg;
	@Column(name="gender", unique=false, nullable=true)
	private GenderType gender;
	@Column(name="profession", unique=false, nullable=true)
	private String profession;
	@Column(name="workplace", unique=false, nullable=true)
	private String workplace;
	@Column(name="userType", unique=false, nullable=true)
	private UserType userType;
	
	public User() {}

	public User(Integer id, String name, String surname, String address, String city, String country, String phoneNumber,
			String jmbg, GenderType gender, String profession, String workplace, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
		this.gender = gender;
		this.profession = profession;
		this.workplace = workplace;
		this.userType = userType;
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

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}