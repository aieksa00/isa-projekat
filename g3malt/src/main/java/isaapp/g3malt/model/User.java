package isaapp.g3malt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="u_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class User implements UserDetails {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@NotNull
	@Column(name="name", unique=false, nullable=true)
	private String name;
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
	@Column(name="last_appointment", unique=false, nullable=true)
	private String lastAppointment;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_types",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_type_id", referencedColumnName = "id"))
	private List<UserType> userType;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private UserCredentials usercredentials;

	public User() {}

	public User(Integer id, String name, String surname, String street, String city, String country, String phoneNumber,
			String jmbg, GenderType gender, String profession, String workplace, List<UserType> userType, String lastAppointment) {
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
		this.lastAppointment = lastAppointment;
	}

	
	public String getLastAppointment() {
		return lastAppointment;
	}

	public void setLastAppointment(String lastAppointment) {
		this.lastAppointment = lastAppointment;
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

	public List<UserType> getUserType() {
		return userType;
	}

	public void setUserType(List<UserType> userType) {
		this.userType = userType;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.userType;
	}

	@Override
	public String getPassword() {
		return this.usercredentials.getPassword();
	}

	@Override
	public String getUsername() {
		return this.usercredentials.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}