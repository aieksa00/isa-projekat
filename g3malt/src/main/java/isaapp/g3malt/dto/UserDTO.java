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

}
