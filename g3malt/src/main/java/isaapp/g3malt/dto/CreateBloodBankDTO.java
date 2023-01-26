package isaapp.g3malt.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
public class CreateBloodBankDTO {
	@NotEmpty
	@NotNull
	public String name;
	@NotEmpty
	@NotNull
	@Size(min=10)
	public String description;
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
	@Pattern(regexp="(((0|1)[0-9])|(2[0-4]))(\\-)(((0|1)[0-9])|(2[0-4]))")
	public String workingHours;
	
	public CreateBloodBankDTO() {}
	
	public CreateBloodBankDTO(String name, String description, String street,  String city, String country, String workingHours) {
		super();
		this.name = name;
		this.description = description;
		this.address = street;
		this.city = city;
		this.country = country;
		this.workingHours = workingHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStreet() {
		return address;
	}
	public void setStreet(String street) {
		this.address = street;
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
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
}
