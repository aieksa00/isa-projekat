package isaapp.g3malt.dto;

import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.UserType;

public class AllUserInfoDto {
	public Integer userId;
	public String userName;
	public String userSurname;
	public String userStreet;
	public String userCity;
	public String userCountry;
	public String userPhoneNumber;
	public String userJmbg;
	public GenderType userGender;
	public String userProfession;
	public String userWorkplace;
	 
	public String userCredentialsEmail;
	public String userCredentialsPassword;
	public AllUserInfoDto(Integer userId, String userName, String userSurname, String userStreet, String userCity,
			String userCountry, String userPhoneNumber, String userJmbg, GenderType userGender, String userProfession,
			String userWorkplace,  String userCredentialsEmail, String userCredentialsPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userStreet = userStreet;
		this.userCity = userCity;
		this.userCountry = userCountry;
		this.userPhoneNumber = userPhoneNumber;
		this.userJmbg = userJmbg;
		this.userGender = userGender;
		this.userProfession = userProfession;
		this.userWorkplace = userWorkplace;
		this.userCredentialsEmail = userCredentialsEmail;
		this.userCredentialsPassword = userCredentialsPassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public String getUserStreet() {
		return userStreet;
	}
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserJmbg() {
		return userJmbg;
	}
	public void setUserJmbg(String userJmbg) {
		this.userJmbg = userJmbg;
	}
	public GenderType getUserGender() {
		return userGender;
	}
	public void setUserGender(GenderType userGender) {
		this.userGender = userGender;
	}
	public String getUserProfession() {
		return userProfession;
	}
	public void setUserProfession(String userProfession) {
		this.userProfession = userProfession;
	}
	public String getUserWorkplace() {
		return userWorkplace;
	}
	public void setUserWorkplace(String userWorkplace) {
		this.userWorkplace = userWorkplace;
	}
	public String getUserCredentialsEmail() {
		return userCredentialsEmail;
	}
	public void setUserCredentialsEmail(String userCredentialsEmail) {
		this.userCredentialsEmail = userCredentialsEmail;
	}
	public String getUserCredentialsPassword() {
		return userCredentialsPassword;
	}
	public void setUserCredentialsPassword(String userCredentialsPassword) {
		this.userCredentialsPassword = userCredentialsPassword;
	}
}
