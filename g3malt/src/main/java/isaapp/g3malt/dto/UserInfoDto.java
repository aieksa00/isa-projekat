package isaapp.g3malt.dto;

import javax.persistence.Column;

import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.UserType;

public class UserInfoDto {
	
	private Integer id;
	private String name;
	private String surname;
	private String address;
	private String city;
	private String country;
	private String phoneNumber;
	private String jmbg;
	private GenderType gender;
	private String profession;
	private String workplace;
	private UserType userType;
	private String email;
	private String password;
}
