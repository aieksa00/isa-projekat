package isaapp.g3malt.dto;

import isaapp.g3malt.model.User;

public class UserDTO {
	 public String name;
	 public String surname;
	 public String address;
	 public String city;
	 public String country;
	 public String phoneNumber;
	 public String jmbg;
	 public String gender;
	 public String profession;
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
