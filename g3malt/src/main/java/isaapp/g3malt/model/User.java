package isaapp.g3malt.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="u_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name", unique=false, nullable=true)
	private String id;
	@NotEmpty
	@NotNull
	private String name;
<<<<<<< HEAD
	@Column(name="surname", unique=false, nullable=true)
	private String surname;
	@Column(name="street", unique=false, nullable=true)
	private String street;
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
=======
	@NotEmpty
	@NotNull
	private String surname;
	@NotEmpty
	@NotNull
	private String address;
	@NotEmpty
	@NotNull
	private String city;
	@NotEmpty
	@NotNull
	private String country;
	@Range(min=9, max=14)
	private String phoneNumber;
	@Range(min=13, max=13)
	private String jmbg;
	@NotNull
	private GenderType gender;
	@NotEmpty
	@NotNull
	private String profession;
	@NotEmpty
	@NotNull
	private String workplace;
	@NotNull
>>>>>>> 58c08ed (validation model)
	private UserType userType;
	
	public User() {}

	public User(Integer id, String name, String surname, String street, String city, String country, String phoneNumber,
			String jmbg, GenderType gender, String profession, String workplace, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.street = street;
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